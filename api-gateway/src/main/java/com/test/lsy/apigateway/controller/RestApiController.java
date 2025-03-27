package com.test.lsy.apigateway.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    @GetMapping("")
    public String index() {
        return "apigateway server request~~~";
    }
}
