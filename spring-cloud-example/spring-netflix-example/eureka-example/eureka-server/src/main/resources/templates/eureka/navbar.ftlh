<h1>系统状态</h1>
<div class="row">
  <div class="col-md-6">
    <table id='instances' class="table table-condensed table-striped table-hover">
      <#if amazonInfo??>
        <tr>
          <td>EUREKA 服务</td>
          <td>AMI: ${amiId!}</td>
        </tr>
        <tr>
          <td>区域</td>
          <td>${availabilityZone!}</td>
        </tr>
        <tr>
          <td>实例id</td>
          <td>${instanceId!}</td>
        </tr>
      </#if>
      <tr>
        <td>环境</td>
        <td>${environment!}</td>
      </tr>
      <tr>
        <td>数据中心</td>
        <td>${datacenter!}</td>
      </tr>
    </table>
  </div>
  <div class="col-md-6">
    <table id='instances' class="table table-condensed table-striped table-hover">
      <tr>
        <td>当前时间</td>
        <td>${currentTime}</td>
      </tr>
      <tr>
        <td>正常运行时间</td>
        <td>${upTime}</td>
      </tr>
      <tr>
        <td>租约到期已启用</td>
        <td>${registry.leaseExpirationEnabled?c}</td>
      </tr>
      <tr>
        <td>更新阈值</td>
        <td>${registry.numOfRenewsPerMinThreshold}</td>
      </tr>
      <tr>
        <td>更新（最后一分钟）</td>
        <td>${registry.numOfRenewsInLastMin}</td>
      </tr>
    </table>
  </div>
</div>

<#if isBelowRenewThreshold>
    <#if !registry.selfPreservationModeEnabled>
        <h4 id="uptime"><font size="+1" color="red"><b>续订低于阈值. 自我保护模式已关闭. 如果出现网络/其他问题, 这可能无法保护实例过期.</b></font></h4>
    <#else>
        <h4 id="uptime"><font size="+1" color="red"><b>突发事件! EUREKA可能错误地对未启动的实例声明为启动状态. 续订低于阈值, 因此为了安全起见，实例不会过期.</b></font></h4>
    </#if>
<#elseif !registry.selfPreservationModeEnabled>
    <h4 id="uptime"><font size="+1" color="red"><b>自我保护模式已关闭. 如果出现网络/其他问题, 这可能无法保护实例过期.</b></font></h4>
</#if>

<h1>DS 副本</h1>
<ul class="list-group mb-3">
  <#list replicas as replica>
    <li class="list-group-item"><a href="${replica.value}">${replica.key}</a></li>
  </#list>
</ul>
