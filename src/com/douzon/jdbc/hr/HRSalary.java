package com.douzon.jdbc.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.douzon.jdbc.hr.dao.SalaryDao;
import com.douzone.jdbc.hr.vo.EmployeeVo;
import com.douzone.jdbc.hr.vo.SalaryVo;

public class HRSalary 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		
		long userInputMin = scan.nextLong();
		long userInputMax = scan.nextLong();
		
		printList(searchSalary(userInputMin, userInputMax));
		
	}
	
	public static ArrayList<SalaryVo> searchSalary(long inputMin, long inputMax)
	{
		SalaryVo vo = new SalaryVo();
		vo.setMinSalary(inputMin);
		vo.setMaxSalary(inputMax);
		return new SalaryDao().salarySelect(vo);
	}
	
	public static void printList(List<SalaryVo> list)
	{
		for (SalaryVo vo : list)
			System.out.println(vo.getName() + " " + vo.getSalary());
	}

}
