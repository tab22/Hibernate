package com.cdac.client;

import java.util.List;

import com.cdac.dao.EmpDao;
import com.cdac.dao.EmpDaoImpl;
import com.cdac.entity.Employee;

public class Test {

	public static void main(String[] args) {
		
		 getEmp();
		 showAllRecords();
		 update();
		 insert();
 	     delete();	
		
	}

	private static void delete() {
		EmpDao empDao= new EmpDaoImpl();
         empDao.delete(4);		
	}

	private static void insert() {
	       
		Employee e= new Employee(4, "Karuna", "Kale");
		EmpDao empDao= new EmpDaoImpl();
		empDao.insert(e);
		
		
	}

	private static void update() {
		
		EmpDao empDao= new EmpDaoImpl();
		Employee e= new Employee(2,"Rani","Pawar");
		empDao.update(e);
	}

	private static void showAllRecords() {
		EmpDao empDao= new EmpDaoImpl();
		      List<Employee>eList=empDao.showAll();
	        for(Employee e:eList){
	        	System.out.println(e);
	        }
	
	}

	private static void getEmp() {
		  EmpDao empDao= new EmpDaoImpl();
		   Employee e=    empDao.getEmployee(2);
		   System.out.println(e);
	}

}
