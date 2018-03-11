package com.info.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for legal form.
 */
@Entity
@Table(name="legalform")
public class Legalform implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id of legal form stored in database.
	 */
	@Id
	private int id;
	
	/**
	 * Description of legal form stored in database.
	 */
	private String description;
	
	/**
	 * Default constructor.
	 */
	public Legalform() {
		super();
	}

	/**
	 * Getter method for {@link #id}.
	 * @return id of legal form.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for {@link #id}.
	 * @param id id of legal form to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for {@link #description}.
	 * @return description of legal form.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter method for {@link #description}.
	 * @param description description of legal form to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
