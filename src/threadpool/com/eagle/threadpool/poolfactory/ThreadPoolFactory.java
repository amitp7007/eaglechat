package com.eagle.threadpool.poolfactory;

import com.eagle.threadpool.impl.Provider;
import com.eagle.threadpool.impl.ThreadPool;
import com.eagle.threadpool.impl.ThreadPoolProvider;

public class ThreadPoolFactory {

	private static ThreadPoolFactory service = new ThreadPoolFactory();
	private Provider<ThreadPool>  provider = new ThreadPoolProvider();
	
	public static ThreadPool createThreadPool(int size) {
		return service.provider.getNewThreadPool(size);
	}
}
