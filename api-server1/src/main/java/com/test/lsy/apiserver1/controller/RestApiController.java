package com.test.lsy.apiserver1.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    @GetMapping("")
    public String index() {
        return "api server 1 request~~~";
    }

    @GetMapping("/user")
    public List<String> ms1(@RequestHeader Map<String, String> headers) {

        headers.forEach((key, value) -> log.info("key : {}, value : {}", key, value));

        log.info("user server~~~~~~~~~~~~~~~~");
        ArrayList<String> list = new ArrayList<>();
        list.add("user server");
        list.add("홍길동");
        list.add("임꺽정");
        return list;
    }
}
