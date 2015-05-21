package com.bitter.data;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DataColumnCollection extends AbstractList<DataColumn> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DataColumn> list;

	private DataColumnCollection() {
		list = new ArrayList<DataColumn>();
	}

	@Override
	public DataColumn get(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public DataColumn set(int index, DataColumn element) {
		DataColumn[] _columns = new DataColumn[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if (i == index)
				_columns[i] = element;
			else
				_columns[i] = list.get(i);
		}
		list = new ArrayList<DataColumn>();
		for (DataColumn dataColumn : _columns) {
			list.add(dataColumn);
		}
		return element;
	}

	@Override
	public void add(int index, DataColumn element) {
		DataColumn[] _columns = new DataColumn[list.size() + 1];
		for (int i = 0; i < list.size() - 1; i++) {
			if (i == index)
				_columns[i] = element;
			else if (i < index)
				_columns[i] = list.get(i);
			else
				_columns[i] = list.get(i - 1);
		}
		list = new ArrayList<DataColumn>();
		for (DataColumn dataColumn : _columns) {
			list.add(dataColumn);
		}
	}

	/**
	 * 
	 * @param columns
	 */
	public void AddRange(DataColumn[] columns) {
		for (DataColumn dataColumn : columns)
			this.add(dataColumn);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public DataColumn add(String name) {
		return this.add(name, String.class.getSimpleName(), null);
	}

	/**
	 * 
	 * @param name
	 * @param dataType
	 * @return
	 */
	public DataColumn add(String name, String dataType) {
		return this.add(name, dataType, null);
	}

	/**
	 * 
	 * @param name
	 * @param dataType
	 * @param caption
	 * @return
	 */
	public DataColumn add(String name, String dataType, String caption) {
		DataColumn column = new DataColumn(name, dataType, caption);
		list.add(column);
		return column;
	}

	@Override
	public DataColumn remove(int index) {
		DataColumn[] _columns = new DataColumn[list.size() - 1];
		DataColumn _removeColumn = null;
		for (int i = 0; i < list.size() - 1; i++) {
			if (i < index)
				_columns[i] = list.get(i);
			else if (i == index)
				_removeColumn = list.get(i);
			else
				_columns[i] = list.get(i + 1);
		}
		list = new ArrayList<DataColumn>();
		for (DataColumn dataColumn : _columns) {
			list.add(dataColumn);
		}
		return _removeColumn;
	}
}
