package com.bitter.service.base;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bitter.bean.user.User;
import com.bitter.util.GenericsUtils;
import com.bitter.util.HibernateSessionFactoty;
import com.bitter.util.JdbcUtils;

public abstract class AbstractJDBC<T> implements IDAO<T> {

	@SuppressWarnings("unchecked")
	protected Class<T> _clazz = GenericsUtils.getGenericClass(getClass());

	@Override
	public void save(T obj) {

		Connection conn = JdbcUtils.getConnection();
		PreparedStatement ps = null;
		User _user = new User(1, "bitter", "bitter");
		String sql = "insert into User values (?,?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, _user.getId());
			ps.setString(2, _user.getName().trim());
			ps.setString(3, _user.getPassword().trim());
			int i = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.release(null, ps, conn);
		}

		if (obj != null) {
			Session _session = HibernateSessionFactoty.getSession();
			Transaction _tx = _session.beginTransaction();
			_session.save(obj);
			_tx.commit();
			_session.close();
		}
	}

	@Override
	public void update(T obj) {
		if (obj != null) {
		}
	}

	@Override
	public void delete(T obj) {
		if (obj != null) {
		}
	}

	@Override
	public void delete(Serializable id) {
		if (id != null)
			delete(new Serializable[] { id });
	}

	@Override
	public void delete(Serializable[] ids) {
		if (ids != null && ids.length > 0) {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Serializable id) {
		T _obj = null;
		if (id != null) {
		}
		return _obj;
	}

	@Override
	public List<T> findByProperty(Map<String, Object> property) {
		List<T> _list = null;
		return _list;
	}

	@Override
	public List<T> findByProperty(String property, String value) {
		// TODO Auto-generated method stub
		List<T> _list = null;
		return _list;
	}

	protected void setQueryParams(Query query, Object[] params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	protected String buildOrderby(Map<String, String> orderby) {
		StringBuffer sb = new StringBuffer();
		if (orderby != null && orderby.size() > 0) {
			sb.append(" order by ");
			for (String key : orderby.keySet()) {
				sb.append("o.").append(key).append(" ")
						.append(orderby.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	protected String getEntityName() {
		return _clazz.getSimpleName();
	}
}
