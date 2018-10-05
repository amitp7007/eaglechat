package com.eagle.chat.db;

public class DatabaseConectionProvider {
	public static DataBaseItf getNewConnection(String dbType) {
		switch(dbType) {
		case "inmemory":
			return new InMemoryDB();
		case "oracle":
			return new OracleDB();
		case "mysql": 
			return new MysqlDB();
		default:
			return new InMemoryDB();
		}
	}
	public static DataBaseItf getNewConnection() {
		return getNewConnection("");
	}
}
