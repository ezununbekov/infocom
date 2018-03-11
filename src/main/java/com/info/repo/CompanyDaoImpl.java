package com.info.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.exception.ConstraintViolationException;

import com.info.model.Company;
import com.info.util.SessionFactoryUtil;

/**
 * Implementation of {@link com.info.repo.CompanyDao}.
 */
@Stateless
public class CompanyDaoImpl implements CompanyDao{
	/**
	 * The main runtime interface between a Java application and Hibernate.
	 */
	private Session session;

	/**
	 * Gets {@link com.info.model.Company} by its {@link com.info.model.Company#id}.
	 * @param id id of company.
	 * @return {@link com.info.model.Company} by its {@link com.info.model.Company#id}.
	 */
	public Company getCompany(Integer id){
		Company company = null;
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Company c where c.id = ?");
			query.setParameter(0, id);
			company = (Company)query.getSingleResult();
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
		
		return company;
	}
	
	/**
	 * Gets all {@link com.info.model.Company}s stored in database.
	 * @return List of all {@link com.info.model.Company}s stored in database.
	 */
	public List<Company> getAllCompanies(){
		Transaction transaction = null;
		List<Company> result = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Company");
			result = query.list();
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
		return result;
	}
	
	/**
	 * Adds {@link com.info.model.Company} to database.
	 * @param company {@link com.info.model.Company} to be stored in database.
	 * @throws org.hibernate.exception.ConstraintViolationException if
	 * company to be stored has non-unique {@link com.info.model.Company#license} field.
	 */
	public void addCompany(Company company){
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(company);
			transaction.commit();
		} catch (ConstraintViolationException cve){
			if(transaction != null)
				transaction.rollback();
			throw cve;
		} catch (Exception e){
			e.printStackTrace();
			//TODO: add logger
			if(transaction != null)
				transaction.rollback();
		} finally{
			if(session != null)
				session.close();
		}
	}
	
	/**
	 * Updates {@link com.info.model.Company} stored in database.
	 * @param company {@link com.info.model.Company} to be updated.
	 * @throws org.hibernate.exception.ConstraintViolationException if
	 * company to be updated has non-unique {@link com.info.model.Company#license} field.
	 */
	public void updateCompany(Company company){
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.update(company);
			transaction.commit();
		} catch(Exception e){
			e.printStackTrace();
			//TODO: add logger
			if(transaction != null)
				transaction.rollback();
			if(e.getCause().getClass().equals(ConstraintViolationException.class))
				throw new ConstraintViolationException(e.getCause().getCause().getMessage(), (java.sql.SQLException)e.getCause().getCause(), null);
		} finally{
			if(session != null)
				session.close();
		}
	}
	
	/**
	 * Deletes {@link com.info.model.Company} from database.
	 * @param id {@link com.info.model.Company#id} of {@link com.info.model.Company} to be deleted.
	 * @throws org.hibernate.exception.ConstraintViolationException if
	 * company to be deleted has one or more {@link com.info.model.Employee}s AND 
	 * {@link com.info.model.Company#employees} field is not annotated with
	 * {@code @OnDelete(action=OnDeleteAction.CASCADE)}.
	 */
	public void deleteCompany(Integer id){
		Transaction transaction = null;
		Company company = getCompany(id);
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(company);
			transaction.commit();
		} catch(Exception e){
			e.printStackTrace();
			//TODO: add logger
			if(transaction != null)
				transaction.rollback();
			if(e.getCause().getClass().equals(ConstraintViolationException.class))
				throw new ConstraintViolationException(e.getCause().getCause().getMessage(), (java.sql.SQLException)e.getCause().getCause(), null);
		} finally{
			if(session != null)
				session.close();
		}
	}
}
