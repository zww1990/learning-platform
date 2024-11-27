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

@Controller
@Slf4j
public class HomeController {
    @GetMapping(path = {"/", "/favicon.ico"})
    public ResponseEntity<String> index() {
    	return ResponseEntity.ok("hello world!");
    }

    @GetMapping(path = "/{filename}")
    public ResponseEntity<PathResource> loadFile(@PathVariable String filename) {
        log.info("loadFile(): filename = {}", filename);
        PathResource body = new PathResource(Paths.get(".", filename));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
