package com.eagle.chat.main;

public class Main {


	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		new Thread(new ChattingThread()).start();
		
	}

}
