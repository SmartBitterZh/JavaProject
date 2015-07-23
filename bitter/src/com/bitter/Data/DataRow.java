package com.bitter.data;

import com.bitter.data.interfaces.IDataRow;
import com.bitter.data.interfaces.IDataTable;

public class DataRow implements IDataRow {

	private static DataTable table;
	private static Object[] data;
	private static Object[][] chunkDat;
	private static boolean deleted;

	public DataRow(DataTable table, Object[] data) {
		this.table = table;
		this.data = data;
	}

	protected DataRow(boolean deleted, DataTable table, Object[] data) {
		this.table = table;
		this.data = data;
		this.deleted = deleted;
	}

	protected DataRow(DataTable table, Object[] data, Object[][] chunkDat) {
		this(table, data);
		this.chunkDat = chunkDat;
	}

	@Override
	public DataRowState rowState() {
		// TODO Auto-generated method stub
		if(deleted)
			return DataRowState.Deleted;
		return table.getRowState(data);
	}

	@Override
	public boolean hasChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] GetOriginalRow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] ItemArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataTable table() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String columnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(String columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(DataColumn column) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object set(DataColumn columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(int column, DataRowVersion version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String columnName, DataRowVersion version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(DataColumn column, DataRowVersion version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean IsNull(DataColumn column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsNull(int column) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void AcceptChanges() {
		// TODO Auto-generated method stub

	}

	@Override
	public void RejectChanges() {
		// TODO Auto-generated method stub

	}

}
