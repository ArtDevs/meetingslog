package org.artdevs.meetingslog.facades.populator;

import org.artdevs.meetingslog.core.model.UserModel;
import org.artdevs.meetingslog.facades.data.UserData;

/**
 * Created by Artem L.V. on 2/28/15.
 */
public interface UserPopulator {

	public UserData populateDataObject(UserModel userModel);
	public UserModel populateModelObject(UserData userData);

}
