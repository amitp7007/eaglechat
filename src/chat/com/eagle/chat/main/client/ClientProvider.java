package com.eagle.chat.main.client;

public class ClientProvider {
	protected ClientProvider() {
		
	}
	public Client newClient(int port, String address) {
		return new ClientImpl();
	}
}
