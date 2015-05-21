package com.bitter.util;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactoty {
	private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";
	private static final ThreadLocal<Session> m_threadLocal = new ThreadLocal<Session>();
	private static Configuration m_configuration;
	private static SessionFactory m_sessionFactory;
	private static String m_configFile = "";
	private static ServiceRegistry m_serviceRegistry;
	static {
		getConfigFile();
		File _file = new File(m_configFile);
		if (_file.exists()) {
			m_configuration = new Configuration().configure(_file);
		} else {
			m_configuration = new Configuration().configure();
		}
		rebuildSessionFactory();
	}

	private HibernateSessionFactoty() {
	}

	public static Session getSession() {
		Session _session = m_threadLocal.get();
		if (_session == null || _session.isOpen()) {
			if (m_sessionFactory == null) {
				rebuildSessionFactory();
			}
			_session = (m_sessionFactory != null) ? m_sessionFactory
					.openSession() : null;
			m_threadLocal.set(_session);
		}
		return _session;
	}

	public static void rebuildSessionFactory() {
		try {
			m_serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(m_configuration.getProperties()).build();
			m_sessionFactory = m_configuration
					.buildSessionFactory(m_serviceRegistry);

		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void closeSession() {
		Session _session = m_threadLocal.get();
		m_threadLocal.set(null);
		if (_session != null)
			_session.close();
	}

	public static SessionFactory getSessionFactory() {
		return m_sessionFactory;
	}

	public static String getConfigFile() {
		if (m_configFile.isEmpty()) {
			String _path = HibernateSessionFactoty.class.getResource("/")
					.getPath();
			m_configFile = _path + "com/bitter/files/" + CONFIG_FILE_LOCATION;
		}
		return m_configFile;
	}

	public static void setConfigFile(String configFile) {
		HibernateSessionFactoty.m_configFile = configFile;
		m_sessionFactory = null;
	}

	public static Configuration getConfiguration() {
		return m_configuration;
	}
}
