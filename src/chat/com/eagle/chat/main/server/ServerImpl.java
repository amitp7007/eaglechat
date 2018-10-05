package com.eagle.chat.main.server;

import com.eagle.chat.main.common.MessageCallback;

public class ServerImpl implements Server{

	private MessageCallback listener;
	
	public ServerImpl(MessageCallback lste) {
		listener = lste;
	}

	@Override
	public void listen() {
		// TODO Auto-generated method stub
		
	}
	
}
