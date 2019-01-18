package com.douzon.jdbc.driver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements Driver 
{

	// 클래스 로딩이되면서 static 부분이 실행됨
	static
	{		
		try 
		{
			System.out.println("static code area");

			// 드라이버를 등록시켜줘야함 드라이버 매니저에게
			DriverManager.registerDriver(new MyDriver()); // 커넥션하면 등록된 드라이버에 커넥트
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		System.out.println(url);
		System.out.println(info);
		return new MyConnection();
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
