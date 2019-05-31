package com.cdac.client;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.cdac.entity.Employee;

import comcdac.util.HibernateUtil;

public class TestQuery {

	static void selectQuery(){
		Session session =null;// sessionFactory.openSession();
		Transaction tx=null;
		try{
			session=HibernateUtil.getSession();
			tx = session.beginTransaction();
			
		@SuppressWarnings("unchecked")
		org.hibernate.query.Query<Employee> query = session.createQuery("select e1 from Employee e1");
		            List<Employee> list=query.getResultList();
		            for(Employee emp:list){
		            	
		            	System.out.println(emp);
		            }
		}catch(HibernateException e){
			System.out.println(e);
		}finally{
			session.close();
		}
	}
	
	static void nativeQuery(){
		Session session =null;// sessionFactory.openSession();
		Transaction tx=null;
		try{
			session=HibernateUtil.getSession();
			tx = session.beginTransaction();
		   String q1="SELECT * FROM EMP100";
		   String q2="SELECT * FROM EMP100 where id >=3";
		 List<Employee> empList = session.createNativeQuery(q2,Employee.class).list();
		  /*NativeQuery<Employee>nQuery=  session.createNativeQuery(q2,Employee.class);
		  List<Employee> empList =nQuery.list();*/
	         for (Employee employee : empList) {
	            System.out.println(employee);
	         }
		}catch(HibernateException e ){
			System.out.println(e);
		}finally{
			session.close();
		}
	}
	public static void parameterQuery(){
		Session session =null;// sessionFactory.openSession();
		Transaction tx=null;
		try{
			session=HibernateUtil.getSession();
			tx = session.beginTransaction();
		          
				Query<Employee> query=session.createQuery("select e from Employee e where e.firstName like :name");
		             query.setParameter("name", "A%");
		             List<Employee> empList=query.getResultList();
	
		for(Employee emp:empList){
        	
        	System.out.println(emp);
        }
	}catch(HibernateException h){
		System.out.println(h);
		}finally{
			session.close();
		}
	}
	public static void main(String[] args) {
		selectQuery();
		parameterQuery();
		nativeQuery();
		/*Session session =null;// sessionFactory.openSession();
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
*/
	}

}
