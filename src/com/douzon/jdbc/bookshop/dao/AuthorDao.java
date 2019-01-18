package com.douzon.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.jdbc.bookshop.vo.AuthorVo;

public class AuthorDao 
{
	public boolean insert(AuthorVo authorVo)
	{
		Connection conn = null;
		boolean result = false;
		PreparedStatement pstmt = null; 
		
		try 
		{
			conn = getConnection();
			
			String sql = "insert" + 
					"  into author" + 
					" values (null, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, authorVo.getName());
			pstmt.setString(2, authorVo.getBio());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
			
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
	
	public static List<AuthorVo> getList()
	{
		List<AuthorVo> list = new ArrayList<AuthorVo>();
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select no, name, bio from author";  // 반드시 세미콜론 빼야함 쿼리에
			rs = stmt.executeQuery(sql); // executeQuery 결과가있기때문에 받아야함, executeUpdate
			
			while (rs.next())
			{
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String bio = rs.getString(3);

				AuthorVo vo = new AuthorVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setBio(bio);
				
				list.add(vo);
			}
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
		return list;
	}
	
	private static Connection getConnection() throws SQLException
	{
		Connection conn = null;
		
		try 
		{
			// 1. JDBC Driver(MySQL) 로딩
			Class.forName("com.mysql.jdbc.Driver"); // 제대로 로딩됐는지 확인
			
			// 2. 연결하기 (Connection 객체 얻어오기)
			String url = "jdbc:mysql://localhost:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
		return conn;
	}
}
