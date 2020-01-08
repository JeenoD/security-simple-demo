package com.jeeno.springsecuritysimpledemo.repository;

import com.jeeno.springsecuritysimpledemo.pojo.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 杜家浩
 * @version 1.0.0
 * @date 2020/1/8 17:22
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    /**
     * 根据用户名查找用户信息
     * @param username 用户名
     * @return 用户信息
     */
    UserDO findByUsername(String username);
}
