/**
 * 业务代表模式（Business Delegate Pattern）用于对表示层和业务层解耦。<br>
 * 它基本上是用来减少通信或对表示层代码中的业务层代码的远程查询功能。在业务层中我们有以下实体。<br><br>
 * 客户端（Client） - 表示层代码可以是 JSP、servlet 或 UI java 代码。<br><br>
 * 业务代表（Business Delegate） - 一个为客户端实体提供的入口类，它提供了对业务服务方法的访问。<br><br>
 * 查询服务（LookUp Service） - 查找服务对象负责获取相关的业务实现，并提供业务对象对业务代表对象的访问。<br><br>
 * 业务服务（Business Service） - 业务服务接口。实现了该业务服务的实体类，提供了实际的业务实现逻辑。<br><br>
 * 我们将创建 Client、BusinessDelegate、BusinessService、LookUpService、JMSService 和 EJBService 来表示业务代表模式中的各种实体。<br>
 * BusinessDelegatePatternDemo 类使用 BusinessDelegate 和 Client 来演示业务代表模式的用法。<br>
 */
package com.runoob.design.chapter4.javaee.pattern27;
