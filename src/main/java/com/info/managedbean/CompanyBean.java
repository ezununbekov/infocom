package com.info.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;

import com.info.model.Company;
import com.info.repo.CompanyDao;
import com.info.repo.CompanyDaoImpl;

@ManagedBean(name="companyBean", eager=true)
@RequestScoped
public class CompanyBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Company> companies;
	private Company company;
	@ManagedProperty(value = "#{param.compId}")
	private Integer id;
	

	//@EJB
	CompanyDao companyDao = new CompanyDaoImpl();
	
//	@PostConstruct
//	public void init(){
//		companies = companyDao.getAllCompanies();
//	}
	
	public List<Company> getCompanies() {
		if(companies == null)
			companies = companyDao.getAllCompanies();
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getCompany(){
		if(id != null)
			company = companyDao.getCompany(id);
		else if(company == null)
			company = new Company();
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String addNewCompany(){
		try{
			companyDao.addCompany(company);
		} catch(ConstraintViolationException cve){
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("nonunique", "1");
			return "companyAdd.xhtml";
		} 
		return "company.xhtml?faces-redirect=true&compId="+company.getId();
	}
	
	public String updateCompany(){
		try{
			companyDao.updateCompany(company);
		} catch(ConstraintViolationException cve){
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("nonunique", "1");
			return "companyEdit.xhtml";
		}
		return "company.xhtml?faces-redirect=true&compId="+company.getId();
	}
	
	public String deleteCompany(){
		companyDao.deleteCompany(company.getId());
		return "companies.xhtml?faces-redirect=true";
	}
}
