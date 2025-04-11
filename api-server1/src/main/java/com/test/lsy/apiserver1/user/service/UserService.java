package com.test.lsy.apiserver1.user.service;

import com.test.lsy.apiserver1.user.entity.UserEntity;
import com.test.lsy.apiserver1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }
}
