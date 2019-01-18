package com.douzon.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class SelectTest 
{
	public static void main(String[] args) 
	{
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		
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
			String sql = "select name, owner, birth from pet";  // 반드시 세미콜론 빼야함 쿼리에
			rs = stmt.executeQuery(sql); // executeQuery 결과가있기때문에 받아야함, executeUpdate
			
			// 5. 결과 가져오기
			// 문제는 쿼리가 자바안에들어있다	아주 긴 쿼리가문제 지금은 해결x 스프링에서해결 java코드와 xml코드를 분리
			
			while (rs.next())
			{
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String birth = rs.getString(3);
				
				System.out.println(name + ":" + owner + ":" + birth);
			}
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
				if (rs != null)
					rs.close();
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
	}

}
