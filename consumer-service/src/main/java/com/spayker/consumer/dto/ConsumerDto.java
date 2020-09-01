package com.spayker.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Data container object for Consumer entity. Used to isolate real Consumer model entities from what comes in system
 * from outer world
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ConsumerDto {

    private long id;

    private String name;

    private String email;

    private String password;

    private Date createdDate;

    private Date modifiedDate;

}
