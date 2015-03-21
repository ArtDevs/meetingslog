package org.artdevs.meetingslog.facades.populator.impl;

import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.facades.data.UserData;
import org.artdevs.meetingslog.facades.populator.UserPopulator;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * Created by Artem L.V. on 2/28/15.
 */
@Component("defaultUserPopulator")
public class DefaultUserPopulator implements UserPopulator {

	@Override
	public UserModel populateModelObject (UserData data) {

		UserModel model = new UserModel();

		model.setLogin(data.getLogin());
		model.setLogin(data.getPassword());
		model.setLogin(data.getFirstName());
		model.setLogin(data.getSecondName());
		model.setLogin(data.getEmail());
		model.setLogin(data.getAddress());
		model.setLogin(data.getPhoneNumber1());
		model.setLogin(data.getPhoneNumber2());
		model.setLogin(data.getComment());

		return model;
	}

	@Override
	public UserData populateDataObject(UserModel userModel) {

		UserData userData = new UserData();

		userData.setLogin(userModel.getLogin());
		userData.setLogin(userModel.getPassword());
		userData.setLogin(userModel.getFirstName());
		userData.setLogin(userModel.getSecondName());
		userData.setLogin(userModel.getEmail());
		userData.setLogin(userModel.getAddress());
		userData.setLogin(userModel.getPhoneNumber1());
		userData.setLogin(userModel.getPhoneNumber2());
		userData.setLogin(userModel.getComment());

		return userData;
	}
}
