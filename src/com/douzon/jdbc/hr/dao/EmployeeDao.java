package com.douzon.jdbc.hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDao 
{
	public ArrayList<EmployeeVo> select(String keyWord)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeVo> list = new ArrayList<EmployeeVo>();
		
		try 
		{
			// 1. 클래스 로딩
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/employees";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			String sql = "select emp_no, first_name, last_name, hire_date" + 
						 "	from employees" + 
						 "		where first_name like ('%" + keyWord + "%') or last_name like ('%" + keyWord + "%')";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				long empNo = rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);
				
				EmployeeVo emVo = new EmployeeVo();
				emVo.setEmpNo(empNo);
				emVo.setFirstName(firstName);
				emVo.setLastName(lastName);
				emVo.setHireDate(hireDate);
				
				list.add(emVo);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("드라이버 어쩌구 실패 : " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("쿼리안감 : " + e);
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
}
