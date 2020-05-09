package com.spayker.account.util.factory;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.Gender;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;

import static com.spayker.account.domain.Gender.MALE;

public class AccountFactory {

    public static Account createAccount(){
        return Account.builder()
                .name(RandomStringUtils.randomAlphabetic(11))
                .email(RandomStringUtils.randomAlphabetic(11) + "@gmail.com")
                .createdDate(new Date())
                .modifiedDate(new Date())
                .age(Integer.parseInt(RandomStringUtils.randomNumeric(2)))
                .gender(MALE)
                .weight(Integer.parseInt(RandomStringUtils.randomNumeric(2)))
                .height(Integer.parseInt(RandomStringUtils.randomNumeric(2)))
                .build();
    }


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
