package com.info.managedbean;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.info.model.Company;
import com.info.model.File;
import com.info.repo.CompanyDao;
import com.info.repo.CompanyDaoImpl;
import com.info.repo.FileDao;
import com.info.repo.FileDaoImpl;

@ManagedBean(name="fileBean", eager=true)
@RequestScoped
public class FileBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<File> files;
	private File file;
	private Company company;
	private Part part;
	
	@ManagedProperty(value = "#{param.compId}")
	private Integer compId;
	
	@ManagedProperty(value = "#{param.fileId}")
	private Integer fileId;

	private FileDao fileDao = new FileDaoImpl();
	private CompanyDao companyDao = new CompanyDaoImpl();

	public List<File> getFiles() {
		if(compId != null){
			files = fileDao.getAllFiles(compId);
		} else{
			files = new ArrayList<>();
		}
			
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	public File getFile() {
		if(fileId != null)
			file = fileDao.getFile(fileId);
		else if(file == null)
			file = new File();
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Company getCompany() {
		if(compId != null)
			company = companyDao.getCompany(compId);
		else if(company == null)
			company = new Company();
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}
	
	public void uploadFile(ActionEvent ae){
		File file = new File();
		try{
			byte[] f = IOUtils.toByteArray(part.getInputStream());
			file.setName(part.getSubmittedFileName());
			file.setCompany(getCompany());
			file.setFile(f);
			fileDao.addFile(file, compId);
			FacesContext.getCurrentInstance().getExternalContext().redirect("company.xhtml?compId="+compId);
		} catch(IOException ioe){
			System.out.println("Upload IOException");
		} catch(Exception e){
			System.out.println("Upload Exception");
		}
		//return "company.xhtml?faces-redirect=true&compId="+compId;
	}
	
	public void downloadFile(ActionEvent ae){
		File file = getFile();		
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
		    ExternalContext ec = fc.getExternalContext();
		    ec.responseReset();
		    ec.setResponseContentType("multipart/form-data");
		    //ec.setResponseContentLength(length);
		    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		    
		    OutputStream output = ec.getResponseOutputStream();
		    output.write(file.getFile());
		    fc.responseComplete();
		} catch(IOException ioe){
			System.out.println("Upload IOException");
		} catch(Exception e){
			System.out.println("Upload Exception");
		}
		//return "company.xhtml?faces-redirect=true&compId="+compId;
	}
	
	public void deleteFile(ActionEvent ae){
		try{
			fileDao.deleteFile(fileId);
			FacesContext.getCurrentInstance().getExternalContext().redirect("company.xhtml?compId="+compId);
		} catch(Exception e){
			
		}
	}
}
