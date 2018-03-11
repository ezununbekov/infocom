package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Company;
import com.info.model.Employee;

/**
 * Interface which must be implemented by any class that is going to provide DAO functions related to employee.
 */
@Local
public interface EmployeeDao {
	/**
	 * Gets {@link com.info.model.Employee} by its {@link com.info.model.Employee#id}.
	 * @param empId id of employee.
	 * @return {@link com.info.model.Employee} by its {@link com.info.model.Employee#id}.
	 */
	Employee getEmployee(Integer empId);
	
	/**
	 * Gets all {@link com.info.model.Employee}s contained in {@link com.info.model.Company}
	 * by {@link com.info.model.Company#id}.
	 * @param compId id of company.
	 * @return List of all {@link com.info.model.Employee}s contained in {@link com.info.model.Company}.
	 */
	List<Employee> getAllEmployees(Integer compId);
	
	/**
	 * Adds {@link com.info.model.Employee} to {@link com.info.model.Company} by 
	 * {@link com.info.model.Company#id}.
	 * @param emp {@link com.info.model.Employee} to be stored.
	 * @param compId id of {@link com.info.model.Company} where employee will be contained.
	 */
	void addEmployee(Employee emp, Integer compId);
	
	/**
	 * Updates {@link com.info.model.Employee} contained in {@link com.info.model.Company} by 
	 * {@link com.info.model.Company#id}.
	 * @param emp {@link com.info.model.Employee} to be updated.
	 * @param compId id of {@link com.info.model.Company} where employee is contained.
	 */
	void updateEmployee(Employee emp, Integer compId);
	
	/**
	 * Deletes {@link com.info.model.Employee} from database.
	 * @param empId {@link com.info.model.Employee#id} of {@link com.info.model.Employee} to be deleted.
	 */
	void deleteEmployee(Integer empId);
}
