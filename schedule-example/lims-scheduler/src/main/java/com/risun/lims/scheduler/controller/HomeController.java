package com.risun.lims.scheduler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Paths;

/**
 * Home控制器类
 * @author 张维维
 * @since 2024-11-28 08:58:07
 */
@Controller
@Slf4j
public class HomeController {
    @GetMapping(path = {"/", "/favicon.ico"})
    public ResponseEntity<String> index() {
    	return ResponseEntity.ok("hello world!");
    }

    @GetMapping(path = "/{filename}")
    public ResponseEntity<?> loadFile(@PathVariable String filename) {
        log.debug("loadFile(): filename = {}", filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        PathResource body = new PathResource(Paths.get(".", filename));
        if (!body.exists()) {
            return new ResponseEntity<>(String.format("此文件[%s]不存在！", filename), headers, HttpStatus.OK);
        }
        if (!body.isReadable()) {
            return new ResponseEntity<>(String.format("此文件[%s]不可读！", filename), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
