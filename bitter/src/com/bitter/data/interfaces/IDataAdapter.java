package com.bitter.data.interfaces;

import java.util.List;

import com.bitter.eventhandler.IEventHandler;

public interface IDataAdapter {
	IEventHandler Done = null;
	IEventHandler PausingRead = null;
	IEventHandler SchemaRead = null;

	Object getTag();
	void setTag(Object tag);

	int getReadRows();
	void setReadRows(int readRows);

	boolean isAlive();

	boolean cancelled();

	List<IDataTable> fillSchema();

	List<IDataTable> fill();

	void fill(IDataTable table);

	int update(IDataTable table);

	void fillBackground();

	void fillBackground(IDataTable table);

	void Cancel();
}
