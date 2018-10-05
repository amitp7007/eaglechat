package com.eagle.threadpool.impl;

public class ThreadPoolProvider implements Provider<ThreadPool>{

	@Override
	public ThreadPool getNewThreadPool(int size) {
		return new ThreadPoolImpl(size);
	}

}
