package com.bitter.data;

import java.util.List;

public interface IDataRowCollection extends List<IDataRow> {

	/**
	 * Determines whether the collection contains a specific row.
	 * 
	 * @param row
	 *            The row to locate in the collection.
	 * @return true if the row is found in the collection; otherwise, false.
	 */
	boolean Contains(IDataRow row);

	/**
	 * Removes all items from the row.
	 */
	void Clear();

	/**
	 * Determines the index of a specific row in the collection.
	 * 
	 * @param row
	 * @return The index of value if found in the list; otherwise, -1.
	 */
	int IndexOf(IDataRow row);

	/**
	 * Inserts an item to the collection at the specified index.
	 * 
	 * @param index
	 *            The zero-based index at which value should be inserted.
	 * @param row
	 *            The row to insert into the collection.
	 */
	void Insert(int index, Object[] row);

	/**
	 * Removes the first occurrence of a specific row from the collection.
	 * 
	 * @param row
	 *            The row to remove from the table.
	 */
	void Remove(IDataRow row);

	/**
	 * Removes the row at the specified index.
	 * 
	 * @param index
	 *            The zero-based index of the item to remove.
	 */
	void RemoveAt(int index);

	/**
	 * Gets or sets the element at the specified index.
	 */
	DataRow get(int index);

	/**
	 * Gets the number of elements contained in the collection.
	 * 
	 * @return
	 */
	Integer Count();
}
