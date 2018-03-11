package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Ownership;

/**
 * Interface which must be implemented by any class that is going to provide DAO functions related to ownership form.
 */
@Local
public interface OwnershipDao {
	/**
	 * Gets {@link com.info.model.Ownership} by its {@link com.info.model.Ownership#id}.
	 * @param id id of ownership form.
	 * @return {@link com.info.model.Ownership} by its {@link com.info.model.Ownership#id}.
	 */
	Ownership getFormById(int id);
	
	/**
	 * Gets all {@link com.info.model.Ownership}s stored in database.
	 * @return List of all {@link com.info.model.Ownership}s stored in database.
	 */
	List<Ownership> getAllForms();
}
