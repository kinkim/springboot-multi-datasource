package com.madlyai.repository.test2;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madlyai.domain.User;


public interface UserTest2Repository extends JpaRepository<User,Long>{
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}
