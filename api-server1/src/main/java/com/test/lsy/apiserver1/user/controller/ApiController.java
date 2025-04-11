package com.test.lsy.apiserver1.user.controller;


import com.test.lsy.apiserver1.user.entity.UserEntity;
import com.test.lsy.apiserver1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class ApiController {

    private final UserService userService;

//    @GetMapping("")
//    public String index() {
//        return "api server 1 request~~~";
//    }

    @GetMapping
    public List<UserEntity> users() {
        return userService.getUsers();
    }
}
