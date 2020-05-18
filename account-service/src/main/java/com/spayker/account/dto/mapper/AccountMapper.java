package com.spayker.account.dto.mapper;

import com.spayker.account.domain.Account;
import com.spayker.account.domain.User;
import com.spayker.account.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    Account accountDtoToAccount(AccountDto accountDto);

    User accountDtoToUser(AccountDto accountDto);

}
