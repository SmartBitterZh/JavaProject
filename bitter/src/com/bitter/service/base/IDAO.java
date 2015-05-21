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
	public void save(T obj);

	/**
	 * update object
	 * 
	 * @param obj
	 */
	public void update(T obj);

	/**
	 * 
	 * @param obj
	 */
	public void delete(T obj);

	/**
	 * 
	 * @param id
	 */
	public void delete(Serializable id);

	/**
	 * 
	 * @param ids
	 */
	public void delete(Serializable[] ids);

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
