<#import "/spring.ftl" as spring />
<!doctype html>
<html lang="en">
  <head>
    <base href="<@spring.url basePath/>">
    <meta charset="utf-8">
    <title>Eureka注册中心</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="eureka/css/wro.css">

  </head>

  <body id="one">
    <script type="text/javascript">
      if ( document.documentMode ) {
         document.write('<p>您正在使用 <strong>过时的</strong> 浏览器. 请升级您的浏览器以改善您的体验.</p>');
      }
    </script>

    <#include "header.ftlh">
    <div class="container-fluid xd-container">
      <#include "navbar.ftlh">
      <h1>目前在Eureka注册的实例</h1>
      <table id='instances' class="table table-striped table-hover">
        <thead>
          <tr><th>应用</th><th>AMIs</th><th>可用区域</th><th>状态</th></tr>
        </thead>
        <tbody>
          <#if apps?has_content>
            <#list apps as app>
              <tr>
                <td><b>${app.name}</b></td>
                <td>
                  <#list app.amiCounts as amiCount>
                    <b>${amiCount.key}</b> (${amiCount.value})<#if amiCount_has_next>,</#if>
                  </#list>
                </td>
                <td>
                  <#list app.zoneCounts as zoneCount>
                    <b>${zoneCount.key}</b> (${zoneCount.value})<#if zoneCount_has_next>,</#if>
                  </#list>
                </td>
                <td>
                  <#list app.instanceInfos as instanceInfo>
                    <#if instanceInfo.isNotUp>
                      <font color=red size=+1><b>
                    </#if>
                    <b>${instanceInfo.status}</b> (${instanceInfo.instances?size}) -
                    <#if instanceInfo.isNotUp>
                      </b></font>
                    </#if>
                    <#list instanceInfo.instances as instance>
                      <#if instance.isHref>
                        <a href="${instance.url}" target="_blank">${instance.id}</a>
                      <#else>
                        ${instance.id}
                      </#if><#if instance_has_next>,</#if>
                    </#list>
                  </#list>
                </td>
              </tr>
            </#list>
          <#else>
            <tr><td colspan="4">没有可用的实例</td></tr>
          </#if>

        </tbody>
      </table>

      <h1>一般信息</h1>

      <table id='generalInfo' class="table table-striped table-hover">
        <thead>
          <tr><th>名称</th><th>值</th></tr>
        </thead>
        <tbody>
          <#list statusInfo.generalStats?keys as stat>
            <tr>
              <td>${stat}</td><td>${statusInfo.generalStats[stat]!""}</td>
            </tr>
          </#list>
          <#list statusInfo.applicationStats?keys as stat>
            <tr>
              <td>${stat}</td><td>${statusInfo.applicationStats[stat]!""}</td>
            </tr>
          </#list>
        </tbody>
      </table>

      <h1>实例信息</h1>

      <table id='instanceInfo' class="table table-striped table-hover">
        <thead>
          <tr><th>名称</th><th>值</th></tr>
        <thead>
        <tbody>
          <#list instanceInfo?keys as key>
            <tr>
              <td>${key}</td><td>${instanceInfo[key]!""}</td>
            </tr>
          </#list>
        </tbody>
      </table>
    </div>
    <script type="text/javascript" src="eureka/js/wro.js" ></script>
  </body>
</html>
