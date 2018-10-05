package com.eagle.chat.host.discovery;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.util.Enumeration;

import com.eagle.chat.Constants;
import com.eagle.chat.db.DataBaseItf;
import com.eagle.chat.exception.DBException;
import com.eagle.chat.main.common.HostInfo;
import com.eagle.chat.main.common.MessageCallback;
import com.eagle.chat.main.common.MessageFactory;

public class ClientInfoReciever implements DicoveryObject{
	private DatagramChannel channel = null;
	private MessageCallback callback;
	private DataBaseItf db = null;
	private String group = Constants.GROUP_ADDRESS;
	NetworkInterface intrf = null;
	public ClientInfoReciever(DataBaseItf db, MessageCallback cal) throws IOException {
		channel = DatagramChannel.open(StandardProtocolFamily.INET);
		this.db = db;
		this.callback = cal;
	}
	private void init() throws IOException {
		channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		channel.configureBlocking(true);
		channel.bind(new InetSocketAddress(Constants.BROADCAST_PORT));
		InetAddress iGroup = InetAddress.getByName(group);
		fillNtf();
		channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, intrf);
		
		channel.join(iGroup, intrf);
	}
	public static void main(String[] args) {
		//fillNtf();
	}
	private void fillNtf() {
		
		try {
			//intrf = NetworkInterface.getByName("wlan1");
			intrf = NetworkInterface.getByName("lo");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void recieve() throws IOException, ClassNotFoundException, DBException {
		
		while(true) {
			ByteBuffer buffer = ByteBuffer.allocate(256);			
			channel.receive(buffer);
			ByteArrayInputStream byteInS = new ByteArrayInputStream(buffer.array());
			ObjectInputStream objOutS = new ObjectInputStream(byteInS);
			HostInfo info = (HostInfo)objOutS.readObject();
			db.insert(info);
			callback.recieved(MessageFactory.createNewMessage("Message recievde : " +  info.getHostAddress() + " : "
					+ info.getHostName()));
		}
	}
	@Override
	public void start() {
		try {
			init();
			recieve();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

