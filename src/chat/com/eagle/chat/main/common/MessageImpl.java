package com.eagle.chat.main.common;

public class MessageImpl implements Message {
	private static final long serialVersionUID = 8540693735999919481L;
	private String message;
	private long length;
	MessageImpl(String m){
		this.message = m;
		this.length = this.message.length();
	}
	@Override
	public long getLength() {
		return length;
	}
	@Override
	public String toString() {
		return message;
	}

}
