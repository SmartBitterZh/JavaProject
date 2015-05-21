package com.bitter.test;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			// for chinese
			res.setContentType("text/html;charset=gbk");
			PrintWriter pw = res.getWriter();

			Object _msg = req.getAttribute("message");
			// return login page
			pw.println("<html>");
			pw.println("<body>");
			pw.println("<h1>Login Page</h1>");
			if (_msg!=null && !_msg.toString().isEmpty())
				pw.println("<h2>" + _msg.toString() + "</h2>");
			pw.println("<form action=logincl method=post>");
			pw.println("User:<input type=text name=username><br>");
			pw.println("Password:<input type=password name=passwd><br>");
			pw.println("<input type=submit value=Login><br>");
			pw.println("</form>");
			pw.println("<body/>");
			pw.println("<html/>");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		this.doGet(req, res);
	}

}
