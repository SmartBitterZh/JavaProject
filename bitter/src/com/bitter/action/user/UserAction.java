package com.bitter.action.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bitter.service.user.IUserService;
import com.bitter.service.user.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;

	private String info = "struts";

	public String add() {
		info = "Add user information.";
		return "add";
	}

	public String update() {
		info = "Update user information.";
		return "update";
	}

	public String login() {
		IUserService _service = new UserServiceImpl();
		// _service.login(userName, password);
		// userName.endsWith("bitter")&& password.endsWith("123456");
		HttpServletRequest reqeust = ServletActionContext.getRequest();
		userName = reqeust.getParameter("username");
		password = reqeust.getParameter("password");
		boolean _login = _service.login(userName, password);
		if (_login)
			return SUCCESS;
		else
			return INPUT;
	}

	public String execute() {
		info = "The first struts2 application.";
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
