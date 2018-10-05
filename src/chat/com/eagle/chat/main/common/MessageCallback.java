package com.eagle.chat.main.common;

public interface MessageCallback {
	public void recieved(Message msg);
	public void error(String err);
}
