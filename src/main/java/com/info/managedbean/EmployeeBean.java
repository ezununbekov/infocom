package com.info.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.info.model.Company;
import com.info.model.Employee;
import com.info.repo.CompanyDao;
import com.info.repo.CompanyDaoImpl;
import com.info.repo.EmployeeDao;
import com.info.repo.EmployeeDaoImpl;

@ManagedBean(name="employeeBean", eager=true)
@RequestScoped
public class EmployeeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Employee> employees;
	private Employee employee;
	private Company company;
	
	@ManagedProperty(value = "#{param.companyId}")
	private Integer compId;
	
	@ManagedProperty(value = "#{param.empId}")
	private Integer empId;
	
	

	
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	private CompanyDao companyDao = new CompanyDaoImpl();

	public List<Employee> getEmployees() {
		if(compId != null){
			employees = employeeDao.getAllEmployees(compId);
		} else{
			employees = new ArrayList<>();
		}
			
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Employee getEmployee() {
		if(empId != null)
			employee = employeeDao.getEmployee(empId);
		else if(employee == null)
			employee = new Employee();
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Company getCompany() {
		if(compId != null)
			company = companyDao.getCompany(compId);
		else if(company == null)
			company = new Company();
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String addNewEmployee(){
		//Employee employee = this.employee;
		//Company company = getCompany();
		employeeDao.addEmployee(employee, this.compId);
		return "employee.xhtml?faces-redirect=true&empId="+employee.getId()+"&companyId="+this.compId;
	}
	
	public String updateEmployee(){
		Company c = getCompany();
		employeeDao.updateEmployee(employee, this.compId);
		return "employee.xhtml?faces-redirect=true&empId="+employee.getId()+"&companyId="+this.compId;
	}
	
	public String deleteEmployee(){
		
		Employee e = getEmployee();
		Integer compId = e.getCompany().getId();
		
		employeeDao.deleteEmployee(this.empId);
		
		return "employees.xhtml?faces-redirect=true&companyId="+compId;
	}
}
