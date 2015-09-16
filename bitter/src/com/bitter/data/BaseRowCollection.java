package com.bitter.data;

import java.util.Iterator;
import java.util.List;

import com.bitter.data.interfaces.IDataRow;
import com.bitter.data.interfaces.IDataRowCollection;

public abstract class BaseRowCollection implements IDataRowCollection {

	final class RowEnumerator implements Iterator<DataRow> {

		private Iterator tableEnumerator;

		public RowEnumerator(Iterator enumerator) {
			this.tableEnumerator = enumerator;
		}

		private Object _current;

		public boolean moveNext() {
			_current = tableEnumerator.next();
			return _current != null;
		}

		public Object current() {
			return _current;
		}

		DataRow currentDataRow() {
			return (DataRow) _current;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return tableEnumerator.hasNext();
		}

		@Override
		public DataRow next() {
			// TODO Auto-generated method stub
			if (moveNext())
				return currentDataRow();
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

	}

	private List m_list;

	protected BaseRowCollection(List list) {
		m_list = list;
	}

	protected abstract Iterator GetTableEnumerator();

	public List getList() {
		return m_list;
	}

	@Override
	public boolean Contains(IDataRow row) {
		// TODO Auto-generated method stub
		return m_list.contains(row);
	}

	@Override
	public void Clear() {
		// TODO Auto-generated method stub
		m_list.clear();
	}

	@Override
	public int IndexOf(IDataRow row) {
		// TODO Auto-generated method stub
		return m_list.indexOf(row);
	}

	@Override
	public void Insert(int index, Object[] row) {
		// TODO Auto-generated method stub
		m_list.add(index, row);
	}

	@Override
	public void Remove(IDataRow row) {
		// TODO Auto-generated method stub
		m_list.remove(row);
	}

	@Override
	public void RemoveAt(int index) {
		// TODO Auto-generated method stub
		m_list.remove(index);
	}

	@Override
	public DataRow get(int index) {
		// TODO Auto-generated method stub
		return (DataRow) m_list.get(index);
	}

	@Override
	public Integer Count() {
		// TODO Auto-generated method stub
		return m_list.size();
	}

	@Override
	public Iterator iterator() {
		return new RowEnumerator(GetTableEnumerator());
	}
}
