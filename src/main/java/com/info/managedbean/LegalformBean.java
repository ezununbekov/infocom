package com.info.managedbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.info.model.Legalform;
import com.info.repo.LegalformDao;

/**
 * Managed bean for handling legal form info. This class is only used to get list of all possible
 * legal forms from database and to display their descriptions. 
 * There are no methods for addition or deletion legal forms.
 */
@ManagedBean(name="legalform", eager=true)
@RequestScoped
public class LegalformBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of all possible {@link com.info.model.Legalform}s.
	 */
	private List<Legalform> all;

	/**
	 * Map representation of {@link com.info.model.Legalform}. 
	 * It is used to display description of legal form 
	 * based on which legal form id company has.
	 */
	private Map<Integer, String> map;
	
	/**
	 * Provider of DAO methods for legal forms.
	 */
	@EJB
	private LegalformDao legalDao;
	
	/**
	 * Default constructor.
	 */
	public LegalformBean(){}

	/**
	 * Getter method for list of all {@link com.info.model.Legalform}s.
	 * @return list of all {@link com.info.model.Legalform}s stored in database.
	 */
	public List<Legalform> getAll() {
		all = legalDao.getAllForms();
		return all;
	}

	/**
	 * Setter method for list of all {@link com.info.model.Legalform}s.
	 * @param forms List of {@link com.info.model.Legalform}s to set.
	 */
	public void setAll(List<Legalform> forms) {
		this.all = forms;
	}

	/**
	 * Getter method for {@link #map}.
	 * Each element in {@link #all} converted to {@link java.util.HashMap} and stored 
	 * in {@link #map}. This is done to display description of legal form for certain company
	 * based on which legal form id that company has.
	 * @return map representation of all {@link com.info.model.Legalform}s.
	 */
	public Map<Integer, String> getMap() {
		Map<Integer, String> m = new HashMap<>();
		for(Legalform o : getAll()){
			m.put(o.getId() , o.getDescription());
		}
		map = m;
		return map;
	}

	/**
	 * Setter method for {@link #map}.
	 * @param map map representation of {@link com.info.model.Legalform}(s) to set.
	 */
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
}
