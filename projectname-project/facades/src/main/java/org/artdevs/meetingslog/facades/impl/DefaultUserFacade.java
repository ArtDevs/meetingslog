package org.artdevs.meetingslog.facades.impl;

import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.facades.UserFacade;
import org.artdevs.meetingslog.facades.data.UserData;
import org.artdevs.meetingslog.facades.populator.UserPopulator;
import org.artdevs.meetingslog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Artem L.V. on 2/28/15.
 */
@Component("defaultUserFacade")
public class DefaultUserFacade implements UserFacade {

	@Autowired
	UserService userService;

	@Autowired
	UserPopulator userPopulator;

	@Override
	public boolean insertUser(UserData user) throws RuntimeException {
		return userService.insertUser(userPopulator.populateModelObject(user));
	}

	@Override
	public boolean changeUserInfo(UserData user) throws RuntimeException {
		return false;
	}

	@Override
	public boolean deleteUser(UserData user) throws RuntimeException {
		return false;
	}

	@Override
	public boolean checkPassword(String login, String password) {
		return false;
	}

	@Override
	public UserData findUserByLogin(String login) {

		UserModel userModel = userService.findUserByLogin(login);

		return userPopulator.populateDataObject(userModel);
	}

	@Override
	public boolean verify(UserData data) {
		return userService.findUserByLogin(data.getLogin())==null;
	}
}
