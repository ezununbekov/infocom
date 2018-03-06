package com.info.repo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.info.model.Company;
import com.info.model.File;
import com.info.util.SessionFactoryUtil;

public class FileDaoImpl implements FileDao {
	private Session session;
	
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
