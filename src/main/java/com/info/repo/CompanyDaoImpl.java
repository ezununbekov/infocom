package com.info.repo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.info.model.Company;
@Stateless
public class CompanyDaoImpl implements CompanyDao{
	private static List<Company> all = new ArrayList<>();
	static{
		all.add(new Company(1, 2, 2, "Beeline", "Veon", "fax",
				"Fon", "wp.com", "lic21", "l-date", "cert", 
				"cert-date", "adrs"));
		all.add(new Company(2, 1, 3, "MGCM", "unknwn", "fax2",
				"Fon2", "megacom.com", "lic24", "l24-date", "cert24", 
				"cert24-date", "adrs24"));
	}
	
	private Company company;
	
	public Company getCompany(int id){
		company = new Company();
		for(Company c : all){
			if(c.getId() == id){
				company = c;
				break;
			}
		}
		return company;
	}
	
	public List<Company> getAllCompanies(){
		return all;
	}
	
	public void addCompany(Company company){
		for(Company c : all){
			if(c.getId() == company.getId())
				return;
		}
		all.add(company);
	}
	
	public void updateCompany(Company company){
		for(Company c : all){
			if(c.getId() == company.getId()){
				all.remove(c);
				all.add(company);
				break;
			}
		}
	}
	
	public void deleteCompany(int id){
		for(Company c : all){
			if(c.getId() == id){
				all.remove(c);
				break;
			}
		}
	}
}
