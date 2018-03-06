package com.info.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "company")
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="company", cascade = CascadeType.REMOVE)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<Employee> employees;
	
	@OneToMany(mappedBy="company", cascade = CascadeType.REMOVE)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<File> files;
	
	private int ownership;
	
	private int legalform;
	
	private String name;
	
	private String managername;

	private String fax;

	private String phone;
	
	private String webpage;
	
	@Column(unique=true)
	private String license;
	
	private Date licensedate;
	
	private String certificate;
	
	private Date certdate;
	
	private String address;

	public Company() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public int getOwnership() {
		return ownership;
	}

	public void setOwnership(int ownership) {
		this.ownership = ownership;
	}

	public int getLegalform() {
		return legalform;
	}

	public void setLegalform(int legalform) {
		this.legalform = legalform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManagername() {
		return managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Date getLicensedate() {
		return licensedate;
	}

	public void setLicensedate(Date licensedate) {
		this.licensedate = licensedate;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Date getCertdate() {
		return certdate;
	}

	public void setCertdate(Date certdate) {
		this.certdate = certdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}
}
