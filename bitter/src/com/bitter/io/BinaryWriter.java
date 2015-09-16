package com.bitter.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BinaryWriter extends FilterOutputStream {

	public BinaryWriter(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}

	public void Write(boolean value) {
	}

	public void Write(byte value) {
	}

	public void Write(byte[] buffer) {
	}

	public void Write(float value) {
	}

	public void Write(int value) {
	}

	public void Write(String value) {
	}

	/**
	 * 
	 * @param bytes
	 * @throws IOException
	 */
	public void writeBytes(byte[] bytes) throws IOException {
		this.write(bytes);
	}
}
