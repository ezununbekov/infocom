package com.info.repo;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.info.model.Company;
import com.info.model.Employee;
import com.info.util.SessionFactoryUtil;

@Stateless
public class EmployeeDaoImpl implements EmployeeDao {
	private Session session;
	
	public List<Employee> getAllEmployees(Integer compId){
		Transaction transaction = null;
		List<Employee> result = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Employee e where e.company.id = ?").setParameter(0, compId);
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
	
	public Employee getEmployee(Integer empId){
		Employee employee = null;
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Employee e where e.id = ?").setParameter(0, empId);
			employee = (Employee)query.getSingleResult();
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
		return employee;
	}
	
	public void addEmployee(Employee emp, Integer compId){
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Company comp = session.load(Company.class, compId);
			emp.setCompany(comp);
			comp.getEmployees().add(emp);
			session.update(comp);
			session.save(emp);
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
	
	public void updateEmployee(Employee emp, Integer compId){
		Transaction transaction = null;
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			Company comp = session.load(Company.class, compId);
			emp.setCompany(comp);
			session.update(comp);
			session.update(emp);
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
	
	public void deleteEmployee(Integer empId){
		Transaction transaction = null;
		Employee employee = getEmployee(empId);
		try{
			session = SessionFactoryUtil.buildSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(employee);
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
