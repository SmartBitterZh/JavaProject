package com.bitter.util;

import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.dialect.SQLServerDialect;

public class SQLServerDialectExtensions extends SQLServerDialect {
	public SQLServerDialectExtensions() {
		super();
		// Use Unicode Characters
		registerColumnType(Types.VARCHAR, 255, "nvarchar($l)");
		registerColumnType(Types.CHAR, "nchar(1)");
		registerColumnType(Types.CLOB, "nvarchar(max)");
		// Microsoft SQL Server supports bigint and bit
		registerColumnType(Types.BIGINT, "bigint");
		registerColumnType(Types.BIT, "bit");
	}

	public String getTypeName(int code, int length, int precision, int scale)
			throws HibernateException {
		if (code != 2005) {
			return super.getTypeName(code, length, precision, scale);
		} else {
			return "ntext";
		}
	}
}
