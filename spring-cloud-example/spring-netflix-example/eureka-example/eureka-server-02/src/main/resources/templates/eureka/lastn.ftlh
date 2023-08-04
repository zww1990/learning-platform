<#import "/spring.ftl" as spring />
<!doctype html>
<html lang="en">
  <head>
    <base href="<@spring.url basePath/>">
    <title>Eureka注册中心 - 最近N个事件</title>
    <link rel="stylesheet" type="text/css" href="eureka/css/wro.css">
  </head>
  <body id="three">
    <script type="text/javascript">
      if ( document.documentMode ) {
         document.write('<p>您正在使用 <strong>过时的</strong> 浏览器. 请升级您的浏览器以改善您的体验.</p>');
      }
    </script>

    <#include "header.ftlh">

    <div class="container-fluid xd-container">
      <#include "navbar.ftlh">

    <div id="xd-jobs" class="tab-pane col-md-12">
        <ul class="nav nav-tabs" role="tablist" id="myTab">
          <li class="nav-item"><a href="<@spring.url dashboardPath/>/lastn#cancelled" class="nav-link p-2" data-bs-toggle="tab">最近1000个已取消租约</a></li>
          <li class="nav-item"><a href="<@spring.url dashboardPath/>/lastn#registered" class="nav-link p-2 active" data-bs-toggle="tab">最近1000个新注册租约</a></li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane" id="cancelled">
            <table id='lastNCanceled' class="table table-striped table-hover">
              <thead>
                <tr><th>时间</th><th>租约</th></tr>
              </thead>
              <tbody>
                <#if lastNCanceled?has_content>
	                <#list lastNCanceled as entry>
	                  <tr><td>${entry.date?datetime}</td><td>${entry.id}</td></tr>
	                </#list>
	            <#else>
	              <tr><td colspan="2">暂无结果</td></tr>
	            </#if>
              <tbody>
            </table>
          </div>
          <div class="tab-pane active" id="registered">
            <table id='lastNRegistered' class="table table-striped table-hover">
              <thead>
                <tr><th>时间</th><th>租约</th></tr>
              </thead>
              <tbody>
                <#if lastNRegistered?has_content>
                  <#list lastNRegistered as entry>
                    <tr><td>${entry.date?datetime}</td><td>${entry.id}</td></tr>
                  </#list>
                <#else>
	              <tr><td colspan="2">暂无结果</td></tr>
	            </#if>
              </tbody>
            </table>
          </div>
      </div>
    </div>
    </div>
    <script type="text/javascript" src="eureka/js/wro.js" ></script>
  </body>
</html>
