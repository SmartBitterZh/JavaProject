package com.bitter.data.interfaces;

import java.io.Writer;

public interface IFastArrayTransform<T> {
	Object[] addRow(T row);

	boolean WriteValue(Writer writer, Object row, int chunckCount);
	
	Object[] CompleteTransformation();
}
