package com.eagle.chat.main.common;

public class MessageFactory {
	private static MessageProvider mProvider = new MessageProvider();
	
	public static Message createNewMessage(String m) {
		return mProvider.newMessage(m);
	}
}
