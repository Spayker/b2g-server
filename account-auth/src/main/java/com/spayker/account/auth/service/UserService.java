package com.spayker.account.auth.service;

import com.spayker.account.auth.domain.User;

public interface UserService {

	User create(User user);

	User saveChanges(User update);

}
