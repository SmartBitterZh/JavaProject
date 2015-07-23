package com.bitter.data.interfaces;

import java.sql.ResultSet;

import com.bitter.data.DataTable;

public interface IDataConversion {
	
	DataTable createTable(ResultSet reader, DataReadOptions options);
	
	void readRow(ResultSet reader, DataTable table,DataReadOptions options );
}
