package com.spayker.consumer.dto.mapper;

import com.spayker.consumer.domain.Consumer;
import com.spayker.consumer.domain.User;
import com.spayker.consumer.dto.ConsumerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct interface with declared methods that helps handling consumer dto. Last ones come from outer world
 * and can be observed mostly in controller layer
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsumerMapper {

    Consumer consumerDtoToConsumer(ConsumerDto consumerDto);

    @Mapping(source = "email", target = "username")
    User consumerDtoToUser(ConsumerDto consumerDto);

}
