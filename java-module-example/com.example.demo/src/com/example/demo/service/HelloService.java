package com.example.demo.service;

import com.example.demo.api.IHelloService;

public class HelloService implements IHelloService {
    public String getMessage(String text) {
        return text + ": Hello Java 9 Module System!";
    }
}
