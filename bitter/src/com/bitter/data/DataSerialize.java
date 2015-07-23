package com.bitter.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Comparator;
import java.util.Hashtable;

public abstract class DataSerialize implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Comparator getComparer() {
		return null;
	}

	public abstract Object Deserialize(InputStream inStream);

	public abstract boolean Serialize(OutputStream outStream, Object obj);

	private static DataSerialize valueSerializer(String type) {
		return null;
	}

	private static Hashtable<String, DataSerialize> dataSerializers = new Hashtable<String, DataSerialize>();

	public static void registerTypeSerializer(String type,
			DataSerialize serializer) {
		synchronized (dataSerializers) {
			dataSerializers.put(type, serializer);
		}
	}

	static {
		// registerTypeSerializer(Object.class.getSimpleName(),new );
	}

	public static DataSerialize getSerializer(String type) {
		return null;

	}
}

final class DataStringSerialize extends DataSerialize implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object Deserialize(InputStream inStream) {
		// TODO Auto-generated method stub
		byte[] _rData = new byte[512];
		try {
			switch (inStream.read()) {
			case 0:
				return null;
			case 1:
				return inStream.read(_rData);
			default:
				int length = inStream.read();
				inStream.read(_rData);
				return _rData;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _rData;
	}

	@Override
	public boolean Serialize(OutputStream outStream, Object obj) {
		// TODO Auto-generated method stub
		if (obj == null) {
			try {
				outStream.write(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String _str = obj.toString();
			boolean _useUtf16 = false;
			for (int i = 0; i < _str.length(); i++) {
			}
		}
		return false;
	}

}
