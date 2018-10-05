package com.eagle.threadpool.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.eagle.threadpool.common.Constants;

public class ThreadPoolImpl implements ThreadPool{

	private Lock lock = new ReentrantLock();
	private BlockingQueue<Runnable> taskQueue = null;
	private int queueSize  = Constants.DEFAULT_Q_SIZE;
	private int poolSize = Constants.DEFAULT_POOL_SIZE;
	private Thread thScheduler = null;

	protected ThreadPoolImpl(int size){
		poolSize = size;
		taskQueue = new LinkedBlockingQueue<Runnable>();
		initScheduler();
	}

	private void createWorker() {
		Thread worker = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Runnable task = null;
						synchronized (lock) {
							while(taskQueue.isEmpty()) {
								lock.wait();
							}
							task = taskQueue.poll();
							lock.notifyAll();
						}
						task.run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		worker.start();
	}
	private void initScheduler() {
		thScheduler = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while(true) {
					synchronized (lock) {					
						try {
							while(taskQueue.isEmpty()) {
								lock.wait();
							}
							if(count < poolSize) {
								createWorker();
							}else {
								break;
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						count++;
					}

					// TODO Auto-generated method stub

				}
			}
		});
		thScheduler.start();
	}
	private boolean addTask(Runnable task) {
		taskQueue.add(task);
		return true;

	}

	@Override
	public void submit(Runnable task)  {
		synchronized (lock){
			try {
				while(taskQueue.size() > queueSize) {
					lock.wait();
				}
				addTask(task);
				lock.notifyAll();
			}
			catch(InterruptedException ie) {

			}
		}
	}

}
