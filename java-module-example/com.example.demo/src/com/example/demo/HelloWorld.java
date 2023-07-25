package com.example.demo;

import com.example.demo.service.HelloService;

public class HelloWorld {
    public static void main(String[] args) {
        HelloService service = new HelloService();
        System.err.println(service.getMessage());
    }
}
