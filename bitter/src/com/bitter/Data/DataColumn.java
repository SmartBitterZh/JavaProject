package com.bitter.Data;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class DataColumn {

	private boolean allowDBNull;
	private String caption;
	private String columnName;
	private String dataType;
	private Object defaultValue;
	private HashMap<String, Object> extendedProperties;
	private int maxLength;
	private boolean readOnly;
	private boolean unique;

	// private EventHandler propertyChanged;
	public DataColumn(ResultSetMetaData resultSetMetaData, int columnIndex) {
		try {
			columnName = resultSetMetaData.getColumnName(columnIndex);
			dataType = resultSetMetaData.getColumnTypeName(columnIndex);
			String _lable = resultSetMetaData.getColumnLabel(columnIndex);
			String _className = resultSetMetaData.getColumnClassName(columnIndex);
			String _catalog = resultSetMetaData.getCatalogName(columnIndex);
			String _tableName = resultSetMetaData.getTableName(columnIndex);
			String _schemaName = resultSetMetaData.getSchemaName(columnIndex);
			int _precision = resultSetMetaData.getPrecision(columnIndex);
			int _scale = resultSetMetaData.getScale(columnIndex);
			boolean _isAutoIncrement = resultSetMetaData.isAutoIncrement(columnIndex);
			boolean _isCaseSensitive = resultSetMetaData.isCaseSensitive(columnIndex);
			boolean _isCurrency = resultSetMetaData.isCurrency(columnIndex);
			boolean _isReadOnly = resultSetMetaData.isReadOnly(columnIndex);
			int _isNullable = resultSetMetaData.isNullable(columnIndex);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DataColumn(String columnName) {
		this(columnName, "String", columnName);
	}

	public DataColumn(String columnName, String dataType) {
		this(columnName, dataType, columnName);
	}

	public DataColumn(String columnName, String dataType, String caption) {
		this.dataType = dataType;
		this.columnName = columnName == null ? "" : columnName;
		allowDBNull = true;
		defaultValue = null;
		maxLength = -1;
		this.caption = caption;
	}

	private void RaisePropertyChanged() {
		// if(propertyChanged!=null)
		// propertyChanged()
	}

	public boolean isAllowDBNull() {
		return allowDBNull;
	}

	public void setAllowDBNull(boolean allowDBNull) {
		this.allowDBNull = allowDBNull;
		RaisePropertyChanged();
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
		RaisePropertyChanged();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
		RaisePropertyChanged();
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
		RaisePropertyChanged();
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
		RaisePropertyChanged();
	}

	public HashMap<String, Object> getExtendedProperties() {
		return extendedProperties;
	}

	public void setExtendedProperties(HashMap<String, Object> extendedProperties) {
		this.extendedProperties = extendedProperties;
		RaisePropertyChanged();
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		RaisePropertyChanged();
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		RaisePropertyChanged();
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
		RaisePropertyChanged();
	}
}
