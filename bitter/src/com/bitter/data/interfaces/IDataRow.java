package com.bitter.data.interfaces;

import com.bitter.data.DataColumn;
import com.bitter.data.DataRowState;
import com.bitter.data.DataRowVersion;

public interface IDataRow {

	/**
	 * Get the state of the row.
	 * 
	 * @return
	 */
	DataRowState rowState();

	/**
	 * Check if the row has changed.
	 * 
	 * @return
	 */
	boolean hasChanged();

	/**
	 * Get the original values of this row before it was modified or deleted.
	 * 
	 * @return
	 */
	Object[] GetOriginalRow();

	/**
	 * Get the internal data for the row. You must not change values in this
	 * array.
	 * 
	 * @return
	 */
	Object[] ItemArray();

	/**
	 * Get the table of the row.
	 * 
	 * @return
	 */
	IDataTable table();

	/**
	 * Get or set value of a column.
	 * 
	 * @param column
	 *            Column Index.
	 * @return
	 */
	Object get(int columnIndex);
	void set(int columnIndex, Object value);

	/**
	 * Get or set value of a column.
	 * 
	 * @param columnName
	 *            Column name.
	 * @return The value.
	 */
	Object get(String columnName);
	void set(String columnName, Object value);
	
	/**
	 * Get or set value of a column.
	 * 
	 * @param column
	 *            Column.
	 * @return The value.
	 */
	Object get(DataColumn column);
	void set(DataColumn column, Object value);
	
	/**
	 * Get or set value of a column.
	 * 
	 * @param column
	 *            Column index.
	 * @param version
	 *            The version to get the value of.
	 * @return The value.
	 */
	Object get(int column, DataRowVersion version);
	
	/**
	 * Get or set value of a column.
	 * 
	 * @param columnName
	 *            Column Name.
	 * @param version
	 *            The version to get the value of.
	 * @return
	 */
	Object get(String columnName, DataRowVersion version);
	
	/**
	 * Get or set value of a column.
	 * 
	 * @param columnName
	 *            Column.
	 * @param version
	 *            The version to get the value of.
	 * @return
	 */
	Object get(DataColumn column, DataRowVersion version);
	

	/**
	 * 
	 * @param column
	 * @return
	 */
	boolean IsNull(String column);
	/**
	 * Check if the value of a column is null.
	 * 
	 * @param column
	 *            The column to check.
	 * @return True if value of column is null.
	 */
	boolean IsNull(DataColumn column);

	/**
	 * Check if the value of a column is null.
	 * 
	 * @param column
	 *            The column to check.
	 * @return True if value of column is null.
	 */
	boolean IsNull(int column);

	/**
	 * Accept the changes for this row.
	 */
	void AcceptChanges();

	/**
	 * Accept the changes for this row.
	 */
	void RejectChanges();
}
