package com.info.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Entity class for employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * id of employee. Automatically generated by database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * {@link com.info.model.Company} this employee belongs to.
	 */
	@ManyToOne
    @JoinColumn(name="comp_id", nullable=false)
	private Company company;
	
	/**
	 * Employee's name.
	 * Mandatory field.
	 * Can contain only letters, hyphens, and spaces and cannot be greater than 50 characters.
	 */
	private String name;
	
	/**
	 * Home phone of employee.
	 * Optional field.
	 * If available, must be 12 digits in length.
	 */
	private String homephone;
	
	/**
	 * Mobile phone of employee.
	 * Optional field.
	 * If available, must be 12 digits in length.
	 */
	private String mobilephone;
	
	/**
	 * Work phone of employee.
	 * Mandatory field.
	 * Must be 12 digits in length.
	 */
	private String workphone;
	
	/**
	 * Employee's address.
	 * Mandatory field.
	 * Cannot be greater than 50 characters. Can contain any characters.
	 */
	private String address;
	
	/**
	 * Employee's e-mail.
	 * Optional field.
	 * If available, must match {@code ([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})} pattern.
	 */
	private String email;
	
	/**
	 * Employee's bank data.
	 * Mandatory field.
	 * Cannot be greater than 50 characters. Can contain any characters.
	 */
	private String bankdata;
	
	/**
	 * Employee's position.
	 * Mandatory field.
	 * Can contain only letters, hyphens, and spaces and cannot be greater than 20 characters.
	 */
	private String position;

	/**
	 * Default constructor.
	 */
	public Employee() {
		super();
	}

	/**
	 * Getter method for {@link #id}.
	 * @return id of employee.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter method for {@link  #id}.
	 * @param id id of employee to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter method for {@link #company}.
	 * @return company of employee.
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Setter method for {@link #company}.
	 * @param company company of employee to set.
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Getter method for {@link #name}.
	 * @return name of employee.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for {@link #name}.
	 * @param name name of employee to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method for {@link #homephone}.
	 * @return home phone of employee.
	 */
	public String getHomephone() {
		return homephone;
	}

	/**
	 * Setter method for {@link #homephone}.
	 * @param homephone home phone of employee to set.
	 */
	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	/**
	 * Getter method for {@link #mobilephone}.
	 * @return mobile phone of employee.
	 */
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * Setter method for {@link #mobilephone}.
	 * @param mobilephone mobile phone of employee to set.
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	/**
	 * Getter method for {@link #workphone}.
	 * @return work phone of employee.
	 */
	public String getWorkphone() {
		return workphone;
	}

	/**
	 * Setter method for {@link #workphone}.
	 * @param workphone work phone of employee to set.
	 */
	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	/**
	 * Getter method for {@link #address}.
	 * @return address of employee.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter method for {@link #address}.
	 * @param address address of employee to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter method for {@link #email}.
	 * @return e-mail of employee.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter method for {@link #email}.
	 * @param email e-mail of employee to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter method for {@link #bankdata}.
	 * @return bank data of employee.
	 */
	public String getBankdata() {
		return bankdata;
	}

	/**
	 * Setter method for {@link #bankdata}.
	 * @param bankdata bank data of employee to set.
	 */
	public void setBankdata(String bankdata) {
		this.bankdata = bankdata;
	}

	/**
	 * Getter method for {@link #position}.
	 * @return position of employee.
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Setter method for {@link #position}.
	 * @param position position of employee to set.
	 */
	public void setPosition(String position) {
		this.position = position;
	}
}
