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
import com.info.repo.LegalformDaoImpl;

@ManagedBean(name="legalform", eager=true)
@RequestScoped
public class LegalformBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Legalform> all;
	private Legalform form;
	private Map<Integer, String> map;
	private int id;
	private String desc;
	
	//@EJB
	private LegalformDao legalDao = new LegalformDaoImpl();

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
	
	public List<Legalform> getAll() {
		all = legalDao.getAllForms();
		return all;
	}

	public void setAll(List<Legalform> forms) {
		this.all = forms;
	}

	public Legalform getForm() {
		return form;
	}

	public void setForm(Legalform form) {
		this.form = form;
	}

	public Map<Integer, String> getMap() {
		Map<Integer, String> m = new HashMap<>();
		for(Legalform o : getAll()){
			m.put(o.getId() , o.getDescription());
		}
		//map = m;
		return m;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
}
