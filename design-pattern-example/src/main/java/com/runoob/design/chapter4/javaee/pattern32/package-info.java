/**
 * 服务定位器模式（Service Locator Pattern）用在我们想使用 JNDI 查询定位各种服务的时候。<br>
 * 考虑到为某个服务查找 JNDI 的代价很高，服务定位器模式充分利用了缓存技术。<br>
 * 在首次请求某个服务时，服务定位器在 JNDI 中查找服务，并缓存该服务对象。<br>
 * 当再次请求相同的服务时，服务定位器会在它的缓存中查找，这样可以在很大程度上提高应用程序的性能。<br>
 * 以下是这种设计模式的实体。<br><br>
 * 服务（Service） - 实际处理请求的服务。对这种服务的引用可以在 JNDI 服务器中查找到。<br><br>
 * Context / 初始的 Context - JNDI Context 带有对要查找的服务的引用。<br><br>
 * 服务定位器（Service Locator） - 服务定位器是通过 JNDI 查找和缓存服务来获取服务的单点接触。<br><br>
 * 缓存（Cache） - 缓存存储服务的引用，以便复用它们。<br><br>
 * 客户端（Client） - Client 是通过 ServiceLocator 调用服务的对象。<br><br>
 * 我们将创建 ServiceLocator、InitialContext、Cache、Service 作为表示实体的各种对象。<br>
 * Service1 和 Service2 表示实体服务。<br>
 * ServiceLocatorPatternDemo 类在这里是作为一个客户端，<br>
 * 将使用 ServiceLocator 来演示服务定位器设计模式。<br>
 */
package com.runoob.design.chapter4.javaee.pattern32;
