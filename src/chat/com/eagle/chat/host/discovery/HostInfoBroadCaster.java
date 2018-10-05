package com.eagle.chat.host.discovery;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.channels.DatagramChannel;

import com.eagle.chat.Constants;
import com.eagle.chat.main.common.HostInfo;

public class HostInfoBroadCaster implements DicoveryObject{
		private DatagramChannel channel = null;
		private InetAddress group = null;
		private byte[] buffer = null;	
		private final HostInfo info;
		
		public HostInfoBroadCaster(final HostInfo info) {
			this.info = info;
		}
		
		private byte[] getByteHostInfo() {
			byte[] buffer = null;
			ByteArrayOutputStream outS = new ByteArrayOutputStream();
			try {
				ObjectOutputStream objOtS = new ObjectOutputStream(outS);
				objOtS.writeObject(this.info);
				buffer = outS.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return buffer;
		}
		public void multicastInfo() throws IOException {
			group = InetAddress.getByName(Constants.GROUP_ADDRESS);
			channel = DatagramChannel.open(StandardProtocolFamily.INET).setOption(StandardSocketOptions.SO_REUSEADDR, true);
			channel.bind(new InetSocketAddress(Constants.BROADCAST_PORT));
			NetworkInterface ni = NetworkInterface.getByName("lo");
	        channel .setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
			
			buffer = getByteHostInfo();
			DatagramPacket packet  = new DatagramPacket(buffer, buffer.length, group, Constants.BROADCAST_PORT);
			channel.socket().send(packet);
		}

		@Override
		public void start() {			
			while(true) {
				try {
					Thread.sleep(1000*10);
					multicastInfo();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
}
