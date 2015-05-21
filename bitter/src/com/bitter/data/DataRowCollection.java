package com.bitter.data;

import java.util.List;

import com.bitter.data.exception.BitterDataException;

public class DataRowCollection extends AbstractRowCollection {

	private boolean deleted;

	public DataRowCollection(boolean deleted, List<IDataRow> list) {
		super(list);
		// TODO Auto-generated constructor stub
		this.deleted = deleted;
	}

	public DataRowCollection(boolean deleted, DataTable table) {
		super(table);
		// TODO Auto-generated constructor stub
		this.deleted = deleted;
	}

	/**
	 * Get the deleted rows from this table.
	 * 
	 * @return
	 * @throws BitterDataException
	 */
	public DataRowCollection DeletedRows() throws BitterDataException {
		checkDeleted();
		return new DataRowCollection(true, this.getList());
	}

	private void checkDeleted() throws BitterDataException {
		if (deleted)
			throw new BitterDataException(
					"Not valid from a deleted row colleciton");
	}

	/**
	 * Adds an item to the collection.
	 * 
	 * @param values The values to add to the collection.
	 * @return
	 * @throws BitterDataException 
	 */
	public DataRow Add(Object[] values) throws BitterDataException {
		checkDeleted();
		DataRow _row = new DataRow();

		if (this.getList().add(_row))
			return _row;
		else
			return null;
	}
}
