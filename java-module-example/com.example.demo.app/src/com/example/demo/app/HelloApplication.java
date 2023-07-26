package com.example.demo.app;

import com.example.demo.api.IHelloService;
//import com.example.demo.service.HelloService;

import java.util.ServiceLoader;

public class HelloApplication {
    public static void main(String[] args) {
//        IHelloService service = new HelloService();
        IHelloService service = ServiceLoader.load(IHelloService.class).findFirst().orElseThrow();
        System.err.println(service.getMessage("模块com.example.demo.app"));
    }
}
