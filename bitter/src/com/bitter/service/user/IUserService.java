package com.bitter.service.user;

import com.bitter.bean.user.User;
import com.bitter.service.base.IDAO;

public interface IUserService extends IDAO<User> {
	boolean login(String user,String password);
}
