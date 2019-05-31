package com.cdac.client;

import java.util.List;


import javax.persistence.criteria.*;
import org.hibernate.*;
import org.hibernate.query.Query;

import com.cdac.entity.Employee;

import comcdac.util.HibernateUtil;

public class ShowAll {

	public static void main(String[] args) {
		Session session =null;// sessionFactory.openSession();
		Transaction tx=null;
		try{
			session=HibernateUtil.getSession();
			tx = session.beginTransaction();

	         CriteriaBuilder builder = session.getCriteriaBuilder();
	         CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
	         Root<Employee> root = query.from(Employee.class);
	         query.select(root);
	         Query<Employee> q=session.createQuery(query);
	         List<Employee> employees=q.getResultList();
	         for (Employee employee : employees) {
	            System.out.println(employee);
	         }
	         tx.commit();		
		}finally{
		session.close();
		
        }


	}

}
