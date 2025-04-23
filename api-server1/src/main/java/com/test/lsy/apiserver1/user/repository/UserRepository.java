package com.test.lsy.apiserver1.user.repository;

import com.test.lsy.apiserver1.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String userName);
}
