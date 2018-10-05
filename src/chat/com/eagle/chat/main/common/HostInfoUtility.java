package com.eagle.chat.main.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInfoUtility {
	public static HostInfo gatherCurrentHostInfo() {
		try {
			InetAddress address = InetAddress.getLocalHost();
			String hostAddress = address.getHostAddress();
			String hostName = address.getHostName();
			String userName = System.getProperty("user.name");
			return new HostInfo(hostName, hostAddress, userName, 50000);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
