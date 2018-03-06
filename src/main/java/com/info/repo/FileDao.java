package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.File;

@Local
public interface FileDao {
	File getFile(Integer fileId);
	List<File> getAllFiles(Integer compId);
	void addFile(File file, Integer compId);
	void deleteFile(Integer fileId);
}
