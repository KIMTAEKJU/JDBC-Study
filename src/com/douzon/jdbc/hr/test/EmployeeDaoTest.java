package com.douzon.jdbc.hr.test;

import java.util.List;

import com.douzon.jdbc.hr.dao.EmployeeDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class EmployeeDaoTest 
{
	public static void main(String[] args) 
	{
		selectTest("a");
	}
	
	public static void selectTest(String keyWord)
	{
		List<EmployeeVo> list = new EmployeeDao().select(keyWord);
		
		for (EmployeeVo vo : list)
			System.out.println(vo.getEmpNo() + " " + vo.getFirstName() + " " + vo.getLastName() + " " + vo.getHireDate());
	}

}
