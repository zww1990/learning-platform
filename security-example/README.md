# 首先阅读我
在构建此项目的过程中发现了以下内容：

* 使用Wavefront进行分布式跟踪需要Spring Boot 2.3或更高版本。

# 入门

### 参考文献资料
有关更多参考，请考虑以下部分：

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.8.RELEASE/maven-plugin/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#howto-use-exposing-spring-data-repositories-rest-endpoint)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-spring-hateoas)
* [Spring Web Services](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-webservices)
* [Jersey](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-jersey)
* [Vaadin](https://vaadin.com/spring)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Apache Freemarker](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Mustache](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Groovy Templates](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-security)
* [OAuth2 Client](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-security-oauth2-client)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-security-oauth2-server)
* [Spring LDAP](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-ldap)
* [Okta](https://github.com/okta/okta-spring-boot/blob/master/README.md)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-sql)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Data JDBC](https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* [JOOQ Access Layer](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-jooq)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-redis)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-redis)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-mongodb)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-mongodb)
* [Spring Data Elasticsearch (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-elasticsearch)
* [Spring Data for Apache Solr](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-solr)
* [Spring Data for Apache Cassandra](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-cassandra)
* [Spring Data Reactive for Apache Cassandra](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-cassandra)
* [Spring for Apache Geode](https://docs.spring.io/spring-boot-data-geode-build/current/reference/html5/)
* [Spring Data Couchbase](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-couchbase)
* [Spring Data Reactive Couchbase](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-couchbase)
* [Spring Data Neo4j](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-neo4j)
* [Spring Integration](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-integration)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-amqp)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-kafka)
* [Apache Kafka Streams Support](https://docs.spring.io/spring-kafka/docs/current/reference/html/_reference.html#kafka-streams)
* [Apache Kafka Streams Binding Capabilities of Spring Cloud Stream](https://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/#_kafka_streams_binding_capabilities_of_spring_cloud_stream)
* [Spring for Apache ActiveMQ 5](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-activemq)
* [Spring for Apache ActiveMQ Artemis](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-artemis)
* [WebSocket](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-websockets)
* [Getting started with Solace and Spring](https://www.solace.dev/start-spring-io-help/)
* [Solace Developer Portal](https://solace.dev)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#howto-batch-applications)
* [Validation](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-validation)
* [Java Mail Sender](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-email)
* [Spring cache abstraction](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-caching)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#production-ready)
* [Spring Boot Admin (Client)](https://codecentric.github.io/spring-boot-admin/current/#getting-started)
* [Spring Boot Admin (Server)](https://codecentric.github.io/spring-boot-admin/current/#getting-started)
* [Datadog](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/production-ready-features.html#production-ready-metrics-export-datadog)
* [Influx](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/production-ready-features.html#production-ready-metrics-export-influx)
* [Graphite](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/production-ready-features.html#production-ready-metrics-export-graphite)
* [New Relic](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/production-ready-features.html#production-ready-metrics-export-new-relic)
* [Prometheus](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/production-ready-features.html#production-ready-metrics-export-prometheus)
* [Wavefront for Spring Boot documentation](https://docs.wavefront.com/wavefront_springboot.html)
* [Wavefront for Spring Boot repository](https://github.com/wavefrontHQ/wavefront-spring-boot)
* [Embedded LDAP Server](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/boot-features-nosql.html#boot-features-ldap-embedded)
* [Cloud Bootstrap](https://spring.io/projects/spring-cloud-commons)
* [Function](https://cloud.spring.io/spring-cloud-function/)
* [Open Service Broker](https://docs.spring.io/spring-cloud-open-service-broker/docs/current/reference/html5/)
* [Cloud LoadBalancer](https://cloud.spring.io/spring-cloud-static/spring-cloud-commons/current/reference/html/#spring-cloud-loadbalancer)
* [Resilience4J](https://cloud.spring.io/spring-cloud-static/spring-cloud-circuitbreaker/current/reference/html)
* [Config Client (PCF)](https://docs.pivotal.io/spring-cloud-services/)
* [Service Registry (PCF)](https://docs.pivotal.io/spring-cloud-services/)
* [Circuit Breaker (PCF)](https://docs.pivotal.io/spring-cloud-services/)
* [Azure Support](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot)
* [Azure Active Directory](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-starters/azure-active-directory-spring-boot-starter)
* [Azure Key Vault](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-starters/azure-keyvault-secrets-spring-boot-starter)
* [Azure Storage](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-starters/azure-storage-spring-boot-starter)
* [GCP Support](https://cloud.spring.io/spring-cloud-gcp/reference/html/)
* [GCP Messaging](https://cloud.spring.io/spring-cloud-gcp/reference/html/#spring-integration)
* [GCP Storage](https://cloud.spring.io/spring-cloud-gcp/reference/html/#spring-resources)
* [Nacos Configuration](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html#_spring_cloud_alibaba_nacos_config)
* [Nacos Service Discovery](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html#_spring_cloud_alibaba_nacos_discovery)
* [Sentinel](https://spring-cloud-alibaba-group.github.io/github-pages/hoxton/en-us/index.html#_spring_cloud_alibaba_sentinel)

### 指南
以下指南说明了如何具体使用某些功能：

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)
* [Creating CRUD UI with Vaadin](https://spring.io/guides/gs/crud-with-vaadin/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Okta-Hosted Login Page Example](https://github.com/okta/samples-java-spring/tree/master/okta-hosted-login)
* [Custom Login Page Example](https://github.com/okta/samples-java-spring/tree/master/custom-login)
* [Okta Spring Security Resource Server Example](https://github.com/okta/samples-java-spring/tree/master/resource-server)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Using Spring for Apache Geode](https://github.com/spring-projects/spring-boot-data-geode/tree/master/spring-geode-samples)
* [Accessing Data with Neo4j](https://spring.io/guides/gs/accessing-data-neo4j/)
* [Integrating Data](https://spring.io/guides/gs/integration/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Samples for using Apache Kafka Streams with Spring Cloud stream](https://github.com/spring-cloud/spring-cloud-stream-samples/tree/master/kafka-streams-samples)
* [Java Message Service API via Apache ActiveMQ Classic.](https://spring.io/guides/gs/messaging-jms/)
* [Messaging with JMS](https://spring.io/guides/gs/messaging-jms/)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Using Apache Camel with Spring Boot](https://camel.apache.org/spring-boot)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Spring Cloud Contract Verifier Setup](https://cloud.spring.io/spring-cloud-contract/spring-cloud-contract.html#_spring_cloud_contract_verifier_setup)
* [Using Spring Cloud Open Service Broker](https://github.com/spring-cloud-samples/bookstore-service-broker)
* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)
* [Service Registration and Discovery](https://spring.io/guides/gs/service-registration-and-discovery/)
* [Routing and Filtering](https://spring.io/guides/gs/routing-and-filtering/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)
* [Client-side load-balancing with Ribbon and Spring Cloud](https://spring.io/guides/gs/client-side-load-balancing/)
* [Client-side load-balancing with Spring Cloud LoadBalancer](https://spring.io/guides/gs/spring-cloud-loadbalancer/)
* [Circuit Breaker](https://spring.io/guides/gs/circuit-breaker/)
* [Using Active Directory](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-samples/azure-active-directory-spring-boot-sample)
* [Using Key Vault](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-samples/azure-keyvault-secrets-spring-boot-sample)
* [Using Azure Storage](https://github.com/Microsoft/azure-spring-boot/tree/master/azure-spring-boot-samples/azure-storage-spring-boot-sample)
* [GCP Samples](https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp-samples)
* [GCP Pub/Sub Sample](https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp-samples/spring-cloud-gcp-pubsub-sample)
* [GCP Storage Sample](https://github.com/spring-cloud/spring-cloud-gcp/tree/master/spring-cloud-gcp-samples/spring-cloud-gcp-storage-resource-sample)
* [Nacos Configuration Example](https://github.com/alibaba/spring-cloud-alibaba/tree/master/spring-cloud-alibaba-examples/nacos-example/nacos-config-example)
* [Nacos Service Discovery Example](https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/nacos-example/nacos-discovery-example/readme.md)
* [Sentinel Example](https://github.com/alibaba/spring-cloud-alibaba/tree/master/spring-cloud-alibaba-examples/sentinel-example/sentinel-core-example)

### 附加链接
这些其他参考也能帮助您：

* [Switch from Apache Geode to Pivotal Cloud Cache](https://docs.spring.io/spring-boot-data-geode-build/current/reference/html5/#geode-gemfire-switch)
* [Various sample apps using Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function/tree/master/spring-cloud-function-samples)

## Wavefront的可观察性

如果您没有Wavefront帐户，则入门程序会为您创建一个免费增值帐户。
启动时会记录访问Wavefront服务仪表盘的URL。

您还可以使用 `/actuator/wavefront` 端点访问仪表盘。
# Spring Cloud Netflix 维护模式

下面列出的依赖项处于维护模式。 我们不建议将它们添加到新项目：

*  Ribbon
*  Zuul
*  Turbine
*  Hystrix
*  Hystrix Dashboard
*  Turbine Stream

之所以决定将大多数Spring Cloud Netflix项目移至维护模式，是因为Netflix没有继续维护我们提供支持的依赖库。

请参阅 [此博客条目](https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now#spring-cloud-netflix-projects-entering-maintenance-mode)
，以获取有关维护模式的更多信息以及这些库的建议替代列表。
