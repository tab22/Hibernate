package com.cdac.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entity.Employee;

import comcdac.util.HibernateUtil;

public class UpdateTest {

	public static void main(String[] args) {
		Session session = null;;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			int empno = 2;
			Employee e = session.get(Employee.class, empno);
			System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLastName());
			e.setFirstName("Vimal");
			session.save(e);
			tx.commit();
		} catch (HibernateException h) {
			System.out.println(h);
		} finally {
			session.close();
			HibernateUtil.closeSessionFactory();
		}
	}

}
