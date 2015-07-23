package com.bitter.data;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bitter.eventhandler.IEventHandler;

public class DataColumnCollection extends AbstractList<DataColumn> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DataColumn> list;
	private IEventHandler listChanged;
	private Object syncRoot;
	
	private DataColumnCollection() {
		list = new ArrayList<DataColumn>();
	}

	public Object getSyncRoot() {
		return syncRoot;
	}

	public void setSyncRoot(Object syncRoot) {
		this.syncRoot = syncRoot;
	}

	public void addListChanged(IEventHandler listChanged) {
		this.listChanged = listChanged;
	}

	public void removeListChanged() {
		listChanged = null;
	}

	public int indexOf(String name) {
		synchronized (syncRoot) {
			int i = 0;
			for (DataColumn _column : list) {
				if (_column.getColumnName().equals(name))
					return i;
				i++;
			}
		}
		return -1;
	}

	public int indexOf(DataColumn column) {
		synchronized (syncRoot) {
			return list.indexOf(column);
		}
	}

	public boolean contains(String name) {
		synchronized (syncRoot) {
			for (DataColumn _column : list) {
				if (_column.getColumnName().equals(name))
					return true;
			}
		}
		return false;
	}

	public void insert(int index, DataColumn column) {
		synchronized (syncRoot) {
			dontSerialize();
			this.list.add(index, column);
			if (listChanged != null)
				listChanged.invoked(this, new ListChangedEventArgs(
						ListChangedType.ItemAdded, list.size() - 1));
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		synchronized (syncRoot) {
			return list.size();
		}
	}

	public DataColumn get(String name) {
		// TODO Auto-generated method stub
		synchronized (syncRoot) {
			for (DataColumn _column : list) {
				if (_column.getColumnName().equals(name))
					return _column;
			}
		}
		return null;
	}

	@Override
	public DataColumn get(int index) {
		// TODO Auto-generated method stub
		synchronized (syncRoot) {
			return list.get(index);
		}
	}

	@Override
	public DataColumn set(int index, DataColumn element) {
		synchronized (syncRoot) {
			this.list.add(index, element);
			if (listChanged != null)
				listChanged.invoked(this, new ListChangedEventArgs(
						ListChangedType.ItemAdded, index));
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

	public void addColumn(DataColumn column) {
		synchronized (syncRoot) {
			dontSerialize();
			this.list.add(column);
			if (listChanged != null)
				listChanged.invoked(this, new ListChangedEventArgs(
						ListChangedType.ItemAdded, list.size() - 1));
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
		synchronized (syncRoot) {
			dontSerialize();
			list.add(column);
			if (listChanged != null)
				listChanged.invoked(this, new ListChangedEventArgs(
						ListChangedType.ItemAdded, list.size() - 1));
		}
		return column;
	}

	public void copyTo(ArrayList<DataColumn> columnArray, int arrayIndex) {
		synchronized (syncRoot) {
			this.list
					.addAll(arrayIndex, new ArrayList<DataColumn>(columnArray));
		}
	}

	public void contains(DataColumn column) {
		synchronized (syncRoot) {
			this.list.contains(column);
		}
	}

	public void clear() {
		synchronized (syncRoot) {
			for (DataColumn _clolumn : list) {
				_clolumn.removePropertyChanged();
			}
		}
	}

	public void removeAt(int index) {
		synchronized (syncRoot) {
			dontSerialize();
			DataColumn _column = this.list.get(index);
			_column.removePropertyChanged();
			this.list.remove(index);
			if (listChanged != null)
				listChanged.invoked(this, new ListChangedEventArgs(
						ListChangedType.ItemDeleted, index));
		}
	}

	public boolean remove(DataColumn column) {
		synchronized (syncRoot) {
			int _ind = this.indexOf(column);
			if (_ind >= 0) {
				this.removeAt(_ind);
				return true;
			}
		}
		return false;
	}

	public boolean remove(String name) {
		synchronized (syncRoot) {
			int _ind = this.indexOf(name);
			if (_ind >= 0) {
				this.removeAt(_ind);
				return true;
			}
		}
		return false;
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

	public boolean isReadOnly(){
		return false;
	}
	
	public DataColumn[] toArray(){
		synchronized (syncRoot) {
			return (DataColumn[]) this.list.toArray();
		}
	}
	
	public Iterator<DataColumn> getIterator(){
		return this.list.iterator();
	}
	
	private void dontSerialize() {
		
	}
}

final class DataColumnSerializable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String name;
	static String dataType;
	static String caption;
	static boolean allowDBNull;
	static Object defaultValue;
	static boolean unique;
	static int maxLength;
	static Hashtable extendedProperties;

	public DataColumnSerializable(DataColumn column) {
		this.name = column.getColumnName();
		this.dataType = column.getDataType();
		this.caption = column.getCaption();
		this.allowDBNull = column.isAllowDBNull();
		this.defaultValue = column.getDefaultValue();
		this.unique = column.isUnique();
		Hashtable _table = column.getExtendedProperties();
		if (_table.size() > 0) {
			extendedProperties = new Hashtable();
			Set _keySet = _table.keySet();
			for (Object _key : _keySet) {
				extendedProperties.put(_key, _table.get(_key));
			}
		}
		this.maxLength = column.getMaxLength();
	}

	public DataColumn getDataColumn() {
		DataColumn _column = new DataColumn(name, dataType);
		_column.setCaption(caption);
		_column.setAllowDBNull(allowDBNull);
		_column.setDefaultValue(defaultValue);
		_column.setUnique(unique);
		_column.setMaxLength(maxLength);
		if (extendedProperties != null) {
			Set _keySet = this.extendedProperties.keySet();
			Hashtable _table = _column.getExtendedProperties();
			for (Object _key : _keySet) {
				_table.put(_key, this.extendedProperties.get(_key));
			}
		}
		return _column;
	}
}
