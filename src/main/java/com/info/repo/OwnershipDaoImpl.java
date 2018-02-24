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

import com.info.model.Ownership;
import com.info.util.SessionFactoryUtil;

@SuppressWarnings("deprecation")
@Stateless
public class OwnershipDaoImpl implements OwnershipDao{
	
	private Session session;
	
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
