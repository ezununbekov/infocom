package com.info.repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.info.model.Legalform;
import com.info.util.SessionFactoryUtil;

/**
 * Implementation of {@link com.info.repo.LegalformDao}.
 */
@SuppressWarnings("deprecation")
@Stateless
public class LegalformDaoImpl implements LegalformDao{
	/**
	 * The main runtime interface between a Java application and Hibernate.
	 */
	private Session session;
	
	/**
	 * Gets {@link com.info.model.Legalform} by its {@link com.info.model.Legalform#id}.
	 * @param id id of legal form.
	 * @return {@link com.info.model.Legalform} by its {@link com.info.model.Legalform#id}.
	 */
	@Override
	public Legalform getFormById(int id){
		Legalform form = null;
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Legalform l where l.id = ?");
			query.setParameter(0, id);
			form = (Legalform)query.getSingleResult();
			transaction.commit();
		} catch(Exception e){
			e.printStackTrace();
			//TODO: add logger
			if(transaction != null)
				transaction.rollback();
		} finally{
			if(session != null)
				session.close();
		}
		return form;
	}
	
	/**
	 * Gets all {@link com.info.model.Legalform}s stored in database.
	 * @return List of all {@link com.info.model.Legalform}s stored in database.
	 */
	@SuppressWarnings("unchecked")
	public List<Legalform> getAllForms(){
		Transaction transaction = null;
		List<Legalform> allForms = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Legalform");
			allForms = query.list();
			transaction.commit();
		} catch(Exception e){
			e.printStackTrace();
			//TODO: add logger
			if(transaction != null)
				transaction.rollback();
		} finally{
			if(session != null)
				session.close();
		}
		return allForms;
	}
}
