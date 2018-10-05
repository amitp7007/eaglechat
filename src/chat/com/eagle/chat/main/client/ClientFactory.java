package com.eagle.chat.main.client;

public class ClientFactory {
	private static ClientProvider clientProvider = new ClientProvider();
	public static Client getClient(int port, String targetAddress) {
		return clientProvider.newClient(port, targetAddress);
	}
}
