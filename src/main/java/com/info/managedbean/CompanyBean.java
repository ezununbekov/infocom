package com.info.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;

import com.info.model.Company;
import com.info.repo.CompanyDao;
import com.info.repo.CompanyDaoImpl;


/**
 * Managed bean for handling company information.
 *
 */
@ManagedBean(name="companyBean", eager=true)
@RequestScoped
public class CompanyBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contains all companies stored in database.
	 */
	private List<Company> companies;
	
	/**
	 * Holds information about company.
	 */
	private Company company;
	
	/**
	 * Holds id of company.
	 * Set by parameter compId.
	 */
	@ManagedProperty(value = "#{param.compId}")
	private Integer id;
	

	//@EJB
	/**
	 * Provider of DAO methods for company.
	 */
	private CompanyDao companyDao = new CompanyDaoImpl();
	
	/**
	 * Default constructor.
	 */
	public CompanyBean(){}
	
	/**
	 * Getter method for {@link #companies}.
	 * @return List of all companies stored in database.
	 */
	public List<Company> getCompanies() {
		if(companies == null)
			companies = companyDao.getAllCompanies();
		return companies;
	}

	/**
	 * Setter method for {@link #companies}.
	 * @param companies List of companies to set.
	 */
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	/**
	 * Getter method for {@link #id}.
	 * @return id of company.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter method for {@link #id}.
	 * @param id id of company to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 *Getter method for {@link #company}.
	 *If {@link #id} is set to value other than {@code null} company with that id
	 *is retrieved from database. Otherwise, new company is initialized.
	 * @return company with {@link #id} if that id is not null, or newly initialized company, otherwise.
	 */
	public Company getCompany(){
		if(id != null)
			company = companyDao.getCompany(id);
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
	 * Method for adding new company.
	 * Probably, for this purpose it is better to use {@code void addNewCompany(ActionEvent ae)}.
	 * @return company details view in case of successful company addition, or add company view if new company has non-unique license field.
	 */
	public String addNewCompany(){
		try{
			companyDao.addCompany(company);
		} catch(ConstraintViolationException cve){
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("nonunique", "1");
			return "companyAdd.xhtml";
		} 
		return "company.xhtml?faces-redirect=true&compId="+company.getId();
	}
	
	/**
	 * Method for updating a company.
	 * Probably, for this purpose it is better to use {@code void updateCompany(ActionEvent ae)}.
	 * @return company details view in case of successful company update, or edit company view if company being edited has non-unique license field. 
	 */
	public String updateCompany(){
		try{
			companyDao.updateCompany(company);
		} catch(ConstraintViolationException cve){
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("nonunique", "1");
			return "companyEdit.xhtml";
		}
		return "company.xhtml?faces-redirect=true&compId="+company.getId();
	}
	
	/**
	 * Method for deleting a company.
	 * Probably, for this purpose it is better to use {@code void deleteCompany(ActionEvent ae)}. 
	 * Also, need to think about a way to handle situation in which company to be deleted has one or more employees.
	 * @return list of all companies view in case of successful company deletion.
	 */
	public String deleteCompany(){
		try{
			companyDao.deleteCompany(company.getId());
		} catch(ConstraintViolationException cve){
			//TODO: handle company has employees 
			return "company.xhtml?faces-redirect=true&compId="+company.getId();
		}
		
		return "companies.xhtml?faces-redirect=true";
	}
}
