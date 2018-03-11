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

/**
 * Implementation of {@link com.info.repo.EmployeeDao}.
 */
@Stateless
public class EmployeeDaoImpl implements EmployeeDao {
	/**
	 * The main runtime interface between a Java application and Hibernate.
	 */
	private Session session;
	
	/**
	 * Gets all {@link com.info.model.Employee}s contained in {@link com.info.model.Company}
	 * by {@link com.info.model.Company#id}.
	 * @param compId id of company.
	 * @return List of all {@link com.info.model.Employee}s contained in {@link com.info.model.Company}.
	 */
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
	
	/**
	 * Gets {@link com.info.model.Employee} by its {@link com.info.model.Employee#id}.
	 * @param empId id of employee.
	 * @return {@link com.info.model.Employee} by its {@link com.info.model.Employee#id}.
	 */
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
	
	/**
	 * Adds {@link com.info.model.Employee} to {@link com.info.model.Company} by 
	 * {@link com.info.model.Company#id}.
	 * @param emp {@link com.info.model.Employee} to be stored.
	 * @param compId id of {@link com.info.model.Company} where employee will be contained.
	 */
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
	
	/**
	 * Updates {@link com.info.model.Employee} contained in {@link com.info.model.Company} by 
	 * {@link com.info.model.Company#id}.
	 * @param emp {@link com.info.model.Employee} to be updated.
	 * @param compId id of {@link com.info.model.Company} where employee is contained.
	 */
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
	
	/**
	 * Deletes {@link com.info.model.Employee} from database.
	 * @param empId {@link com.info.model.Employee#id} of {@link com.info.model.Employee} to be deleted.
	 */
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
