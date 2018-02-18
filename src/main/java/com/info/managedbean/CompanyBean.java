package com.info.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.info.model.Company;
import com.info.repo.CompanyDao;
import com.info.repo.CompanyDaoImpl;

@ManagedBean(name="companyBean", eager=true)
@RequestScoped
public class CompanyBean {
	private List<Company> companies;

	private Company company = new Company();
	
	@ManagedProperty(value = "#{param.compId}")
	private String id;
	
	//@EJB
	CompanyDao companyDao = new CompanyDaoImpl();
	
	@PostConstruct
	public void init(){
		companies = companyDao.getAllCompanies();
	}
	
	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Company getCompany(){
		if(id != null)
			company = companyDao.getCompany(new Integer(id));
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String addNewCompany(){	
		companyDao.addCompany(company);
		return "companies.xhtml?faces-redirect=true";
	}
	
	public String updateCompany(){
		companyDao.updateCompany(company);
		//this.setId(String.valueOf(company.getId()));
		return "company.xhtml?faces-redirect=true&compId="+company.getId();
	}
	
	public String deleteCompany(){
		companyDao.deleteCompany(new Integer(id));
		return "companies.xhtml?faces-redirect=true";
	}
}
