package com.spayker.consumer.util.factory;

import com.spayker.consumer.domain.Account;
import com.spayker.consumer.domain.Training;

import java.util.Date;

public class TrainingFactory {

    public static Training createTraining(String deviceName,
                                          String deviceFirmware,
                                          String type,
                                          int time,
                                          int callories,
                                          Date createdDate,
                                          Account account) {
        return Training.builder()
                .deviceName(deviceName)
                .deviceFirmware(deviceFirmware)
                .type(type)
                .time(time)
                .calories(callories)
                .createdDate(createdDate)
                .account(account)
                .build();
    }

    public static Training createTraining(Long id,
                                          String deviceName,
                                          String deviceFirmware,
                                          String type,
                                          int time,
                                          int callories,
                                          Date createdDate,
                                          Account account) {
        return Training.builder()
                .id(id)
                .deviceName(deviceName)
                .deviceFirmware(deviceFirmware)
                .type(type)
                .time(time)
                .calories(callories)
                .createdDate(createdDate)
                .account(account)
                .build();
    }

}
