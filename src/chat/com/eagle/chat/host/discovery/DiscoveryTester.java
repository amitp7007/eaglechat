package com.eagle.chat.host.discovery;

import java.io.IOException;

import com.eagle.chat.db.DataBaseItf;
import com.eagle.chat.db.DatabaseConectionProvider;
import com.eagle.chat.main.common.HostInfoUtility;
import com.eagle.chat.main.common.Message;
import com.eagle.chat.main.common.MessageCallback;

public class DiscoveryTester {

	static MessageCallback cl =  new MessageCallback() {
		@Override
		public void recieved(Message msg) {
			System.out.println(msg);
		}
		
		@Override
		public void error(String err) {
			System.out.println(err);
		}
	};
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		DataBaseItf db = DatabaseConectionProvider.getNewConnection();
		Thread recThread = new Thread(new DicoveryThread(new ClientInfoReciever(db, cl)));
		Thread broThread = new Thread(new DicoveryThread(new HostInfoBroadCaster(HostInfoUtility.gatherCurrentHostInfo())));
		Thread.sleep(500);
		recThread.start();
		broThread.start();
	}

}
