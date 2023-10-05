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
     console.log(name, logo);
     var modal = $(this);
     modal.find('.modal-title').text(name);
     modal.find('.modal-body img').prop('src', logo);
   });
   $('.videohubshow').on('click', function(event){
       event.stopPropagation();
       var url = $(this).data('url');
       location.replace(url);
   });
});
