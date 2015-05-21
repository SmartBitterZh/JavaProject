package com.bitter.data;

import java.util.HashMap;

public interface IDataTable {

	// / <summary>
	// / Get existing index if one exists for the table.
	// / </summary>
	// / <param name="column">The column to get the index for.</param>
	// / <returns>The index if it exists or null.</returns>
	// FastIndex GetColumnIndex(FastColumn column);

	/**
	 * Get or set the DataSet
	 * 
	 * @return
	 */
	DataSet getDataSet();

	/**
	 * Get or set the name of this table.
	 * 
	 * @return
	 */
	String getTableName();

	/**
	 * Reject any changes made to the table.
	 */
	void rejectChanges();

	/**
	 * Accept any changes made to the table.
	 */
	void acceptChanges();

	/**
	 * Get if any changes have been made to the table.
	 * 
	 * @return
	 */
	boolean hasChanges();

	/**
	 * Add an empty row to the table (Observe that in difference to NewRow of
	 * DataTable this call adds the row immediately to the table.
	 * 
	 * @return
	 */
	IDataRow addRowEmpty();

	/**
	 * Add an empty row to the table (Observe that in difference to NewRow of
	 * DataTable this call adds the row immediately to the table.
	 * 
	 * @param data
	 * @return
	 */
	IDataRow addRow(Object[] data);

	/**
	 * Suspend notifications while loading data.
	 */
	void beginLoadData();

	/**
	 * Resume notifications after loading data.
	 */
	void endLoadData();

	/**
	 * Clear rows of table.
	 */
	void clear();

	/**
	 * Access the columns of the table. Adding or removing columns will cause
	 * all the already created items to become invalid.
	 * 
	 * @return
	 */
	DataColumnCollection getColumns();

	/**
	 * Access to the rows of the table.
	 * 
	 * @return
	 */
	IDataRowCollection getRows();

	/**
	 * Adds an item to the table that is already accepted. Be observed that the
	 * array passed in will actually be used internally in the table so you must
	 * not change it after adding it to the table.
	 * 
	 * @param data
	 *            The data to add to the table.
	 * @return
	 */
	int addRowAccepted(Object[] data);

	/**
	 * Clear all of the state of the table.
	 */
	void reset();

	/**
	 * Get extended properties of this table.
	 */
	HashMap<String, Object> getExtendedProperties();

	/**
	 * 
	 */
	int getTableIndex();

	/**
	 * 
	 * @param index
	 */
	void setTableIndex(int index);

	/**
	 * Create copy of the structure and settings of a data table.
	 * 
	 * @return
	 */
	IDataTable cloneTable();

	/**
	 * Create a copy of the table including the data.
	 * 
	 * @return
	 */
	IDataTable copyTable();
}
