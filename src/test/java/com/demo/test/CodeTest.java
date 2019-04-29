package com.demo.test;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class CodeTest {
	public static void main(String[] args) {
		try {
			PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();
			dataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
			dataSource.setURL("jdbc:oracle:thin:@//10.55.2.200:1521/dev1");
			dataSource.setUser("BDM");
			dataSource.setPassword("BDM");
			dataSource.setInitialPoolSize(5);
			dataSource.setMinPoolSize(5);
			dataSource.setMaxPoolSize(10);
			System.err.println(dataSource.getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
