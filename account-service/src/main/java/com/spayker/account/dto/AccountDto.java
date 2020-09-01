package com.spayker.account.dto;

import com.spayker.account.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data container object for Account entity. Used to isolate real Account model entities from what comes in system
 * from outer world
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountDto {

    private long id;

    private String name;

    private String email;

    private String password;

    private Date createdDate;

    private Date modifiedDate;

    private int age;

    private Gender gender;

    private int weight;

    private int height;



}
