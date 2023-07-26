import com.example.demo.api.IHelloService;
import com.example.demo.service.HelloService;

/**
 * 接口实现模块
 * 
 * @author zhang weiwei
 * @provides IHelloService
 */
module com.example.demo {
	requires com.example.demo.api;

//    exports com.example.demo;
//    exports com.example.demo.service;

	provides IHelloService with HelloService;
}
