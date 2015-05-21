package com.bitter.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bitter.util.GenericsUtils;
import com.bitter.util.HibernateSessionFactoty;

/**
 * 
 * @author bzhang1
 * 
 * @param <T>
 */
public abstract class AbstractDAO<T> implements IDAO<T> {
	@SuppressWarnings("unchecked")
	protected Class<T> _clazz = GenericsUtils.getGenericClass(getClass());

	@Override
	public void save(T obj) {
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
			Session _session = HibernateSessionFactoty.getSession();
			Transaction _tx = _session.beginTransaction();
			_session.update(obj);
			_tx.commit();
			_session.close();
		}
	}

	@Override
	public void delete(T obj) {
		if (obj != null) {
			Session _session = HibernateSessionFactoty.getSession();
			Transaction _tx = _session.beginTransaction();
			_session.delete(obj);
			_tx.commit();
			_session.close();
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
			Session _session = HibernateSessionFactoty.getSession();
			Transaction _tx = null;
			for (Serializable id : ids) {
				_tx = _session.beginTransaction();
				_session.delete(_session.get(_clazz, id));
				_tx.commit();
			}
			_session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Serializable id) {
		T _obj = null;
		if (id != null) {
			Session _session = HibernateSessionFactoty.getSession();
			_obj = (T) _session.get(_clazz, id);
			_session.close();
		}
		return _obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Session _session = HibernateSessionFactoty.getSession();
		Criteria _criteria = _session.createCriteria(_clazz);
		List<T> _list = _criteria.list();
		return _list;
	}

	@Override
	public List<T> findByProperty(Map<String, Object> property) {
		// TODO Auto-generated method stub
		Session _session = HibernateSessionFactoty.getSession();
		Criteria _criteria = _session.createCriteria(_clazz);
		for (Entry<String, Object> entry : property.entrySet()) {
			_criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		// for (String key : property.keySet()) {
		// _criteria.add(Restrictions.eq(key, property.get(key)));
		// }
		List<T> _list = _criteria.list();
		return _list;
	}

	@Override
	public List<T> findByProperty(String property, String value) {
		// TODO Auto-generated method stub
		Session _session = HibernateSessionFactoty.getSession();
		Criteria _criteria = _session.createCriteria(_clazz);
		List<T> _list = _criteria.add(Restrictions.eq(property, value)).list();
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
