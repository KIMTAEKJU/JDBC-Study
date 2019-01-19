package com.douzon.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douzone.jdbc.hr.vo.SalaryVo;

public class SalaryDao 
{
	public ArrayList<SalaryVo> salarySelect(SalaryVo saVo)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SalaryVo> list = new ArrayList<>();
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			System.out.println("연결 성공");
			
			String sql = "select concat(a.first_name, ' ', a.last_name), b.salary from" + 
						 "	employees a, salaries b" + 
						 "		where a.emp_no = b.emp_no and b.salary > ? and b.salary < ?" +
						 "			order by b.salary asc";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, saVo.getMinSalary());
			pstmt.setLong(2, saVo.getMaxSalary());
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				String name = rs.getString(1);
				long salary = rs.getLong(2);
				
				SalaryVo vo = new SalaryVo();
				vo.setName(name);
				vo.setSalary(salary);
				
				list.add(vo);
			}
			
			
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("드라이버 연결 실패 : " + e);
		} 
		catch (SQLException e) 
		{
			System.out.println("SQL 실패 : " + e);
		}
		finally 
		{
			try 
			{
				if (rs != null)
					rs.close();
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
		
		return list;
	}
}
