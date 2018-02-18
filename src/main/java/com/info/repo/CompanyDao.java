package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Company;

@Local
public interface CompanyDao {
	Company getCompany(int id);
	List<Company> getAllCompanies();
	void addCompany(Company company);
	void updateCompany(Company company);
	void deleteCompany(int id);
}
