package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Legalform;

/**
 * Interface which must be implemented by any class that is going to provide DAO functions related to legal form.
 */
@Local
public interface LegalformDao {
	/**
	 * Gets {@link com.info.model.Legalform} by its {@link com.info.model.Legalform#id}.
	 * @param id id of legal form.
	 * @return {@link com.info.model.Legalform} by its {@link com.info.model.Legalform#id}.
	 */
	Legalform getFormById(int id);
	
	/**
	 * Gets all {@link com.info.model.Legalform}s stored in database.
	 * @return List of all {@link com.info.model.Legalform}s stored in database.
	 */
	List<Legalform> getAllForms();
}
