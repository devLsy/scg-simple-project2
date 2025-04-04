package com.test.lsy.apiserver1.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<String> ms1(@RequestHeader Map<String, String> headers,
                            @RequestParam(value = "id", required = false) Long id) {

        headers.forEach((key, value) -> log.info("key : {}, value : {}", key, value));

        log.info("user server~~~~~~~~~~~~~~~~");
        ArrayList<String> list = new ArrayList<>();
        list.add("user server");
        list.add("홍길동");
        list.add("임꺽정");
        return list;
    }

    @GetMapping("/tmp")
    public String tmp() {
        return "api server 1 tmp path~~~";
    }

    @GetMapping("/user/test-error")
    public ResponseEntity<String> testError() {
//        return ResponseEntity.status(500).body("강제 오류");
        throw new RuntimeException("강제 오류");
    }

    @GetMapping("/user/test-delay")
    public String testDelay() throws InterruptedException{
        Thread.sleep(6000);
        return "느린 응답";
    }
}
