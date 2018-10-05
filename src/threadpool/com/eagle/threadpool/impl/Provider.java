package com.eagle.threadpool.impl;

public interface Provider<T> {
	public T getNewThreadPool(int size);
}
