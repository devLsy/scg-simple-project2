package com.test.lsy.apiserver1.user.controller;


import com.test.lsy.apiserver1.user.entity.UserEntity;
import com.test.lsy.apiserver1.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class ApiController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> users() {
        return userService.getUsers();
    }

    /**
     * 아이디로 조회(캐싱값이 있으면 캐시 데이터 반환)
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        UserEntity user = userService.getUserById(id);

        if (user == null) {
            log.warn("User with name '{}' not found", id);
        }
        return user;
    }
}
