package com.info.repo;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.info.model.Company;
import com.info.model.File;
import com.info.util.SessionFactoryUtil;

/**
 * Implementation of {@link com.info.repo.FileDao}.
 */
@Stateless
public class FileDaoImpl implements FileDao {
	/**
	 * The main runtime interface between a Java application and Hibernate.
	 */
	private Session session;
	
	/**
	 * Gets {@link com.info.model.File} by its {@link com.info.model.File#id}.
	 * @param fileId id of file.
	 * @return {@link com.info.model.File} by its {@link com.info.model.File#id}.
	 */
	public File getFile(Integer fileId){
		Transaction transaction = null;
		File file = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from File f where f.id = ?").setParameter(0, fileId);
			file = (File)query.getSingleResult();
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
		return file;
	}
	
	/**
	 * Gets all {@link com.info.model.File}s contained in {@link com.info.model.Company}
	 * by {@link com.info.model.Company#id}.
	 * @param compId id of company.
	 * @return List of all {@link com.info.model.File}s contained in {@link com.info.model.Company}.
	 */
	public List<File> getAllFiles(Integer compId){
		Transaction transaction = null;
		List<File> files = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from File f where f.company.id = ?").setParameter(0, compId);
			files = query.list();
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
		return files;
	}
	
	/**
	 * Adds {@link com.info.model.File} to {@link com.info.model.Company} by 
	 * {@link com.info.model.Company#id}.
	 * @param file {@link com.info.model.File} to be stored.
	 * @param compId id of {@link com.info.model.Company} where file will be contained.
	 */
	public void addFile(File file, Integer compId){
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Company comp = session.load(Company.class, compId);
			file.setCompany(comp);
			comp.getFiles().add(file);
			session.update(comp);
			session.save(file);
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
	
	/**
	 * Deletes {@link com.info.model.File} from database.
	 * @param fileId {@link com.info.model.File#id} of {@link com.info.model.File} to be deleted.
	 */
	public void deleteFile(Integer fileId){
		Transaction transaction = null;
		File file = getFile(fileId);
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(file);
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
