
## 快速开始
开始第一个项目非常容易。

#### 启动服务器

##### -Dserver.port：指定启动的端口，默认8080
##### -Dproject.name：指定本服务的名称
##### -Dcsp.sentinel.dashboard.server：指定sentinel控制台的地址，用于将自己注册进入实现监控自己
##### -Dsentinel.dashboard.auth.username=sentinel 用于指定控制台的登录用户名为 "sentinel"，默认值为 “sentinel”
##### -Dsentinel.dashboard.auth.password=123456 用于指定控制台的登录密码为 "123456"，默认值为 "sentinel"
##### -Dserver.servlet.session.timeout=7200 用于指定 Spring Boot 服务端 session 的过期时间，如 7200 表示 7200 秒；60m 表示 60 分钟，默认为 30 分钟，需要注意的是，部署多台控制台时，session 默认不会在各实例之间共享，这一块需要自行改造。

```
java -Dserver.port=9999 -Dcsp.sentinel.dashboard.server=localhost:9999 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
```
