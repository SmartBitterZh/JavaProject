package com.bitter.test;

import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Wel extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.setContentType("text/html;charset=gbk");
//			HttpSession seesion = req.getSession();
//			String user = seesion.getAttribute("username").toString();
			String user = req.getAttribute("username").toString();
			PrintWriter pw = res.getWriter();
			pw.println(MessageFormat.format("Welcoome {0}, Login successfull!", user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		this.doGet(req, res);
	}
}
