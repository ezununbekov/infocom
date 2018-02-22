package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Ownership;

@Local
public interface OwnershipDao {
	Ownership getFormById(int id);
	List<Ownership> getAllForms();
}
