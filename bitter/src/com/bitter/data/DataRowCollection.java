package com.bitter.data;

import java.util.Iterator;

import com.bitter.data.exception.BDataException;
import com.bitter.data.interfaces.IDataRow;

public class DataRowCollection extends BaseRowCollection {
	
	private boolean m_deleted;

	protected DataRowCollection(boolean deleted, DataTable list) {
		super(list);
		// TODO Auto-generated constructor stub
		this.m_deleted = deleted;
	}

	public DataRowCollection getDeleteRows() throws BDataException {
		checkDeleted();
		return new DataRowCollection(true, (DataTable)this.getList());
	}

	private void checkDeleted() throws BDataException{
		if(m_deleted)
			throw new BDataException("Not valid from a deleted row colleciton");
	}
	
	public DataRow add(Object[] values) throws BDataException{
		this.checkDeleted();
		return ((DataTable)this.getList()).addRow(values);
	}
	
	@Override
	protected Iterator GetTableEnumerator() {
		// TODO Auto-generated method stub
		if(m_deleted)
			return ((DataTable)this.getList()).getDeletedRows();
		else
			return this.getList().iterator();
	}
	
	@Override
	public void Clear() {
		// TODO Auto-generated method stub
		try {
			this.checkDeleted();
		} catch (BDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.Clear();
	}

	@Override
	public void Insert(int index, Object[] row) {
		// TODO Auto-generated method stub
		try {
			this.checkDeleted();
		} catch (BDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.Insert(index, row);
	}

	@Override
	public void Remove(IDataRow row) {
		// TODO Auto-generated method stub
		try {
			this.checkDeleted();
		} catch (BDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.Remove(row);
	}

	@Override
	public void RemoveAt(int index) {
		// TODO Auto-generated method stub
		try {
			this.checkDeleted();
		} catch (BDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.RemoveAt(index);
	}

}
