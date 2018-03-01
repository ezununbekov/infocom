package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Company;
import com.info.model.Employee;

@Local
public interface EmployeeDao {
	Employee getEmployee(Integer empId);
	List<Employee> getAllEmployees(Integer compId);
	//Company getCompany(Integer empId);
	//List<Employee> getAllEmployees();
	void addEmployee(Employee emp, Integer compId);
	void updateEmployee(Employee emp, Integer compId);
	void deleteEmployee(Integer empId);
//	void deleteAllEmployees(Integer compId);
	//void deleteAllEmployees();
	
}
