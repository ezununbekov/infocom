package com.info.repo;

import java.util.List;

import javax.ejb.Local;

import com.info.model.Legalform;

@Local
public interface LegalformDao {
	Legalform getFormById(int id);
	List<Legalform> getAllForms();
}
