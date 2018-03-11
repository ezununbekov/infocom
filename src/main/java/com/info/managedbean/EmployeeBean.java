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


/**
 * Managed bean for handling employee information.
 *
 */
@ManagedBean(name="employeeBean", eager=true)
@RequestScoped
public class EmployeeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contains all employees of this {@link #company}. 
	 */
	private List<Employee> employees;
	
	/**
	 * Holds information about employee.
	 */
	private Employee employee;
	
	/**
	 * Holds information about company.
	 */
	private Company company;
	
	/**
	 * Holds id of company.
	 * Set by parameter companyId.
	 */
	@ManagedProperty(value = "#{param.companyId}")
	private Integer compId;
	
	/**
	 * Holds id of employee.
	 * Set by parameter empId.
	 */
	@ManagedProperty(value = "#{param.empId}")
	private Integer empId;
	
	/**
	 * Provider of DAO methods for employee.
	 */
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	
	/**
	 * Provider of DAO methods for company.
	 */
	private CompanyDao companyDao = new CompanyDaoImpl();
	
	/**
	 * Default constructor.
	 */
	public EmployeeBean(){}

	/**
	 * Getter method for {@link #employees}.
	 * If {@link #compId} is set to value other than {@code null} list of all employees of
	 * company with that id is retrieved from database. Otherwise, empty list is returned.
	 * @return List of all employees of {@link #company} if {@link #compId} is not null,
	 * or empty list, otherwise.
	 */
	public List<Employee> getEmployees() {
		if(compId != null){
			employees = employeeDao.getAllEmployees(compId);
		} else{
			employees = new ArrayList<>();
		}
			
		return employees;
	}

	/**
	 * Setter method for {@link #employees}.
	 * @param employees List of employees to set.
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * Getter method for {@link #compId}.
	 * @return id of company.
	 */
	public Integer getCompId() {
		return compId;
	}

	/**
	 * Setter method for {@link #compId}.
	 * @param compId id of company to set.
	 */
	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	/**
	 * Getter method for {@link #empId}.
	 * @return id of employee.
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * Setter method for {@link #empId}.
	 * @param empId id of employee to set.
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 *Getter method for {@link #employee}.
	 *If {@link #empId} is set to value other than {@code null} employee with that id
	 *is retrieved from database. Otherwise, new employee is initialized.
	 * @return employee with {@link #empId} if that id is not null, or newly initialized employee, otherwise.
	 */
	public Employee getEmployee() {
		if(empId != null)
			employee = employeeDao.getEmployee(empId);
		else if(employee == null)
			employee = new Employee();
		return employee;
	}

	/**
	 * Setter method for {@link #employee}.
	 * @param employee employee to set.
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 *Getter method for {@link #company}.
	 *If {@link #compId} is set to value other than {@code null} company with that id
	 *is retrieved from database. Otherwise, new company is initialized.
	 * @return company with {@link #compId} if that id is not null, or newly initialized company, otherwise.
	 */
	public Company getCompany() {
		if(compId != null)
			company = companyDao.getCompany(compId);
		else if(company == null)
			company = new Company();
		return company;
	}

	/**
	 * Setter method for {@link #company}.
	 * @param company company to set.
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	/**
	 * Method for adding new employee.
	 * Probably, for this purpose it is better to use {@code void addNewEmployee(ActionEvent ae)}.
	 * @return employee details view in case of successful employee addition.
	 */
	public String addNewEmployee(){
		employeeDao.addEmployee(employee, this.compId);
		return "employee.xhtml?faces-redirect=true&empId="+employee.getId()+"&companyId="+this.compId;
	}
	
	/**
	 * Method for updating an employee.
	 * Probably, for this purpose it is better to use {@code void updateEmployee(ActionEvent ae)}.
	 * @return employee details view in case of successful employee update. 
	 */
	public String updateEmployee(){
		employeeDao.updateEmployee(employee, this.compId);
		return "employee.xhtml?faces-redirect=true&empId="+employee.getId()+"&companyId="+this.compId;
	}
	
	/**
	 * Method for deleting an employee.
	 * Probably, for this purpose it is better to use {@code void deleteEmployee(ActionEvent ae)}.
	 * @return list of all employees view in case of successful employee deletion.
	 */
	public String deleteEmployee(){
		
		Employee e = getEmployee();
		Integer compId = e.getCompany().getId();
		
		employeeDao.deleteEmployee(this.empId);
		
		return "employees.xhtml?faces-redirect=true&companyId="+compId;
	}
}
