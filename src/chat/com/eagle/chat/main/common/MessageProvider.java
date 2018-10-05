package com.eagle.chat.main.common;

public class MessageProvider {
	public Message newMessage(String str) {
		return new MessageImpl(str);
	}
}
