package com.bitter.data;

import com.bitter.eventhandler.EventArgs;

public class ListChangedEventArgs extends EventArgs {
	public ListChangedType listChangedType;
    public int newIndex ;
    public int oldIndex;
	public ListChangedEventArgs(ListChangedType listChangedType, int newIndex){
		this.listChangedType = listChangedType;
		this.newIndex = newIndex;
	}
	public ListChangedEventArgs(ListChangedType listChangedType, int newIndex, int oldIndex){
		this.listChangedType = listChangedType;
		this.newIndex = newIndex;
		this.oldIndex = oldIndex;
	}
	public ListChangedType getListChangedType() {
		return listChangedType;
	}
	public int getNewIndex() {
		return newIndex;
	}
	public int getOldIndex() {
		return oldIndex;
	}
}
