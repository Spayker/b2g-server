package com.spayker.consumer.auth.util.factory;

import com.spayker.consumer.auth.domain.User;

import java.util.Date;

public class ConsumerFactory {


    public static User createUser(String name,
                                  String password,
                                  Date lastLogin) {
        return User.builder()
                .username(name)
                .password(password)
                .lastLogin(lastLogin)
                .build();
    }

    public static User createUser(Long id,
                                  String name,
                                  String password,
                                  Date lastLogin) {
        return User.builder()
                .id(id)
                .username(name)
                .password(password)
                .lastLogin(lastLogin)
                .build();
    }
}
