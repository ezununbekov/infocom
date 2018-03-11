package com.info.repo;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.info.model.Ownership;
import com.info.util.SessionFactoryUtil;

/**
 * Implementation of {@link com.info.repo.OwnershipDao}.
 */
@SuppressWarnings("deprecation")
@Stateless
public class OwnershipDaoImpl implements OwnershipDao{
	/**
	 * The main runtime interface between a Java application and Hibernate.
	 */
	private Session session;
	
	/**
	 * Gets {@link com.info.model.Ownership} by its {@link com.info.model.Ownership#id}.
	 * @param id id of ownership form.
	 * @return {@link com.info.model.Ownership} by its {@link com.info.model.Ownership#id}.
	 */
	@Override
	public Ownership getFormById(int id){
		Ownership form = null;
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Ownership o where o.id = ?");
			query.setParameter(0, id);
			form = (Ownership)query.getSingleResult();
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
	 * Gets all {@link com.info.model.Ownership}s stored in database.
	 * @return List of all {@link com.info.model.Ownership}s stored in database.
	 */
	@SuppressWarnings("unchecked")
	public List<Ownership> getAllForms(){
		Transaction transaction = null;
		List<Ownership> allForms = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Ownership");
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
