package com.test.lsy.apiserver1.user.service;

import com.test.lsy.apiserver1.redis.RedisService;
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
    private final RedisService redisService;

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    /**
     * 아이디로 조회(캐싱값이 있으면 캐시 데이터 반환)
     * @param id
     * @return
     */
    public UserEntity getUserById(Long id) {
        UserEntity findCacheUser = (UserEntity) redisService.getFromRedis(id);

        if (findCacheUser != null) {
            log.info("cache user data: {}", id);
            return findCacheUser;
        }

        // db에서 찾기
        UserEntity findDbUser = userRepository.findById(id).get();
        if(findDbUser != null) {
            log.info("db에서 조회 후 redis에 저장: {}", id);
            redisService.saveToRedis(id, findDbUser);
            return findDbUser;
        }
        return null;
    }
}
