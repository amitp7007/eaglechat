package com.eagle.chat.main;

import java.io.IOException;
import java.util.Map;

import com.eagle.chat.db.DataBaseItf;
import com.eagle.chat.db.DatabaseConectionProvider;
import com.eagle.chat.host.discovery.ClientInfoReciever;
import com.eagle.chat.host.discovery.DicoveryThread;
import com.eagle.chat.host.discovery.HostInfoBroadCaster;
import com.eagle.chat.main.client.Client;
import com.eagle.chat.main.client.ClientFactory;
import com.eagle.chat.main.common.HostInfo;
import com.eagle.chat.main.common.HostInfoUtility;
import com.eagle.chat.main.common.Message;
import com.eagle.chat.main.common.MessageCallback;
import com.eagle.chat.main.server.Server;
import com.eagle.chat.main.server.ServerImpl;

public class ChattingThread implements Runnable{
	
	Client client = null;
	Server server = null;
	HostInfo hostInfo = null;
	HostInfoBroadCaster broadCaster = null;
	DataBaseItf db = null;
	//Se
	MessageCallback listener = new MessageCallback() {		
		@Override
		public void recieved(Message msg) {
			db.getAll().stream().forEach(n-> {
				
				System.out.println(((HostInfo)n).getHostName() +" : " + ((HostInfo)n).getHostAddress());
			});
		}
		
		@Override
		public void error(String err) {
			System.out.print("error :" + err);
		}
	};
	
	/**
	 * Thread will run every minute to broad cast current host info.
	 * this will help new client to find the host to communicate
	 */
	private void announce() {
		try {
			Thread clientAccpeterThread = new Thread(new DicoveryThread(new ClientInfoReciever(db, listener)));
			clientAccpeterThread.start();
			Thread selfAnounceThread = new Thread(new DicoveryThread(new HostInfoBroadCaster(HostInfoUtility.gatherCurrentHostInfo())));
			selfAnounceThread.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ChattingThread() {
		//client = ClientFactory.getClient(20000, "localhost");
		//server = new ServerImpl(listener);
		//hostInfo = HostInfoUtility.gatherCurrentHostInfo();
		// default it is in-memory db, 
		// one can set any relational database just by providing db type
		db = DatabaseConectionProvider.getNewConnection(); 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		announce();
		
		
		
	}

}
