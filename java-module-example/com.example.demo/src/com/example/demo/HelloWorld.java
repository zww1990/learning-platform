package com.example.demo;

import com.example.demo.api.IHelloService;
import com.example.demo.service.HelloService;

public class HelloWorld {
    public static void main(String[] args) {
        IHelloService service = new HelloService();
        System.err.println(service.getMessage("模块com.example.demo"));
    }
}
