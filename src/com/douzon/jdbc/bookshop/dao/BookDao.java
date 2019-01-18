package com.douzon.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzon.jdbc.bookshop.vo.BookVo;

public class BookDao 
{	
	public static boolean updateStatus(BookVo bookVo)
	{
		Connection conn = null;
		boolean result = false;
		PreparedStatement pstmt = null; 
		
		try 
		{
			conn = getConnection();
			
			String sql = "update" +
						 "	book" +
						 "		set status = ? where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  bookVo.getStatus());
			pstmt.setLong(2, bookVo.getNo());
			
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
	
	public boolean insert(BookVo bookVo)
	{
		Connection conn = null;
		boolean result = false;
		PreparedStatement pstmt = null; 
		
		try 
		{
			conn = getConnection();
			
			String sql = "insert" +
						 "	into book" +
						 "		values(null, ?, '대여가능', ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  bookVo.getTitle());
			pstmt.setLong(2, bookVo.getAuthorNo());
			
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
	
	public List<BookVo> getList()
	{
		List<BookVo> list = new ArrayList<BookVo>();
		
		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select a.no, a.title, a.status, b.name" +
						 "	from book a, author b" +
						 "		where a.author_no = b.no" +
						 "	order by a.no asc";  // 반드시 세미콜론 빼야함 쿼리에
			
			rs = stmt.executeQuery(sql); // executeQuery 결과가있기때문에 받아야함, executeUpdate
			
			while (rs.next())
			{
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				String authorName = rs.getString(4);

				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setStatus(status);
				vo.setAuthorName(authorName);
				
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
