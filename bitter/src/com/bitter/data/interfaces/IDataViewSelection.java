package com.bitter.data.interfaces;

import java.util.List;

public interface IDataViewSelection {
	boolean rowIncluded(IDataTable table, Object[] row);

	List<Object[]> RowsIncluded(IDataTable table);
}
