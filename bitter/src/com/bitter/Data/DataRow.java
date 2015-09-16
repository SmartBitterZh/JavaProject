package com.bitter.data;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bitter.data.exception.BDataException;
import com.bitter.data.interfaces.IDataRow;
import com.bitter.data.interfaces.IDataTable;

public class DataRow implements IDataRow {

	private static DataTable table;
	private static Object[] data;
	private static Object[][] chunkDat;
	private static boolean deleted;

	/**
	 * ----------------------- constructor
	 */
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

	/**
	 * ----------------------- override
	 */
	@Override
	public DataRowState rowState() {
		// TODO Auto-generated method stub
		if (deleted)
			return DataRowState.Deleted;
		return table.getRowState(data);
	}

	@Override
	public boolean hasChanged() {
		// TODO Auto-generated method stub
		return this.rowState() != DataRowState.Current;
	}

	@Override
	public Object[] GetOriginalRow() {
		// TODO Auto-generated method stub
		if (deleted)
			return data;
		return table.getOriginalRow(data);
	}

	@Override
	public Object[] ItemArray() {
		// TODO Auto-generated method stub
		if (deleted)
			return null;
		return data;
	}

	@Override
	public IDataTable table() {
		// TODO Auto-generated method stub
		return table;
	}

	@Override
	public Object get(int columnIndex) {
		// TODO Auto-generated method stub
		if (deleted)
			return null;
		return data[columnIndex];
	}

	@Override
	public void set(int columnIndex, Object value) {
		// TODO Auto-generated method stub
		if (deleted)
			try {
				throw new BDataException("Tried to edit deleted row");
			} catch (BDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		data[columnIndex] = value;
	}

	@Override
	public Object get(String columnName) {
		// TODO Auto-generated method stub
		if (deleted)
			return null;
		int column = table.getColumns().indexOf(columnName);
		if (column < 0)
			try {
				throw new BDataException(MessageFormat.format(
						"Column {0} doesn't exist", columnName));
			} catch (BDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return this.get(column);
	}

	@Override
	public void set(String columnName, Object value) {
		// TODO Auto-generated method stub
		try {
			if (deleted)
				throw new BDataException("Tried to edit deleted row");
			int column = table.getColumns().indexOf(columnName);
			if (column < 0)
				throw new BDataException(MessageFormat.format(
						"Column {0} doesn't exist", columnName));
			this.set(column, value);
		} catch (BDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object get(DataColumn column) {
		// TODO Auto-generated method stub
		if (deleted)
			return null;
		return this.get(table.getColumns().indexOf(column));
	}

	@Override
	public void set(DataColumn column, Object value) {
		if (deleted)
			try {
				throw new BDataException("Tried to edit deleted row");
			} catch (BDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		set(table.getColumns().indexOf(column), value);
	}

	@Override
	public Object get(int column, DataRowVersion version) {
		// TODO Auto-generated method stub
		if (deleted) {
			if (version == DataRowVersion.Deleted)
				return data[column];
			return null;
		}
		if (version == DataRowVersion.Original) {
			Object[] original = GetOriginalRow();
			if (original != null)
				return original[column];
		}
		return data[column];
	}

	@Override
	public Object get(String columnName, DataRowVersion version) {
		// TODO Auto-generated method stub
		return this.get(table.getColumns().indexOf(columnName), version);
	}

	@Override
	public Object get(DataColumn column, DataRowVersion version) {
		// TODO Auto-generated method stub
		return this.get(table.getColumns().indexOf(column), version);
	}

	@Override
	public boolean IsNull(String column) {
		// TODO Auto-generated method stub
		if (deleted)
			return true;
		return this.IsNull(this.table.getColumns().indexOf(column));
	}

	@Override
	public boolean IsNull(DataColumn column) {
		// TODO Auto-generated method stub
		if (deleted)
			return true;
		return this.IsNull(this.table.getColumns().indexOf(column));
	}

	@Override
	public boolean IsNull(int column) {
		// TODO Auto-generated method stub
		if (deleted)
			return true;
		Object _val = this.get(column);
		if (_val == null || _val.toString() != "")
			return true;
		return false;
	}

	@Override
	public void AcceptChanges() {
		// TODO Auto-generated method stub
		this.table.acceptChanges(data);
	}

	@Override
	public void RejectChanges() {
		// TODO Auto-generated method stub
		this.table.rejectChanges(data);
	}

	@Override
	public String toString() {
		StringBuilder _stringB = new StringBuilder();
		for (DataColumn column : this.table.getColumns()) {
			Object _val = this.get(column);
			if (_val == null || _val.toString() == "")
				_val = null;
			if (_val != null) {
				String _dateFormat = "yyyy/MM/dd HH:mm:ss";
				Date _date = null;
				try {
					_date = new SimpleDateFormat(_dateFormat).parse(_val
							.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (_date != null)
					_stringB.append(MessageFormat.format("{0}, ",
							_date.toString()));
				else
					_stringB.append(MessageFormat.format("{0}, ", _val));
			} else
				_stringB.append("NULL, ");
		}
		return _stringB.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().isArray()) {
			return obj == data;
		} else {
			DataRow row = (DataRow) obj;
			if (row != null)
				return row.data == data;
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		if (data == null)
			return 0;
		return data.hashCode();
	}
}
