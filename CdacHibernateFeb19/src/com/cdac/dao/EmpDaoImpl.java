package com.cdac.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.cdac.entity.Employee;

import comcdac.util.HibernateUtil;

public class EmpDaoImpl implements EmpDao {

	@Override
	public void insert(Employee e) {
		
		Session session = null;;
		Transaction tx = null;
		int id=0;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
		    id=(Integer) session.save(e);
			System.out.println("Record Id "+id);
			tx.commit();
		} catch (HibernateException h) {
			System.out.println(h);
		} finally {
			session.close();
			HibernateUtil.closeSessionFactory();
		}

		
	}

	@Override
	public void delete(int empno) {
		
		Session session = null;;
		Transaction tx = null;

		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			Employee e = session.get(Employee.class, empno);
			System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLastName());
			session.delete(e);
			//session.delete("Employee.class", empno);
			/*Employee e=  session.load(Employee.class,empno);
			session.remove(e);*/

			tx.commit();
		} catch (HibernateException h) {
			System.out.println(h);
		} finally {
			session.close();
			//HibernateUtil.closeSessionFactory();
		}
	
	}

	@Override
	public void update(Employee e) {
		Session session = null;;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			int empno = e.getId();
			Employee emp = session.get(Employee.class, empno);
			System.out.println(e.getId() + " " + e.getFirstName() + " " + e.getLastName());
			session.save(emp);
			tx.commit();
		} catch (HibernateException h) {
			System.out.println(h);
		} finally {
			session.close();
			//HibernateUtil.closeSessionFactory();
		}	
	}

	@Override
	public List<Employee> showAll() {
		Session session =null;// sessionFactory.openSession();
		Transaction tx=null;
		List<Employee> eList=null;
		try{
			session=HibernateUtil.getSession();
			tx = session.beginTransaction();

	         CriteriaBuilder builder = session.getCriteriaBuilder();
	         CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
	         Root<Employee> root = query.from(Employee.class);
	         query.select(root);
	         Query<Employee> q=session.createQuery(query);
	         eList=q.getResultList();
	       /*  for (Employee employee : eList) {
	            System.out.println(employee);
	         }*/
	         tx.commit();		
		}finally{
		session.close();
		//HibernateUtil.closeSessionFactory();
        }

        return eList;
	}

	@Override
	public Employee getEmployee(int id) {
		Session session =null;// sessionFactory.openSession();
		Employee e=null;
		try{
		session = HibernateUtil.getSession();
        
		 e=  session.get(Employee.class,id);
		//System.out.println(e.getId()+" "+e.getFirstName()+" "+e.getLastName());
        }catch(HibernateException h){
        	System.out.println(h);
        }finally{
		session.close();
		//HibernateUtil.closeSessionFactory();
        }
		return e;
	}

}
