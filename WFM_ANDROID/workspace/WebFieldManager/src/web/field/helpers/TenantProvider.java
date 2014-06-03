package web.field.helpers;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import web.field.SharedPreferencesKeys;
import web.field.WebFieldApplication;
import web.field.db.IDaoProvider;
import web.field.model.entity.User;
import android.content.SharedPreferences;

public class TenantProvider implements ITenantProvider{
	
	private IDaoProvider daoProvider;
	
	public TenantProvider(IDaoProvider daoProvider){
		this.daoProvider = daoProvider;
	}

	@Override
	public int getTenant() {
		SharedPreferences preferences = WebFieldApplication
				.getSharedPreferences();
		String token = preferences.getString(SharedPreferencesKeys.user_token,
				null);

		if (token != null) {
			User user = getUser(token);
			if (user != null) {
				return user.getTenantId();
			} else {
				throw new IllegalStateException("Can't find user for token "
						+ token);
			}
		} else {
			throw new IllegalStateException(
					"Can't find user token in shared preferences");
		}

	}
	
	private User getUser(String token) {
		try {
			Dao<User, Integer> dao = daoProvider.getUserDao();

			List<User> userResult = dao.queryBuilder().where()
					.eq("Token", token.toUpperCase()).query();

			for (User user : userResult) {
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
