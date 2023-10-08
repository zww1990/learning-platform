$( document ).ready(function() {
   console.log('欢迎来到我的视频网站');
   // 遍历li标签，从而获取下面a标签的href
   $('#navbarsDefault').find('li').each(function () {
       var a = $(this).find('a:first')[0];
       // 判断a标签的href是否与当前页面的路径相同
       if ($(a).attr("href") === location.pathname) {
           // 添加激活状态
           $(a).addClass("active");
       } else {
           // 移除激活状态
           $(a).removeClass("active");
       }
   });
   // 遍历li标签，从而获取下面a标签的href
   $('#navbarsExplore').find('li').each(function () {
       var a = $(this).find('a:first')[0];
       // 判断a标签的href是否与当前页面的路径相同
       if ($(a).attr("href") === location.search) {
           // 添加激活状态
           $(a).addClass("active");
       } else {
           // 移除激活状态
           $(a).removeClass("active");
       }
   });
   // 遍历li标签，从而获取下面a标签的href
   $('#navbarsExplore > ul > li.nav-item.dropdown > div').find('a').each(function () {
       var a = $(this);
       // 判断a标签的href是否与当前页面的路径相同
       if ($(a).attr("href") === location.search) {
           // 添加激活状态
           $(a).addClass("active");
       } else {
           // 移除激活状态
           $(a).removeClass("active");
       }
   });
    // 对话框事件绑定
    $('#exampleModal').on('show.bs.modal', function (event) {
        var div = $(event.relatedTarget);
        var name = div.data('name');
        var logo = div.data('logo');
        var modal = $(this);
        modal.find('.modal-title').text(name);
        modal.find('.modal-body img').prop('src', logo);
    });
    // 视频查看
    $('.videohubshow').on('click', function(event){
        event.stopPropagation();
        var url = $(this).data('url');
        location.replace(url);
    });
    // 视频审核
    $('.videohubaudit').on('click', function(event){
        event.stopPropagation();
        var url = $(this).data('url');
        location.replace(url);
    });
    // 视频删除
    $('.videohubdelete').on('click', function(event){
        event.stopPropagation();
        var name = $(this).data('name');
        var id = $(this).data('id');
        $('#exampleModal2').find('.modal-title').text(name);
        $('#exampleModal2').find('.modal-body input').val(id);
        $('#exampleModal2').modal('toggle');
    });
    // 视频删除确定操作
    $('#exampleModal2 button.btn.btn-primary').on('click', function(event){
        var id = $('#exampleModal2').find('.modal-body input').val();
        location.replace('/videohub/delete/' + id);
    });
    // 图片预览
    $('#videoLogo').on('change', function(){
        var file = this.files[0];
        var src = URL.createObjectURL(file);
        $('#videoLogoPreview').prop('src', src);
    });
});
// 用于在存在无效字段时禁用表单提交
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // 获取我们要应用自定义Bootstrap验证样式的所有表单
    var forms = document.getElementsByClassName('needs-validation');
    // 循环它们并防止提交
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
