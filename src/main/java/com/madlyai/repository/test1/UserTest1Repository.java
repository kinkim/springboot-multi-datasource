package com.madlyai.repository.test1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madlyai.domain.User;

public interface UserTest1Repository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}
