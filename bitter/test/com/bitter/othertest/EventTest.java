package com.bitter.othertest;

import com.bitter.eventhandler.IEventHandler;

public class EventTest {

	public static void main(String[] args) {
		TheThird _td = new TheThird();
		_td.setCallbackListerner(new IEventHandler() {
			
			@Override
			public void invoked(Object sender, Object args) {
				// TODO Auto-generated method stub
				System.out.println("delected third function is called.");
			}
		});

		_td.helloThrid();
	}
}

class TheThird {
	private IEventHandler listener = null;

	public void setCallbackListerner(IEventHandler callback) {
		this.listener = callback;
	}

	public void helloThrid() {
		System.out.println("helloThrid is invoked.");
		if (listener != null)
			listener.invoked(this, null);
	}
}