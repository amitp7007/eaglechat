package com.eagle.chat.main.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import com.eagle.chat.main.common.Message;

public class ClientImpl implements Client{
	InetSocketAddress address = null;
	SocketChannel client = null;
	//private SocketChannel channel = null;
	
	private void init() throws IOException {
		address = new InetSocketAddress("localhost", 1111);
		client = SocketChannel.open(address);
		System.out.println("Started");
	}
	
	public ClientImpl(){
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void send(Message message) {
		System.out.println("Message Sent to server");
	}

}
