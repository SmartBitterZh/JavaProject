package com.bitter.data.interfaces;

public interface IDataTask {

	String getDescription();

	boolean canCancel();

	int getTotalSteps();

	boolean getProcessing();

	int getFinishedSteps();

	void cancel();
}
