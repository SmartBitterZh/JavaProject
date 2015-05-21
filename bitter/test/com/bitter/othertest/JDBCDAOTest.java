package com.bitter.othertest;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.bitter.Data.DataColumn;
import com.bitter.util.JdbcUtils;

public class JDBCDAOTest {
	
	@Test
	public void loginJDBC() {
		String user = "bitter";
		String password = "123456";
		Map<Integer, Set<DataColumn>> _dataSet = JdbcUtils.executeQuery(
				"select * from [User] where Name = ? and Password = ?",
				new Object[] { user, password });
		System.out.println(_dataSet.isEmpty());
//		assertTrue(false, _dataSet.isEmpty());
	}

}
