package com.bitter.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author bzhang1
 * 
 */
public class GenericsUtils {
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static Class getGenericClass(Class clazz) {
		return getGenericClass(clazz, 0);
	}

	/**
	 * 
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class getGenericClass(Class clazz, int index) {
		Type _getType = clazz.getGenericSuperclass();
		if (_getType instanceof ParameterizedType) {
			Type[] _params = ((ParameterizedType) _getType)
					.getActualTypeArguments();
			if (_params != null && (_params.length >= index - 1)) {
				return (Class) _params[index];
			}
		}
		return null;
	}
}
