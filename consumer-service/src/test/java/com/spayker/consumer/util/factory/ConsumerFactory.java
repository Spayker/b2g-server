package com.spayker.consumer.util.factory;

import com.spayker.consumer.domain.Consumer;

import java.util.Date;

public class ConsumerFactory {

    public static Consumer createConsumer(String name, String email, Date createDate, Date modifiedDate) {
        return Consumer.builder()
                .name(name)
                .email(email)
                .createdDate(createDate)
                .modifiedDate(modifiedDate)
                .build();
    }




}
