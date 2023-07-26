import com.example.demo.api.IHelloService;

/**
 * 应用程序入口模块
 * 
 * @author zhang weiwei
 * @uses IHelloService
 */
module com.example.demo.app {
//    requires com.example.demo;
	requires com.example.demo.api;

	uses IHelloService;
}
