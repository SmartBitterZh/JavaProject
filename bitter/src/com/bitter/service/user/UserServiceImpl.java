package com.bitter.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bitter.bean.user.User;
import com.bitter.service.base.AbstractDAO;

public class UserServiceImpl extends AbstractDAO<User> implements IUserService {

	@Override
	public boolean login(String user, String password) {
		Map<String, Object> _params = new HashMap<String, Object>();
		_params.put("name", user);
		_params.put("password", password);
		List<User> _list = this.findByProperty(_params);
		return _list.isEmpty();
	}

}
