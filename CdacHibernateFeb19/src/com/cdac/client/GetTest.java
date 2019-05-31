package com.cdac.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entity.Employee;

import comcdac.util.HibernateUtil;

public class GetTest {

	public static void main(String[] args) {
		Session session =null;// sessionFactory.openSession();
		try{
		session = HibernateUtil.getSession();
        int empno=2;
		Employee e=  session.get(Employee.class,empno);
		System.out.println(e.getId()+" "+e.getFirstName()+" "+e.getLastName());
        }catch(HibernateException h){
        	System.out.println(h);
        }finally{
		session.close();
		
        }
	}

}
