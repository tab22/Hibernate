package com.cdac.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entity.Employee;

import comcdac.util.HibernateUtil;

public class DeleteTest {

	public static void main(String[] args) {
		Session session = null;;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			int empno = 4;
			Employee e = session.get(Employee.class, empno);
			System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLastName());
			session.delete(e);
			//session.delete("Employee.class", empno);
			/*Employee e=  session.load(Employee.class,empno);
			session.remove(e);*/

			tx.commit();
		} catch (HibernateException h) {
			
			System.out.println(h);
		    tx.rollback();
		} finally {
			session.close();
			HibernateUtil.closeSessionFactory();
		}

	}

}
