package com.bitter.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.DataException;

import com.bitter.data.exception.BDataException;
import com.bitter.data.interfaces.DataReadOptions;
import com.bitter.data.interfaces.IDataAdapter;
import com.bitter.data.interfaces.IDataConversion;
import com.bitter.data.interfaces.IDataTable;
import com.bitter.data.interfaces.IDataTask;
import com.bitter.eventhandler.IEventHandler;

public class DataAdapter implements IDataAdapter, IDataTask {

	private IDataConversion conversion;
	private IEventHandler started;
	private Object tag;
	private int readRows = -1;
	private int rowsRead;
	private Thread backgroundThread;
	private boolean cancel;
	private PreparedStatement adapter;
	private ResultSet activeReader;
	private IEventHandler done;
	private IEventHandler schemaReady;
	private IEventHandler pausingRead;
	private DataTable activeTable;
	private boolean alive;
	private boolean spoolData;

	public DataAdapter(PreparedStatement adapter) {
		this(adapter, new DefaultFastConversion());
	}

	public DataAdapter(PreparedStatement adapter, IDataConversion conversion) {
		this.adapter = adapter;
		this.conversion = conversion;
	}

	@Override
	protected void finalize() throws Throwable {
		if (adapter != null) {
			Cancel();
			adapter.close();
			adapter = null;
			done = null;
			schemaReady = null;
			pausingRead = null;
		}
	};

	public IDataConversion getConversion() {
		return conversion;
	}

	public boolean backgroundProcessing() {
		return backgroundThread != null;
	}

	public boolean isCanceled() {
		return cancel;
	}

	public PreparedStatement getAdapter() {
		return adapter;
	}

	public boolean isPaused() {
		if (this.isAlive())
			return !((IDataTask) this).getProcessing();
		return false;
	}

	public boolean isSpoolData() {
		return spoolData;
	}

	public void setSpoolData(boolean spoolData) {
		this.spoolData = spoolData;
	}

	@Override
	public Object getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public void setTag(Object tag) {
		// TODO Auto-generated method stub
		this.tag = tag;
	}

	@Override
	public int getReadRows() {
		// TODO Auto-generated method stub
		return this.readRows;
	}

	@Override
	public void setReadRows(int readRows) {
		// TODO Auto-generated method stub
		this.readRows = readRows;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return this.alive;
	}

	@Override
	public boolean canCancel() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean cancelled() {
		// TODO Auto-generated method stub
		return this.cancel;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalSteps() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public boolean getProcessing() {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (!alive)
				return false;
			return readRows != rowsRead;
		}
	}

	@Override
	public int getFinishedSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (!cancel) {
				cancel = true;
				if (isAlive()) {
					try {
						adapter.cancel();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (backgroundThread != null) {
						synchronized (backgroundThread) {
							backgroundThread.notifyAll();
						}
					}

				}
			}
		}
	}

	private List<DataTable> internalFillSchema() throws SQLException {
		DataTable _startAtTable = activeTable;
		ResultSet _reader = this.getAdapter().executeQuery();
		synchronized (this) {
			activeReader = _reader;
		}
		try {
			ArrayList<DataTable> _ret = new ArrayList<DataTable>();
			do {
				ResultSetMetaData _rsmd = activeReader.getMetaData();
				int _columnCount = _rsmd.getColumnCount();
				if (_columnCount > 0) {
					activeTable = conversion.createTable(_reader,
							new DataReadOptions(this.spoolData, true));
					activeTable.setTableIndex(_ret.size());

					if (_startAtTable == null
							|| activeTable.getTableIndex() > _startAtTable
									.getTableIndex()) {
						_ret.add(activeTable);
					}
				}
			} while (!cancel && activeReader.next());
			checkCancel();
			return _ret;
		} finally {
			if (activeReader != null) {
				try {
					activeReader.close();
				} finally {
					synchronized (this) {
						activeReader = null;
					}
				}
			}
		}
	}

	private void internalFill(List<DataTable> ret) {

	}
	

	public void checkCancel() {
		new BDataException("Background query: {0} ({1})");
	}

	@Override
	public List<IDataTable> fillSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDataTable> fill() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fill(IDataTable table) {
		// TODO Auto-generated method stub

	}

	@Override
	public int update(IDataTable table) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void fillBackground() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fillBackground(IDataTable table) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Cancel() {
		// TODO Auto-generated method stub

	}
}
