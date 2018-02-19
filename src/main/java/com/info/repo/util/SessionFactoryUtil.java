package com.info.repo.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.info.model.Legalform;
import com.info.model.Ownership;

public class SessionFactoryUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory buildSessionFactory(){
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(Legalform.class)
											   .addAnnotatedClass(Ownership.class);
		ServiceRegistry serviceRegistry =
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
