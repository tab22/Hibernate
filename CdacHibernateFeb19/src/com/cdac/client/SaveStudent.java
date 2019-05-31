package com.cdac.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.cdac.entity.CdacStudent;

public class SaveStudent {
	//Create typesafe ServiceRegistry object    
			private static StandardServiceRegistry standardRegistry;
			private static SessionFactory sessionFactory;

	public static void main(String[] args) 
	{
		
			if (sessionFactory == null) {
				try {

					 standardRegistry = new StandardServiceRegistryBuilder()
							.configure("hibernate.cfg.xml").build();
					Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
					sessionFactory = metaData.getSessionFactoryBuilder().build();

				} catch (Exception e) {
					e.printStackTrace();
					if (standardRegistry != null) {
						StandardServiceRegistryBuilder.destroy(standardRegistry);
					}
				}
			}
			if (sessionFactory != null) {
				System.out.println("sf created");
			} else {
				System.out.println("sf not created");
			}
			Session session =null;// sessionFactory.openSession();
			Transaction tx = null;
			Integer id = null;
			try {
				session = sessionFactory.openSession();
				tx = session.beginTransaction();
				
				CdacStudent s=  new CdacStudent();
				s.setRollno(1);
				s.setName("Teena");
				s.setMarks(25);
				id=(Integer)session.save(s);
				System.out.println("Record Id "+id);
			     tx.commit();
			
			} catch (HibernateException hb) {

				hb.printStackTrace();
				tx.rollback();
			} finally {

				session.close();
	            sessionFactory.close();
			}

	}

}

