package com.bitter.data.interfaces;

public class DataReadOptions {

	private boolean readLobs;
	private boolean spoolResults;

	public boolean isReadLobs() {
		return readLobs;
	}

	public boolean isSpoolResults() {
		return spoolResults;
	}

	public DataReadOptions() {
		readLobs = true;
		spoolResults = false;
	}

	public DataReadOptions(boolean readLobs, boolean spoolResults) {
		this.readLobs = readLobs;
		this.spoolResults = spoolResults;
	}
}
