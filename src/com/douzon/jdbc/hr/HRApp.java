package com.douzon.jdbc.hr;

import java.util.List;
import java.util.Scanner;

import com.douzon.jdbc.hr.dao.EmployeeDao;
import com.douzon.jdbc.hr.test.EmployeeDaoTest;
import com.douzone.jdbc.hr.vo.EmployeeVo;

public class HRApp 
{
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		
		String userInput = scan.nextLine();
		
		printList(search(userInput));
	}
	
	public static List<EmployeeVo> search(String userInput)
	{
		return new EmployeeDao().select(userInput);
	}
	
	public static void printList(List<EmployeeVo> list)
	{
		for (EmployeeVo vo : list)
			System.out.println(vo.getEmpNo() + " " + vo.getFirstName() + " " + vo.getLastName() + " " + vo.getHireDate());
	}

}
