package com.bitter.data.interfaces;

public interface IDataProgress {
	int getFinishedSteps();

	void setFinishedSteps(int stept);

	int getTotalSteps();

	void setTotalSteps(int stept);

	int getCancelled();
}
