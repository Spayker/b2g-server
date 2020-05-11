package com.spayker.consumer.auth.service;

import com.spayker.consumer.auth.domain.User;

public interface UserService {

	User create(User user);

	User saveChanges(User update);

}
