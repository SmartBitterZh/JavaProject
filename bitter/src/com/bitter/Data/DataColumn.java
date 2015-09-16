package com.bitter.data;

import java.io.Serializable;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;

import com.bitter.eventhandler.EventArgs;
import com.bitter.eventhandler.IEventHandler;

public class DataColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean allowDBNull;
	private String caption;
	private String columnName;
	private String dataType;
	private Object defaultValue;
	private Hashtable extendedProperties;
	private int maxLength;
	private boolean readOnly;
	private boolean unique;
	private IEventHandler propertyChanged;

	private void RaisePropertyChanged() throws Exception {
		if (propertyChanged != null)
			propertyChanged.invoked(this, EventArgs.Empty);
	}

	public boolean isAllowDBNull() {
		return allowDBNull;
	}

	public void setAllowDBNull(boolean allowDBNull) throws Exception {
		if (this.allowDBNull != allowDBNull) {
			this.allowDBNull = allowDBNull;
			RaisePropertyChanged();
		}
	}

	public String getCaption() {
		return caption.isEmpty() ? columnName : caption;
	}

	public void setCaption(String caption) throws Exception {
		String _newCaption = caption.isEmpty() ? "" : caption;
		if (!this.caption.equals(_newCaption)) {
			this.caption = _newCaption;
			RaisePropertyChanged();
		}
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) throws Exception {
		String _newColumnName = columnName.isEmpty() ? "" : columnName;
		if (!this.caption.equals(_newColumnName)) {
			this.columnName = _newColumnName;
			RaisePropertyChanged();
		}
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) throws Exception {
		if (!this.dataType.equals(dataType)) {
			this.dataType = dataType;
			RaisePropertyChanged();
		}
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		if ((this.defaultValue == null && defaultValue != null)
				|| (this.defaultValue != null && !this.defaultValue
						.equals(defaultValue))) {
			this.defaultValue = defaultValue;

		}
	}

	public Hashtable getExtendedProperties() {
		if (extendedProperties == null)
			extendedProperties = new Hashtable<>();
		return extendedProperties;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) throws Exception {
		if (this.maxLength != maxLength) {
			this.maxLength = maxLength;
			RaisePropertyChanged();
		}
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) throws Exception {
		if (this.readOnly != readOnly) {
			this.readOnly = readOnly;
			RaisePropertyChanged();
		}
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) throws Exception {
		if (this.unique != unique) {
			this.unique = unique;
			RaisePropertyChanged();
		}
	}

	public void addPropertyChanged(IEventHandler propertyChanged) {
		this.propertyChanged = propertyChanged;
	}

	public void removePropertyChanged() {
		propertyChanged = null;
	}

	public DataColumn getDataColumn() throws Exception {
		DataColumn _column = new DataColumn(this.columnName, this.dataType);
		_column.setAllowDBNull(this.allowDBNull);
		if (this.caption != null)
			_column.setCaption(this.caption);
		if (this.defaultValue != null)
			_column.setDefaultValue(this.defaultValue);

		Set _keySet = this.extendedProperties.keySet();
		Hashtable _table = _column.getExtendedProperties();
		for (Object _key : _keySet) {
			_table.put(_key, this.extendedProperties.get(_key));
		}
		_column.setMaxLength(this.maxLength);
		_column.setReadOnly(this.readOnly);
		_column.setUnique(this.unique);
		return _column;
	}

	public DataColumn(String columnName, String dataType, String caption) {

		this.dataType = dataType;
		this.columnName = columnName.isEmpty() ? "" : columnName;
		this.allowDBNull = true;
		this.defaultValue = null;
		this.maxLength = -1;
		this.caption = caption;
	}

	public DataColumn(String columnName, String dataType) {
		this(columnName, dataType, null);
	}

	public DataColumn(String columnName) {
		this(columnName, String.class.getSimpleName(), null);
	}

	public DataColumn(DataColumn column) {
		this.allowDBNull = column.isAllowDBNull();
		this.caption = column.getCaption();
		this.columnName = column.getColumnName();
		this.dataType = column.getDataType();
		this.defaultValue = column.getDefaultValue();
		if (column.extendedProperties != null
				&& column.extendedProperties.size() > 0)
			this.extendedProperties = new Hashtable(
					column.getExtendedProperties());
		this.maxLength = column.getMaxLength();
		this.readOnly = column.isReadOnly();
		this.unique = column.isUnique();
	}

	public DataColumn(ResultSetMetaData resultSetMetaData, int columnIndex) {
		try {
			this.columnName = resultSetMetaData.getColumnName(columnIndex);
			this.dataType = resultSetMetaData.getColumnTypeName(columnIndex);
			String _lable = resultSetMetaData.getColumnLabel(columnIndex);
			String _className = resultSetMetaData
					.getColumnClassName(columnIndex);
			String _catalog = resultSetMetaData.getCatalogName(columnIndex);
			String _tableName = resultSetMetaData.getTableName(columnIndex);
			String _schemaName = resultSetMetaData.getSchemaName(columnIndex);
			int _precision = resultSetMetaData.getPrecision(columnIndex);
			int _scale = resultSetMetaData.getScale(columnIndex);
			boolean _isAutoIncrement = resultSetMetaData
					.isAutoIncrement(columnIndex);
			boolean _isCaseSensitive = resultSetMetaData
					.isCaseSensitive(columnIndex);
			boolean _isCurrency = resultSetMetaData.isCurrency(columnIndex);
			boolean _isReadOnly = resultSetMetaData.isReadOnly(columnIndex);
			int _isNullable = resultSetMetaData.isNullable(columnIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		propertyChanged = null;
		if (extendedProperties != null)
			extendedProperties.clear();
	}
}
