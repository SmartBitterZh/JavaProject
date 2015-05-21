package com.bitter.action.user;

import com.bitter.service.user.IUserService;
import com.bitter.service.user.UserServiceImpl;



public class UserAction {
	public boolean login(String user, String password) {
		IUserService _service = new UserServiceImpl();		
		return _service.login(user, password);
	}
}
