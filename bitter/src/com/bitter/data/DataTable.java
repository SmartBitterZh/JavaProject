package com.bitter.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.bitter.data.interfaces.IDataRowCollection;
import com.bitter.data.interfaces.IDataTable;
import com.bitter.eventhandler.IEventHandler;

public class DataTable implements IDataTable, Serializable, Cloneable, List {

	/**
	 * ---------------------- private properties
	 * --------------------------------
	 */
	private static final long serialVersionUID = 1L;
	private int m_tableIndex;
	private String m_tableName;
	private IEventHandler listChanged;
	private DataRowChangedHandler rowChanged;

	// ---------------------- constructor ------------------------------

	public DataTable() {
		this(false);
	}

	public DataTable(boolean spoolToDisk) {

	}

	/**
	 * ---------------------- public methods -----------------------------
	 */

	public void addListChanged(IEventHandler listChanged) {
		this.listChanged = listChanged;
	}

	public void removeListChanged() {
		listChanged = null;
	}

	public void addRowChanged(DataRowChangedHandler rowChanged) {
		this.rowChanged = rowChanged;
	}

	public void removeRowChanged() {
		rowChanged = null;
	}
	
	public DataRowState getRowState(Object row){
		
		return DataRowState.Current;
	}
	
	public Object[] getOriginalRow(Object[] row){
		
		return row;
	}

	public void acceptChanges(Object[] row){
		
	}
	
	public void rejectChanges(Object[] row) {

	}
	/**
	 * ---------------------- private methods -----------------------------
	 */

	Iterator getDeletedRows() {
		return null;
	}

	private Object[] convertRow(Object[] rows) {
		Object[] _ret = new Object[rows.length];
		/*
		 * for (int i = 0; i < rows.length; i++) { _ret[i] =
		 * convertValue(columns[i], rows[i]) }
		 */
		return _ret;
	}

	private static Object convertValue(DataColumn column, Object value) {
		return null;
	}

	/**
	 * ---------------------- Override -----------------------------
	 */
	@Override
	public DataSet getDataSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return m_tableName;
	}

	@Override
	public void setTableName(String tableName) {
		// TODO Auto-generated method stub
		m_tableName = tableName;
	}

	@Override
	public void rejectChanges() {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptChanges() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasChanges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataRow addRowEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataRow addRow(Object[] data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beginLoadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void endLoadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public DataColumnCollection getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataRowCollection getRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addRowAccepted(Object[] data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Object> getExtendedProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTableIndex() {
		// TODO Auto-generated method stub
		return m_tableIndex;
	}

	@Override
	public void setTableIndex(int index) {
		// TODO Auto-generated method stub
		m_tableIndex = index;
	}

	@Override
	public IDataTable cloneTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataTable copyTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(int index, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, Object element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	static class ChangedData {
		private static boolean added;
		private Object[] originalData;
		private Object[] data;

		public static boolean isAdded() {
			return added;
		}

		public static void setAdded(boolean added) {
			ChangedData.added = added;
		}

		public Object[] getOriginalData() {
			return originalData;
		}

		public void setOriginalData(Object[] originalData) {
			this.originalData = originalData;
		}

		public Object[] getData() {
			return data;
		}

		public void setData(Object[] data) {
			this.data = data;
		}

		public ChangedData(Object[] originalData, Object[] data) {
			this(originalData, data, false);
		}

		public ChangedData(Object[] originalData, Object[] data, boolean added) {
			this.originalData = originalData;
			this.data = data;
			this.added = added;
		}
	}

	final class TableComparer implements Comparator<Object[]> {

		private int column;
		private ListSortDirection direction;

		public TableComparer(int column, ListSortDirection direction) {
			this.column = column;
			this.direction = direction;
		}

		@Override
		public int compare(Object[] o1, Object[] o2) {
			// TODO Auto-generated method stub
			if (direction == ListSortDirection.Ascending)
				return ((Comparable) o2[column]).compareTo(o1[column]);
			return ((Comparable) o1[column]).compareTo(o2[column]);
		}
	}

	final class TableEnumerator implements Iterator {

		private DataTable table;
		private Iterator listEnum;

		// private RowCollection.RowEnumerator

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	final class OriginalRowwsCollection extends LinkedList<ChangedData> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private DataTable table;

		public OriginalRowwsCollection(DataTable table) {
			this.table = table;
		}
	}
	

}
