package com.spayker.consumer.dto.mapper;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.domain.User;
import com.spayker.consumer.dto.ConsumerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsumerMapper {

    Consumer consumerDtoToConsumer(ConsumerDto consumerDto);

    @Mapping(source = "email", target = "username")
    User consumerDtoToUser(ConsumerDto consumerDto);

}
