package com.info.managedbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.info.model.Ownership;
import com.info.repo.OwnershipDao;
import com.info.repo.OwnershipDaoImpl;

/**
 * Managed bean for handling ownership form info. This class is only used to get list of all possible
 * ownership forms from database and to display their descriptions. 
 * There are no methods for addition or deletion ownership forms.
 */
@ManagedBean(name="ownership", eager=true)
@RequestScoped
public class OwnershipBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * List of all possible {@link com.info.model.Ownership}s.
	 */
	private List<Ownership> all;

	/**
	 * Map representation of {@link com.info.model.Ownership}. 
	 * It is used to display description of ownership form 
	 * based on which ownership form id company has.
	 */
	private Map<Integer, String> map;
	
	/**
	 * Provider of DAO methods for ownership forms.
	 */
	//@EJB
	private OwnershipDao ownDao = new OwnershipDaoImpl();
	
	/**
	 * Default constructor.
	 */
	public OwnershipBean(){}
	
	/**
	 * Getter method for list of all {@link com.info.model.Ownership}s.
	 * @return list of all {@link com.info.model.Ownership}s stored in database.
	 */
	public List<Ownership> getAll() {
		all = ownDao.getAllForms();
		return all;
	}

	/**
	 * Setter method for list of all {@link com.info.model.Ownership}s.
	 * @param forms List of {@link com.info.model.Ownership}s to set.
	 */
	public void setAll(List<Ownership> forms) {
		this.all = forms;
	}

	/**
	 * Getter method for {@link #map}.
	 * Each element in {@link #all} converted to {@link java.util.HashMap} and stored 
	 * in {@link #map}. This is done to display description of ownership form for certain company
	 * based on which ownership form id that company has.
	 * @return map representation of all {@link com.info.model.Ownership}s.
	 */
	public Map<Integer, String> getMap() {
		Map<Integer, String> m = new HashMap<>();
		for(Ownership o : getAll()){
			m.put(o.getId() , o.getDescription());
		}
		map = m;
		return map;
	}

	/**
	 * Setter method for {@link #map}.
	 * @param map map representation of {@link com.info.model.Ownership}(s) to set.
	 */
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
}
