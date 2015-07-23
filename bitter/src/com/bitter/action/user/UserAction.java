package com.bitter.action.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bitter.bean.user.User;
import com.bitter.service.user.IUserService;
import com.bitter.service.user.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IUserService _service = new UserServiceImpl();
	private User user;

	private String info = "struts";

	public String add() {
		info = "Add user information.";

		HttpServletRequest reqeust = ServletActionContext.getRequest();
		user = new User();
		user.setName(reqeust.getParameter("username"));
		user.setPassword(reqeust.getParameter("password"));
		_service.save(user);
		return "add";
	}

	public String update() {
		info = "Update user information.";

		HttpServletRequest reqeust = ServletActionContext.getRequest();
		user = new User();
		user.setName(reqeust.getParameter("username"));
		user.setPassword(reqeust.getParameter("password"));
		_service.update(user);
		return "update";
	}

	public String login() {
		// _service.login(userName, password);
		// userName.endsWith("bitter")&& password.endsWith("123456");
		HttpServletRequest reqeust = ServletActionContext.getRequest();
		user = new User();
		user.setName(reqeust.getParameter("username"));
		user.setPassword(reqeust.getParameter("password"));
		boolean _login = _service.login(user.getName(), user.getPassword());
		if (_login)
			return SUCCESS;
		else
			return INPUT;
	}

	public String execute() {
		info = "The first struts2 application.";
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
