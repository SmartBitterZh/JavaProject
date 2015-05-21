package com.bitter.test;

import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitter.Data.DataColumn;
import com.bitter.action.user.UserAction;
import com.bitter.util.JdbcUtils;

public class LoginCL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			String user = req.getParameter("username");
			String pwd = req.getParameter("passwd");
			// check
			// user.equals("bitter") && pwd.equals("bitter")
			if (checkLogin(user, pwd)) {
				// page to welcome

				// HttpSession seesion = req.getSession();
				// seesion.setAttribute("username", "bitter");
				// res.sendRedirect("wel");

				req.setAttribute("username", "bitter");
				ServletContext _context = this.getServletContext();
				RequestDispatcher _rd = _context.getRequestDispatcher("/wel");
				_rd.forward(req, res);
			} else {
				// page to login
				req.setAttribute("message", "login fail!");
				ServletContext _context = this.getServletContext();
				RequestDispatcher _rd = _context.getRequestDispatcher("/login");
				_rd.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		this.doGet(req, res);
	}
	
	private boolean checkLogin(String user, String password) {
		UserAction _action = new UserAction();
		return _action.login(user, password);
	}
	
	private boolean checkLoginJDBC(String user, String password) {
		Map<Integer, Set<DataColumn>> _dataSet = JdbcUtils.executeQuery(
				"select * from [User] where Name = ? and Password = ?",
				new Object[] { user, password });

		return !_dataSet.isEmpty();
	}
}
