package com.bitter.data;

import com.bitter.eventhandler.EventArgs;
import com.bitter.eventhandler.IEventHandler;

public class DataRowChangedHandler implements IEventHandler {

	@Override
	public void invoked(Object sender, EventArgs args) throws Exception {
		// TODO Auto-generated method stub

	}

}
enum RowOperation {
	RowAddAccepted, RowAdd, RowRemoved, RowUpdated, RowMoved, Reset
}

class DataRowChangedArgs extends EventArgs {
	

	private RowOperation rowOperation;
	private Object[] newRow;
	private Object[] oldRow;

	public RowOperation getRowOperation() {
		return rowOperation;
	}

	public void setRowOperation(RowOperation rowOperation) {
		this.rowOperation = rowOperation;
	}

	public Object[] getNewRow() {
		return newRow;
	}

	public void setNewRow(Object[] newRow) {
		this.newRow = newRow;
	}

	public Object[] getOldRow() {
		return oldRow;
	}

	public void setOldRow(Object[] oldRow) {
		this.oldRow = oldRow;
	}

	public DataRowChangedArgs(RowOperation rowOperation, Object[] newRow,
			Object[] oldRow) {
		super();
		this.rowOperation = rowOperation;
		this.newRow = newRow;
		this.oldRow = oldRow;
	}

	public DataRowChangedArgs(RowOperation rowOperation, Object[] newRow) {
		super();
		this.rowOperation = rowOperation;
		this.newRow = newRow;
	}

	public DataRowChangedArgs(RowOperation rowOperation) {
		super();
		this.rowOperation = rowOperation;
	}

}
