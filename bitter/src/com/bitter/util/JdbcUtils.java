package com.bitter.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.bitter.data.DataColumn;

public class JdbcUtils {
	static {
		try {
			ReadConfig.setJDBCConfigFile();
			Class.forName(ReadConfig.getDriverName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		String _url = ReadConfig.getUrl();
		String _user = ReadConfig.getUser();
		String _pwd = ReadConfig.getPassword();
		Connection _conn = null;
		try {
			_conn = DriverManager.getConnection(_url, _user, _pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _conn;
	}

	public static void release(Object obj) {
		if (obj == null)
			return;
		if (obj instanceof ResultSet) {
			try {
				((ResultSet) obj).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Statement) {
			try {
				((Statement) obj).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Connection) {
			Connection _conn = ((Connection) obj);
			try {
				if (!_conn.isClosed())
					_conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void release(ResultSet set, Statement stat, Connection conn) {
		release(set);
		release(stat);
		release(conn);
	}

	protected static void setQueryParams(PreparedStatement statement,
			Object[] params) throws SQLException {
		if (params != null && params.length > 0) {
			int i = 1;
			for (Object param : params) {
				statement.setString(i, param.toString());
				i++;
			}
		}
	}
	
	public static boolean execute(String sql, Object[] params) {
		Connection _conn = JdbcUtils.getConnection();
		PreparedStatement _ps = null;
		try {
			_conn.setAutoCommit(false);
			_ps = _conn.prepareStatement(sql);
			setQueryParams(_ps, params);
			boolean _result = _ps.execute();
			_conn.commit();
			return _result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, _ps, _conn);
		}
		return false;
	}

	public static Map<Integer, Set<DataColumn>> executeQuery(String sql,
			Object[] params) {
		Connection _conn = JdbcUtils.getConnection();
		PreparedStatement _ps = null;
		ResultSet _resultSet = null;
		Set<DataColumn> _columns = null;
		Map<Integer, Set<DataColumn>> _dataSet = new HashMap<Integer, Set<DataColumn>>();
		try {
			_conn.setAutoCommit(false);
			_ps = _conn.prepareStatement(sql);
			setQueryParams(_ps, params);
			_resultSet = _ps.executeQuery();
			_conn.commit();
			ResultSetMetaData _rsmd = _resultSet.getMetaData();
			int _columnCount = _rsmd.getColumnCount();
			DataColumn _column = null;
			int i = 0;
			if (_resultSet.next()) {
				_columns = new HashSet<DataColumn>();
				for (int j = 1; j <= _columnCount; j++) {
					_column = new DataColumn(_rsmd, j);
					_column.setDefaultValue(_resultSet.getObject(j));
					_columns.add(_column);
				}
				_dataSet.put(i, _columns);
				i++;
			}
			return _dataSet;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(_resultSet, _ps, _conn);
		}
		return _dataSet;
	}

	public static int executeUpdate(String sql, Object[] params) {
		Connection _conn = JdbcUtils.getConnection();
		PreparedStatement _ps = null;
		int _result = -1;
		try {
			_conn.setAutoCommit(false);
			_ps = _conn.prepareStatement(sql);
			setQueryParams(_ps, params);
			_result = _ps.executeUpdate();
			_conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, _ps, _conn);
		}
		return _result;
	}
}
