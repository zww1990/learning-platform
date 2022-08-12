package io.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * DesignPatternApplication
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午8:45:37
 */
@SpringBootApplication
@EnableRetry
public class DesignPatternApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DesignPatternApplication.class, args);
	}
}
