package com.madlyai.repository.test2;

import com.madlyai.domain.User;
import com.madlyai.jpa.BasicRepository;

public interface UserDao2 extends BasicRepository<User,Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String userName, String email);
}
