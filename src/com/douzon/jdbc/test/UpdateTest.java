package com.douzon.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class UpdateTest 
{
	public static void main(String[] args) 
	{
		boolean result = update("안대혁", "m", "Fluffy");
		System.out.println(result);
	}
	
	public static boolean update(String owner, String gender, String name)
	{
		boolean result = false;
		
		Connection conn = null;
		java.sql.PreparedStatement pstmt = null; // 쿼리를 준비시켜놓음
		
		try 
		{
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // 제대로 로딩됐는지 확인
			
			// 2. 연결하기 (Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 주의할점 finally에서 자원정리
			System.out.println("연결 성공");

						
			// 3. SQL문 준비
			String sql = "update pet" + 
						 " set owner=?," +  
					     " gender=?" +  
						 " where name=?"; // JDBC에 준비시켜놓음	실제값을 바인딩시키고 날려야함
			pstmt = conn.prepareStatement(sql);
			
			// 4. binding
			pstmt.setString(1, owner);
			pstmt.setString(2, gender);
			pstmt.setString(3, name);
			
			// 5. SQL문 실행
			int count = pstmt.executeUpdate(); // 가로안에 sql 넣으면 에러
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
				if (pstmt != null)
					pstmt.close();
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

}
