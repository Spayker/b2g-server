package com.spayker.consumer.auth.service;

import com.spayker.consumer.auth.domain.User;

/**
 *  Service layer interface to provided API for work with User entity.
 **/
public interface UserService {

	/**
	 *  Creates a new user in db.
	 *  @param user - object to be persisted
	 **/
	User create(User user);

	/**
	 *  Used to update already created user entity with new portion of data
	 *  @param update - object to be updated
	 **/
	User saveChanges(User update);

}
