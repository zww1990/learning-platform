package com.example.demo.app;

import com.example.demo.api.IHelloService;
import com.example.demo.service.HelloService;

public class HelloApplication {
    public static void main(String[] args) {
        IHelloService service = new HelloService();
        System.err.println(service.getMessage());
    }
}
