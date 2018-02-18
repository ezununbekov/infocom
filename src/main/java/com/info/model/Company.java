package com.info.model;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "company")
public class Company {
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column
	private int id;
	
	//@Column
	private int ownership;
	
	//@Column
	private int legalform;
	
	//@Column
	private String name;
	
	//@Column
	private String managername;
	
	//@Column
	private String fax;
	
	//@Column
	private String phone;
	
	//@Column
	private String webpage;
	
	//@Column
	private String license;
	
	//@Column
	private Date licensedate;
	
	//@Column
	private String certificate;
	
	//@Column
	private Date certdate;
	
	//@Column
	private String address;

	public Company() {
		super();
	}

	public Company(int id, int ownership, int legalform, String name, String managername, String fax, String phone,
			String webpage, String license, Date licensedate, String certificate, Date certdate, String address){
		this.id = id;
		this.ownership = ownership;
		this.legalform = legalform;
		this.name = name;
		this.managername = managername;
		this.fax = fax;
		this.phone = phone;
		this.webpage = webpage;
		this.license = license;
		this.licensedate = licensedate;
		this.certificate = certificate;
		this.certdate = certdate;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
