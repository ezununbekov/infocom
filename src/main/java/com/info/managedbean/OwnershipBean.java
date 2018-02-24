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

@ManagedBean(name="ownership", eager=true)
@RequestScoped
public class OwnershipBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Ownership> all;
	private Ownership form;
	private Map<Integer, String> map;
	private int id;
	private String desc;
	
	//@EJB
	private OwnershipDao ownDao = new OwnershipDaoImpl();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public List<Ownership> getAll() {
		all = ownDao.getAllForms();
		return all;
	}

	public void setAll(List<Ownership> forms) {
		this.all = forms;
	}

	public Ownership getForm() {
		return form;
	}

	public void setForm(Ownership form) {
		this.form = form;
	}

	public Map<Integer, String> getMap() {
		Map<Integer, String> m = new HashMap<>();
		for(Ownership o : getAll()){
			m.put(o.getId() , o.getDescription());
		}
		return m;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
}
