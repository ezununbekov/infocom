package com.info.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.info.model.Company;
import com.info.repo.util.SessionFactoryUtil;
@Stateless
public class CompanyDaoImpl implements CompanyDao{
	private Session session;

	public Company getCompany(int id){
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
	
	public void deleteCompany(int id){
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
		} finally{
			if(session != null)
				session.close();
		}
	}
}
