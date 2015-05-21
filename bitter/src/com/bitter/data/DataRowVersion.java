package com.bitter.data;

public enum DataRowVersion {
	/**
	 * Current values of row.
	 */
	Current,
	/**
	 * Original values of row.
	 */
	Original,
	/**
	 * Row that has been deleted.
	 */
	Deleted
}
