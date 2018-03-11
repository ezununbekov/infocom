package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.File;

/**
 * Interface which must be implemented by any class that is going to provide DAO functions related to file.
 */
@Local
public interface FileDao {
	/**
	 * Gets {@link com.info.model.File} by its {@link com.info.model.File#id}.
	 * @param fileId id of file.
	 * @return {@link com.info.model.File} by its {@link com.info.model.File#id}.
	 */
	File getFile(Integer fileId);
	
	/**
	 * Gets all {@link com.info.model.File}s contained in {@link com.info.model.Company}
	 * by {@link com.info.model.Company#id}.
	 * @param compId id of company.
	 * @return List of all {@link com.info.model.File}s contained in {@link com.info.model.Company}.
	 */
	List<File> getAllFiles(Integer compId);
	
	/**
	 * Adds {@link com.info.model.File} to {@link com.info.model.Company} by 
	 * {@link com.info.model.Company#id}.
	 * @param file {@link com.info.model.File} to be stored.
	 * @param compId id of {@link com.info.model.Company} where file will be contained.
	 */
	void addFile(File file, Integer compId);
	
	/**
	 * Deletes {@link com.info.model.File} from database.
	 * @param fileId {@link com.info.model.File#id} of {@link com.info.model.File} to be deleted.
	 */
	void deleteFile(Integer fileId);
}
