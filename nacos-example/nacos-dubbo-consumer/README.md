
## 快速开始
开始第一个项目非常容易。

#### 启动服务器

使用JDK17运行dubbo消费者，需要增加以下JVM参数：

```
--add-opens=java.base/java.lang=ALL-UNNAMED 
--add-opens=java.base/java.io=ALL-UNNAMED 
--add-opens=java.base/java.util=ALL-UNNAMED 
--add-opens=java.base/java.util.concurrent=ALL-UNNAMED 
--add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED 
--add-opens java.base/java.lang.reflect=ALL-UNNAMED 
--add-opens java.base/java.util=ALL-UNNAMED 
--add-opens java.base/java.math=ALL-UNNAMED
```
