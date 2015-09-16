package com.bitter.othertest;

import com.bitter.eventhandler.EventArgs;
import com.bitter.eventhandler.IEventHandler;

public class EventTest {

	public static void main(String[] args) {
		TheThird _td = new TheThird();
		_td.setCallbackListerner(new IEventHandler() {
			
			@Override
			public void invoked(Object sender, EventArgs args) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("delected third function is called.");
			}
		});

		try {
			_td.helloThrid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class TheThird {
	private IEventHandler listener = null;

	public void setCallbackListerner(IEventHandler callback) {
		this.listener = callback;
	}

	public void helloThrid() throws Exception {
		System.out.println("helloThrid is invoked.");
		if (listener != null)
			listener.invoked(this, null);
	}
}