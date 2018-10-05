package com.eagle.chat.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.eagle.chat.exception.DBException;
import com.eagle.chat.main.common.BaseEntity;
import com.eagle.chat.main.common.HostInfo;

public class InMemoryDB implements DataBaseItf{
	private Map<String, BaseEntity> hostDataBase = new HashMap<String, BaseEntity>();

	//not exposed fro creation from outside world
	protected InMemoryDB() {
	}
	
	@Override
	public BaseEntity getData(String key) {
		BaseEntity entity = hostDataBase.get(key);
		return entity;
	}

	@Override
	public void insert(BaseEntity entity) throws DBException {
		HostInfo host = null;
		if(entity instanceof HostInfo) {
			String name = ((HostInfo)entity).getHostName() +  ((HostInfo)entity).getUserName();
			hostDataBase.put(name, entity);
			return;
		}
		throw new DBException("Database validation failed: ");
	}

	@Override
	public Set<BaseEntity> getAll() {
		return hostDataBase.values().stream().collect(Collectors.toSet());
	}
}
