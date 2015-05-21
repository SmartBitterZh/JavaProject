package com.bitter.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Hello implements Servlet {

	/**
	 * Method init init servlet, like counstro first load servlet will run
	 * 
	 * @param parm1
	 * 
	 @throws ServletException
	 * 
	 */

	public void init(ServletConfig parm1) throws ServletException {
		System.out.println("init ");
	}

	/**
	 * Method getServletConfig get servlet configuration file
	 * 
	 * @return
	 * 
	 */
	public ServletConfig getServletConfig() {
		return null;
	}

	/**
	 * Method service deal with the action
	 * 
	 * @param parm1
	 * @param parm2
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
	public void service(ServletRequest parm1, ServletResponse res)
			throws ServletException, IOException {
		System.out.println("service it");
		PrintWriter pw = res.getWriter();
		pw.println("Hello World!");

	}

	/**
	 * Method getServletInfo
	 * 
	 * 
	 * @return
	 * 
	 */
	public String getServletInfo() {
		return "";
	}

	/**
	 * Method destroy
	 * 
	 * 
	 */
	public void destroy() {
		System.out.println("destroy!");
	}

}
