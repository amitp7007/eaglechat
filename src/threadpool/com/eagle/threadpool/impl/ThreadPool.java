package com.eagle.threadpool.impl;

public interface ThreadPool {
	void submit(Runnable task);
}
