package com.test.lsy.apiserver1.controller;


import com.test.lsy.apiserver1.model.OrderDTo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8082/prod/catalog";

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

    @GetMapping("/user/callProd")
    @CircuitBreaker(name = "prodCircuitBreaker", fallbackMethod = "fallbackOrders")
    public List<OrderDTo> getOrders(@RequestParam(name = "category",required = false) String category) throws Exception{
        String url = category == null ? BASE_URL : BASE_URL + "?category=" + category;
        log.info("request url : {}", url);

        try {
            ResponseEntity<List<OrderDTo>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<OrderDTo>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            log.error("RestTemplate 호출 중 예외 발생: {}", e.toString());
            throw new RuntimeException(e); // 반드시 던져야 서킷 브레이커가 fallback 호출함
        }
    }

    // fallback 메서드
    public List<OrderDTo> fallbackOrders(String category, Throwable t) throws Exception{
        log.warn("Fallback 실행됨 - API2 호출 실패. category: {}, error: {}", category, t.toString());

        // 기본 응답 예시
        OrderDTo fallbackOrder = new OrderDTo();
        fallbackOrder.setId(9999);
        fallbackOrder.setName("대체 상품");
        fallbackOrder.setCategory("대체 카테고리");
        fallbackOrder.setColor("white");
        fallbackOrder.setPrice(-9999);

        List<OrderDTo> fallbackList = new ArrayList<>();
        fallbackList.add(fallbackOrder);

        return fallbackList;
    }
}
