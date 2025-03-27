package com.test.lsy.apiserver2.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    @GetMapping("")
    public String index() {
        return "api server 2 request~~~";
    }

    @GetMapping("/prod")
    public List<String> ms2() {
        log.info("product server~~~~~~~~~~~~~~~~");
        ArrayList<String> list = new ArrayList<>();
        list.add("product server");
        list.add("간장");
        list.add("마늘");
        return list;
    }
}
