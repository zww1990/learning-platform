/**
 * 数据访问对象模式（Data Access Object Pattern）或 DAO 模式用于把低级的数据访问 API 或操作从高级的业务服务中分离出来。<br>
 * 以下是数据访问对象模式的参与者。<br><br>
 * 数据访问对象接口（Data Access Object Interface） - 该接口定义了在一个模型对象上要执行的标准操作。<br><br>
 * 数据访问对象实体类（Data Access Object concrete class） - 该类实现了上述的接口。<br>
 * 该类负责从数据源获取数据，数据源可以是数据库，也可以是 xml，或者是其他的存储机制。<br><br>
 * 模型对象/数值对象（Model Object/Value Object） - 该对象是简单的 POJO，包含了 get/set 方法来存储通过使用 DAO 类检索到的数据。<br><br>
 * 我们将创建一个作为模型对象或数值对象的 Student 对象。StudentDao 是数据访问对象接口。StudentDaoImpl 是实现了数据访问对象接口的实体类。<br>
 * DaoPatternDemo，我们的演示类使用 StudentDao 来演示数据访问对象模式的用法。<br>
 */
package com.runoob.design.chapter4.javaee.pattern29;
