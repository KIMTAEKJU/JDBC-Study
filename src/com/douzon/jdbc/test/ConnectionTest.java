package com.douzon.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
	
		Connection conn = null;
		
		try 
		{
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // 제대로 로딩됐는지 확인
			
			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 주의할점 finally에서 자원정리
			
			System.out.println("연결 성공");
			
			
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("드라이버 로딩 실패 : " + e);
		} 
		catch (SQLException e) 
		{
			System.out.println("error : " + e);
		}
		finally 
		{
			try 
			{
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
