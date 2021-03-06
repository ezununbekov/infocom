package com.info.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for ownership form.
 */
@Entity
@Table(name="ownership")
public class Ownership implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id of ownership form stored in database.
	 */
	@Id
	private int id;
	
	/**
	 * Description of ownership form stored in database.
	 */
	private String description;
	
	/**
	 * Default constructor.
	 */
	public Ownership() {
		super();
	}

	/**
	 * Getter method for {@link #id}.
	 * @return id of ownership form.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for {@link #id}.
	 * @param id id of ownership form to set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter method for {@link #description}.
	 * @return description of ownership form.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter method for {@link #description}.
	 * @param description description of ownership form to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
