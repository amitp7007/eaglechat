package com.eagle.threadpool.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.eagle.threadpool.impl.ThreadPool;
import com.eagle.threadpool.poolfactory.ThreadPoolFactory;

public class Main {

	static Runnable task = new Runnable() {
		volatile int k = 1;
		@Override
		public void run() {
			for(int i = 0; i < 3; i++) {
				System.out.println("id " +(k++) +":" + Thread.currentThread().getName() + " : " +(i +1));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(5);
		service.execute(task);
		Thread.sleep(5000);
		//service.submit(task);
		
		startSelfTest();
	}
	public static void startSelfTest() {
		ThreadPool pool = ThreadPoolFactory.createThreadPool(5);
		for(int i = 0; i < 2; i++) {
			pool.submit(task);
		}
	}

}
