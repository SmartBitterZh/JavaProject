package com.bitter.data;

import java.io.Serializable;
import java.util.HashMap;

public class DataTable implements IDataTable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int m_tableIndex;

	
	
	
	
	
	@Override
	public DataSet getDataSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return null;
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
	public IDataRow addRowEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataRow addRow(Object[] data) {
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

}
