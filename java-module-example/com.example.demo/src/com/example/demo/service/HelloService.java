package com.example.demo.service;

import com.example.demo.api.IHelloService;

public class HelloService implements IHelloService {
    public String getMessage() {
        return "Hello Java 9 Module System!";
    }
}
