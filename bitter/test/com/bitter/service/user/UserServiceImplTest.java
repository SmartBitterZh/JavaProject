package com.bitter.service.user;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceImplTest {
	private IUserService m_service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("globalInit invoked!");
	}

	@AfterClass
	public static void setUpAfterClass() throws Exception {
		System.out.println("globalDestroy invoked!");
	}

	@Before
	public void setUp() throws Exception {
		m_service = new UserServiceImpl();
		System.out.println("before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("destroy");
	}

	@Test
	public void login() {
		System.out.println("login");
		String user = "bitter";
		String password = "123456";
		Assert.assertTrue(m_service.login(user, password));
	}

}
