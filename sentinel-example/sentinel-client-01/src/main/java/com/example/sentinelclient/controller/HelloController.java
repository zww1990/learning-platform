package com.example.sentinelclient.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloController {
    private ServerProperties server;

    @GetMapping("/hello")
    @SentinelResource(value = "hello", blockHandler = "handleException")
    public String hello() {
        return "[HTTP] " + server.getPort();
    }

    public String handleException(BlockException be) {
        return "我靠，我被限流了！";
    }
}
