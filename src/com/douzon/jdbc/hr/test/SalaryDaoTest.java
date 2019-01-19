package com.douzon.jdbc.hr.test;

import java.util.ArrayList;

import com.douzon.jdbc.hr.dao.SalaryDao;
import com.douzone.jdbc.hr.vo.SalaryVo;

public class SalaryDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		salaryDaoTest();
	}
	
	public static void salaryDaoTest()
	{
		SalaryVo vo = new SalaryVo();
		vo.setMinSalary(2000);
		vo.setMaxSalary(50000);
		
		ArrayList<SalaryVo> list = new SalaryDao().salarySelect(vo);
		
		for (SalaryVo vo1 : list)
			System.out.println(vo1.getName() + " " + vo1.getSalary());
	}

}
