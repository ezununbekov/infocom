package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Company;

/**
 * Interface which must be implemented by any class that is going to provide DAO functions related to company.
 */
@Local
public interface CompanyDao {
	/**
	 * Gets {@link com.info.model.Company} by its {@link com.info.model.Company#id}.
	 * @param id id of company.
	 * @return {@link com.info.model.Company} by its {@link com.info.model.Company#id}.
	 */
	Company getCompany(Integer id);
	
	/**
	 * Gets all {@link com.info.model.Company}s stored in database.
	 * @return List of all {@link com.info.model.Company}s stored in database.
	 */
	List<Company> getAllCompanies();
	
	/**
	 * Adds {@link com.info.model.Company} to database.
	 * @param company {@link com.info.model.Company} to be stored in database.
	 */
	void addCompany(Company company);
	
	/**
	 * Updates {@link com.info.model.Company} stored in database.
	 * @param company {@link com.info.model.Company} to be updated.
	 */
	void updateCompany(Company company);
	
	/**
	 * Deletes {@link com.info.model.Company} from database.
	 * @param id {@link com.info.model.Company#id} of {@link com.info.model.Company} to be deleted.
	 */
	void deleteCompany(Integer id);
}
