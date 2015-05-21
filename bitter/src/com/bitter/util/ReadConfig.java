package com.bitter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	// private static String JDBC_CONFIG_FILE_LOCATION =
	// "/bitter/WEB-INF/classes/com/bitter/files/jdbc.properties";
	private static String JDBC_CONFIG_FILE_NAME = "jdbc.properties";
	private static String m_configFile = "";

	static Properties m_pros = null;
	static {
		String _path = ReadConfig.class.getResource("/").getPath();
		m_configFile = _path + "com/bitter/files/" + JDBC_CONFIG_FILE_NAME;
		loadProperties();
	}

	public static String getProperty(String key) {
		return m_pros.getProperty(key);
	}

	public static String getDriverName() {
		return m_pros.getProperty("drivername");
	}

	public static String getUrl() {
		return m_pros.getProperty("url");
	}

	public static String getUser() {
		return m_pros.getProperty("user");
	}

	public static String getPassword() {
		return m_pros.getProperty("password");
	}

	public static void setJDBCConfigFile() {
		ReadConfig.setJDBCConfigFile(getJDBCConfigFile());
	}

	public static void setJDBCConfigFile(String m_configFile) {
		ReadConfig.m_configFile = m_configFile;
		loadProperties();
	}

	public static String getJDBCConfigFile() {
		if (m_configFile.isEmpty()) {
			String _path = ReadConfig.class.getResource("/").getPath();
			m_configFile = _path + "com/bitter/files/" + JDBC_CONFIG_FILE_NAME;
		}
		return m_configFile;
	}

	private static void loadProperties() {
		m_pros = new Properties();
		try {
			if (!m_configFile.isEmpty()) {
				File _file = new File(m_configFile);
				if (_file.exists()) {
					InputStream _in = new FileInputStream(_file);
					m_pros.load(_in);
					_in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
