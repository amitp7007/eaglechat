package com.eagle.chat.host.discovery;

public class DicoveryThread implements Runnable{

	DicoveryObject discoveryType = null;
	public DicoveryThread(DicoveryObject obj) {
		this.discoveryType = obj;
	}
	@Override
	public void run() {
		discoveryType.start();
	}

}
