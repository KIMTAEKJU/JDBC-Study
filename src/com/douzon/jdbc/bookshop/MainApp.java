package com.douzon.jdbc.bookshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.douzon.jdbc.bookshop.dao.BookDao;
import com.douzon.jdbc.bookshop.vo.BookVo;
import com.douzon.jdbc.test.UpdateTest;

public class MainApp 
{
	public static void main(String[] args)
	{
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		long no = scanner.nextLong();
		scanner.close();
		
		rent(no);		
		displayBookInfo();
	}
	
	private static void rent(long no)
	{
		BookVo bookVo = new BookVo();
		bookVo.setStatus("대여중");
		bookVo.setNo(no);
		
		BookDao bookDao = new BookDao();
		bookDao.updateStatus(bookVo);
		bookDao.getBookName(no);
	}
	
	public static void displayBookInfo()
	{
		List<BookVo> list = new BookDao().getList();
		for (BookVo vo : list)
			System.out.println(vo);
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
