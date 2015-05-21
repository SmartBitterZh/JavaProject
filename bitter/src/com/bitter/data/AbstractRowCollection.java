package com.bitter.data;

import java.util.AbstractList;
import java.util.List;

public class AbstractRowCollection extends AbstractList<IDataRow> {

	private List<IDataRow> m_list;

	public AbstractRowCollection(List<IDataRow> list) {
		m_list = list;
	}

	public AbstractRowCollection(DataTable table) {
	}

	public List<IDataRow> getList() {
		return m_list;
	}

	@Override
	public IDataRow get(int index) {
		// TODO Auto-generated method stub
		return m_list.get(index);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return m_list.size();
	}

}
