package com.bitter.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.bitter.data.interfaces.DataReadOptions;
import com.bitter.data.interfaces.IDataConversion;

public final class DefaultFastConversion implements IDataConversion {

	@Override
	public DataTable createTable(ResultSet reader, DataReadOptions options) {
		// TODO Auto-generated method stub
		return defaultCreateTable(reader, options);
	}

	@Override
	public void readRow(ResultSet reader, DataTable table,
			DataReadOptions options) {
		// TODO Auto-generated method stub
		defaultReadRow(reader, table, options);
	}

	public static void defaultReadRow(ResultSet reader, DataTable table,
			DataReadOptions options) {
		Object[] rowArray = new Object[table.getColumns().size()];
		ResultSetMetaData _rsmd;
		try {
			_rsmd = reader.getMetaData();
			int _columnCount = _rsmd.getColumnCount();
			for (int i = 0; i < _columnCount; i++) {
				if (table.getColumns().get(i).getDataType() != "byte[]") {
					rowArray[i] = reader.getObject(i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table.acceptChanges();
	}

	public DataTable defaultCreateTable(ResultSet reader,
			DataReadOptions options) {
		// TODO Auto-generated method stub
		DataTable _dataTable = new DataTable(options.isSpoolResults());
		ResultSetMetaData _rsmd;
		int _columnCount = 0;
		try {
			_rsmd = reader.getMetaData();
			_columnCount = _rsmd.getColumnCount();
			boolean _containsIsAutoIncrement = false;
			boolean _containsIdentity = false;

			for (int i = 0; i < _columnCount; i++) {
				if (!_containsIsAutoIncrement)
					_containsIdentity = _containsIsAutoIncrement = _rsmd
							.isAutoIncrement(i);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
