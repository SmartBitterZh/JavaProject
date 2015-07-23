package com.bitter.eventhandler;

public interface IEventHandler {
	/**
	 * 
	 * @param sender
	 * @param args (object or EventArgs)
	 */
	public void invoked(Object sender, Object args);
}
