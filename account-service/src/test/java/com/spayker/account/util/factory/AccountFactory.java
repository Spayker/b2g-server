package com.spayker.account.util.factory;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.Gender;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;

public class AccountFactory {


    public static Account createAccount(String name,
                                        String email,
                                        Date createDate,
                                        Date modifiedDate,
                                        int age,
                                        Gender male,
                                        int weight,
                                        int height) {
        return Account.builder()
                .name(name)
                .email(email)
                .createdDate(createDate)
                .modifiedDate(modifiedDate)
                .age(age)
                .gender(male)
                .weight(weight)
                .height(height)
                .build();
    }
}
