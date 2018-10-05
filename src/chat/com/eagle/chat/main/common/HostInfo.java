package com.eagle.chat.main.common;

import java.io.Serializable;

public class HostInfo implements Serializable, BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hostName;
	private String hostAddress;
	private String userName;
	private int hostListeningPort;
	
	/**
	 * 
	 * @param hostName
	 * @param hostAddress
	 * @param hostListeningPort
	 */
	public HostInfo(String hostName, String hostAddress, String userName, int hostListeningPort) {
		super();
		this.hostName = hostName;
		this.hostAddress = hostAddress;
		this.hostListeningPort = hostListeningPort;
		this.userName = userName;
	}
	/**
	 * default constructor
	 */
	public HostInfo() {}
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostAddress() {
		return hostAddress;
	}
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	public int getHostListeningPort() {
		return hostListeningPort;
	}
	public void setHostListeningPort(int hostListeningPort) {
		this.hostListeningPort = hostListeningPort;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
