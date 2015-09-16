package com.bitter.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import com.bitter.data.interfaces.IDataRow;
import com.bitter.eventhandler.EventArgs;

public class DataIndex {
	DataTable table;
	final int FirstSize = 5;
	private int indexColumn;
	private int references;

	class DifferentTypeComparer implements Comparator<Object> {
		List<String> types = null;

		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			String _typeo1 = o1.getClass().getSimpleName();
			String _typeo2 = o2.getClass().getSimpleName();
			if (_typeo1.equals(_typeo2)) {
				return ((Comparable) o1).compareTo(o2);
			}
			if (types == null) {
				types = new ArrayList<String>();
			}
			int _o1i = types.indexOf(_typeo1);
			if (_o1i < 0) {
				_o1i = types.size();
				types.add(_typeo1);
			}
			int _o2i = types.indexOf(_typeo2);
			if (_o2i < 0) {
				_o2i = types.size();
				types.add(_typeo2);
			}
			return Integer.compare(_o1i, _o2i);
		}

	}

	TreeMap<Object, Object[][]> indexRows = new TreeMap<Object, Object[][]>(
			new DifferentTypeComparer());

	protected DataIndex(DataTable table, int indexColumn) {
		this.table = table;
		this.indexColumn = indexColumn;

		//
		for (IDataRow _row : table.getRows()) {
			this.addRow(_row.ItemArray());
		}
		table.addRowChanged(new DataRowChangedHandler() {

			@Override
			public void invoked(Object sender, EventArgs args) throws Exception {
				// TODO Auto-generated method stub
				tabelRowChanged(sender, (DataRowChangedArgs) args);
			}
		});
	}

	private void tabelRowChanged(Object sender, DataRowChangedArgs args) {
		synchronized (this) {
			if (references == 0)
				return;
			RowOperation _operation = args.getRowOperation();
			if (_operation == RowOperation.Reset) {
				indexRows.clear();
				for (DataRow row : table.getRows()) {
					this.addRow(row.ItemArray());
				}
			} else if (_operation == RowOperation.RowAdd
					|| _operation == RowOperation.RowAddAccepted) {
				this.addRow(args.getNewRow());
			} else if (_operation == RowOperation.RowRemoved) {
				if (args.getNewRow()[indexColumn] != null) {
					if (indexRows.containsKey(args.getNewRow()[indexColumn])) {
						Object[][] indexEntry = indexRows
								.get(args.getNewRow()[indexColumn]);
						for (int i = 0; i < indexEntry.length; i++) {
							if (indexEntry[i] != args.getNewRow()) {
								indexEntry[i] = null;
								break;
							}
						}
					}
				}
			} else if (_operation == RowOperation.RowUpdated) {
				Object oldVal = args.getOldRow()[indexColumn];
				Object newVal = args.getNewRow()[indexColumn];
				boolean equals = (oldVal != null && oldVal.equals(newVal));
				if (oldVal != null && !equals) {
					this.tabelRowChanged(sender, new DataRowChangedArgs(
							RowOperation.RowRemoved, args.getOldRow()));
				}
				if (args.getNewRow()[indexColumn] != null && !equals) {
					this.tabelRowChanged(sender, new DataRowChangedArgs(
							RowOperation.RowAdd, args.getNewRow()));
				}
			} else if (_operation == RowOperation.RowMoved) {
				if(args.getOldRow()[indexColumn]!=null){
					this.tabelRowChanged(sender, new DataRowChangedArgs(
							RowOperation.RowRemoved, args.getOldRow()));
				}
				if(args.getNewRow()[indexColumn]!=null){
					this.tabelRowChanged(sender, new DataRowChangedArgs(
							RowOperation.RowAdd, args.getNewRow()));
				}
			}
		}
	}

	public int getIndexColumn() {
		return this.indexColumn;
	}

	public int getIndexOrdinality() {
		return this.indexRows.size();
	}

	public List<Object[]> getRows(Object value) {
		synchronized (this) {
			ArrayList<Object[]> _rows = new ArrayList<>();
			boolean _containKey = indexRows.containsKey(value);

			if (value != null && _containKey) {
				for (Object[] indexRow : indexRows.get(value)) {
					if (indexRow != null)
						_rows.add(indexRow);
				}
			}
			return _rows;
		}
	}

	public List<DataRow> getDataRows(Object value) {
		List<DataRow> _list = new ArrayList<DataRow>();
		boolean _containKey = indexRows.containsKey(value);

		if (value != null && _containKey) {
			for (Object[] indexRow : indexRows.get(value)) {
				if (indexRow != null)
					_list.add(new DataRow(table, indexRow));
			}
		}

		return _list;
	}

	protected void addReference() {
		synchronized (this) {
			references++;
		}
	}

	private boolean removerRefrence() {
		synchronized (this) {
			references--;
			if (references == 0) {
				table.removeRowChanged();
				return true;
			}
			return false;
		}
	}

	private void addRow(Object[] row) {
		Object val = row[indexColumn];
		if (val != null) {
			if (indexRows.containsKey(val)) {
				Object[][] indexEntry = indexRows.get(val);
				int i;
				if (indexEntry.length > FirstSize)
					i = indexEntry.length / 2;
				else
					i = 1;
				while (i < indexEntry.length) {
					if (indexEntry[i] == null) {
						indexEntry[i] = row;
						return;
					}
					i++;
				}
				Object[][] newEntry = new Object[indexEntry.length * 2][];
				for (int j = 0; j < indexEntry.length; j++)
					newEntry[j] = indexEntry[j];
				newEntry[indexEntry.length] = row;
				indexRows.put(val, newEntry);
			} else {
				Object[][] indexEntry = new Object[FirstSize][];
				indexEntry[0] = row;
				indexRows.put(val, indexEntry);
			}
		}
	}
}
