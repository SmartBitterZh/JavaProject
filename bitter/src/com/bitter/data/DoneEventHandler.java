package com.bitter.data;

import com.bitter.eventhandler.IEventHandler;

public class DoneEventHandler implements IEventHandler {

	public void Done(Object sender, Object args){
		
	}

	@Override
	public void invoked(Object sender, Object args) {
		// TODO Auto-generated method stub
		Done(sender,args);
	}
	

}