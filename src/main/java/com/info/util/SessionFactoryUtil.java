package com.info.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.info.model.Company;
import com.info.model.Employee;
import com.info.model.File;
import com.info.model.Legalform;
import com.info.model.Ownership;

public class SessionFactoryUtil {
	/**
	 * Factory for {@link org.hibernate.Session}
	 */
	private static SessionFactory sessionFactory;
	
	private SessionFactoryUtil(){}
	
	static{
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Legalform.class)
											   .addAnnotatedClass(Ownership.class)
											   .addAnnotatedClass(Company.class)
											   .addAnnotatedClass(Employee.class)
											   .addAnnotatedClass(File.class);
		ServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	/**
	 * Gets single instance of {@link #sessionFactory}.
	 * @return single instance of {@link #sessionFactory}.
	 */
	public static SessionFactory buildSessionFactory(){
		return sessionFactory;
	}
}
