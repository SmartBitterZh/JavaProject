package com.bitter.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import com.bitter.data.interfaces.IDataRow;

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
			boolean _containKey = false;
			_containKey = indexRows.containsKey(value);

			if (value != null && _containKey) {
				for (Object[] indexRow : indexRows.get(value)) {
					if (indexRow != null)
						_rows.add(indexRow);
				}
			}
			return _rows;
		}
	}
}
