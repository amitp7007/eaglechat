package com.eagle.chat.db;

import java.util.Set;

import com.eagle.chat.exception.DBException;
import com.eagle.chat.main.common.BaseEntity;

public interface DataBaseItf {
	
	public BaseEntity getData(String key)throws DBException ;
	public void insert(BaseEntity entity) throws DBException;
	default public Set<BaseEntity> getAll(){
		return null;
	}

}
