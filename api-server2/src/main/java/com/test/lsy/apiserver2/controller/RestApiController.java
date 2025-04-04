package com.test.lsy.apiserver2.controller;


import com.test.lsy.apiserver2.model.OrderDTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestApiController {

    private final List<OrderDTo> productList = List.of(
            new OrderDTo(1, "mobile", "electronics", "white", 20000),
            new OrderDTo(2, "T-Shirt", "clothes", "black", 999),
            new OrderDTo(3, "Jeans", "clothes", "blue", 1999),
            new OrderDTo(4, "Laptop", "electronics", "gray", 50000),
            new OrderDTo(5, "digital watch", "electronics", "black", 2500),
            new OrderDTo(6, "Fan", "electronics", "black", 50000)
    );

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

    @GetMapping("/prod/catalog")
    public List<OrderDTo> getCatalog(@RequestParam(value = "category", required = false) String category) {
        log.info("getCatalog :: {}", category);
        if(category == null) return productList;

        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}
