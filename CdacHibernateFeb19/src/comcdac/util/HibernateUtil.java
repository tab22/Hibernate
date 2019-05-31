package comcdac.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	static {
		if (sessionFactory == null) {
			try {

				registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(registry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}

	}

	public static Session getSession() {
		Session session = null;
		session = sessionFactory.openSession();
		return session;
	}

	public static void closeSessionFactory() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
		sessionFactory.close();
	}

}
