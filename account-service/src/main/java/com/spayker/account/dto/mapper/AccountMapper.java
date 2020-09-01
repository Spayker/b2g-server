package com.spayker.account.dto.mapper;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;
import com.spayker.account.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * MapStruct interface with declared methods that helps handling account dto. Last ones come from outer world
 * and can be observed mostly in controller layer
 */
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account accountDtoToAccount(AccountDto accountDto);

    @Mapping(source = "email", target = "username")
    User accountDtoToUser(AccountDto accountDto);

}
