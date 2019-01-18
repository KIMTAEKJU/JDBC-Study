package com.douzon.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertTest 
{
	public static void main(String[] args) 
	{
		boolean result = insert("마음이3", "또치", "dog", "f", "2010-10-10", null);
		System.out.println(result);
	}
	
	public static boolean insert(String name, String owner, String species, String gender, String birth, String death)
	{
		Connection conn = null;
		java.sql.Statement stmt = null;
		boolean result = false;
		
		try 
		{
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // 제대로 로딩됐는지 확인
			
			// 2. 연결하기 (Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 주의할점 finally에서 자원정리
			System.out.println("연결 성공");

						
			// 3. Statement 객체를 생성
			stmt = conn.createStatement();
			
			// 4. SQL문 실행
			String sql = " insert" + 
						" into pet" +  
						" values('" + name + "', '" + 
								      owner + "', '" + 
									  species + "', '" + 
									  gender + "', '" + 
									  birth + "', null)";  // JDBC문제점 실수할가능성이 높음
			int count = stmt.executeUpdate(sql); //  executeUpdate
			result = count == 1;
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
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
//	public static void insert(PetVO pet) // DB에게 값들을 한곳에모아 전달하는 객체
//	{
//		
//	}

}
