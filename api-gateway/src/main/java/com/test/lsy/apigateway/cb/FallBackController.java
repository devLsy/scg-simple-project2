package com.test.lsy.apigateway.cb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FallBackController {

    @GetMapping("/fallback/user")
    public String userCircuitBreaker() {
        return "User 서비스에 문제가 발생했습니다. 잠시 후 다시 시도해주세요.";
    }
}
