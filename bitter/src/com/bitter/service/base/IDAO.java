package com.bitter.service.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author bzhang1
 * 
 * @param <T>
 */
public interface IDAO<T> {
	/**
	 * save object
	 * 
	 * @param obj
	 */
	public boolean save(T obj);

	/**
	 * update object
	 * 
	 * @param obj
	 */
	public boolean update(T obj);

	/**
	 * 
	 * @param obj
	 */
	public boolean delete(T obj);

	/**
	 * 
	 * @param id
	 */
	public boolean delete(Serializable id);

	/**
	 * 
	 * @param ids
	 */
	public boolean delete(Serializable[] ids);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public T find(Serializable id);
	
	/**
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByProperty(String property, String value);

	/**
	 * 
	 * @param property
	 * @return
	 */
	public List<T> findByProperty(Map<String, Object> property);

}
