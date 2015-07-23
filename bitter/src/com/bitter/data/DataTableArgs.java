package com.bitter.data;

import com.bitter.data.interfaces.IDataTable;
import com.bitter.eventhandler.EventArgs;

public class DataTableArgs extends EventArgs {
	public static IDataTable Table;

	// / <summary>
	// / Constructor.
	// / </summary>
	public DataTableArgs(IDataTable table) {
		Table = table;
	}
}
