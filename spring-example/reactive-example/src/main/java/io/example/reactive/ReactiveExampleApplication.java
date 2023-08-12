package io.example.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用程序启动类
 *
 * @author weiwei
 * @version v1
 * @since 2022年4月26日, 下午5:53:23
 */
@SpringBootApplication
@Slf4j
public class ReactiveExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReactiveExampleApplication.class, args);
        log.info("工厂中定义的 bean 数量 = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
    }

}
