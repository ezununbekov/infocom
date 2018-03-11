package com.info.managedbean;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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
import com.info.repo.FileDao;

/**
 * Managed bean for handling file information.
 */
@ManagedBean(name="fileBean", eager=true)
@RequestScoped
public class FileBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contains all files of this {@link #company}. 
	 */
	private List<File> files;
	
	/**
	 * Holds information about file.
	 */
	private File file;
	
	/**
	 * Holds information about company.
	 */
	private Company company;
	
	/**
	 * Represents a part or form item that was received within a multipart/form-data POST request.
	 */
	private Part part;
	
	/**
	 * Holds id of company.
	 * Set by parameter compId.
	 */
	@ManagedProperty(value = "#{param.compId}")
	private Integer compId;
	
	/**
	 * Holds id of file.
	 * Set by parameter fileId.
	 */
	@ManagedProperty(value = "#{param.fileId}")
	private Integer fileId;

	/**
	 * Provider of DAO methods for file.
	 */
	@EJB
	private FileDao fileDao;
	
	/**
	 * Provider of DAO methods for company.
	 */
	@EJB
	private CompanyDao companyDao;
	
	/**
	 * Default constructor.
	 */
	public FileBean(){}

	/**
	 * Getter method for {@link #files}.
	 * If {@link #compId} is set to value other than {@code null} list of all files of
	 * company with that id is retrieved from database. Otherwise, empty list is returned.
	 * @return List of all files of {@link #company} if {@link #compId} is not null,
	 * or empty list, otherwise.
	 */
	public List<File> getFiles() {
		if(compId != null){
			files = fileDao.getAllFiles(compId);
		} else{
			files = new ArrayList<>();
		}
			
		return files;
	}

	/**
	 * Setter method for {@link #files}.
	 * @param files List of files to set.
	 */
	public void setFiles(List<File> files) {
		this.files = files;
	}

	/**
	 * Getter method for {@link #compId}.
	 * @return id of company.
	 */
	public Integer getCompId() {
		return compId;
	}

	/**
	 * Setter method for {@link #compId}.
	 * @param compId id of company to set.
	 */
	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	/**
	 * Getter method for {@link #fileId}.
	 * @return id of file.
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * Setter method for {@link #fileId}.
	 * @param fileId id of file to set.
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	/**
	 *Getter method for {@link #file}.
	 *If {@link #fileId} is set to value other than {@code null} file with that id
	 *is retrieved from database. Otherwise, new file is initialized.
	 * @return file with {@link #fileId} if that id is not null, or newly initialized file, otherwise.
	 */
	public File getFile() {
		if(fileId != null)
			file = fileDao.getFile(fileId);
		else if(file == null)
			file = new File();
		return file;
	}

	/**
	 * Setter method for {@link #file}.
	 * @param file file to set.
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 *Getter method for {@link #company}.
	 *If {@link #compId} is set to value other than {@code null} company with that id
	 *is retrieved from database. Otherwise, new company is initialized.
	 * @return company with {@link #compId} if that id is not null, or newly initialized company, otherwise.
	 */
	public Company getCompany() {
		if(compId != null)
			company = companyDao.getCompany(compId);
		else if(company == null)
			company = new Company();
		return company;
	}

	/**
	 * Setter method for {@link #company}.
	 * @param company company to set.
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Getter method for {@link #part}.
	 * @return part.
	 */
	public Part getPart() {
		return part;
	}

	/**
	 * Setter method for {@link #part}.
	 * @param part part to set.
	 */
	public void setPart(Part part) {
		this.part = part;
	}
	
	/**
	 * Method for uploading a file. File to be uploaded is represented as {@link #part}. Then, InputStream 
	 * is retrieved from that part and translated into array of bytes. After that appropriate fields of {@link #file}
	 * are set to accomplish file uploading to database.
	 * @param ae {@link javax.faces.event.ActionEvent} - represents the activation of a user interface component (such as a {@link javax.faces.component.UIComponent}).
	 */
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
	}
	
	/**
	 * Method for downloading a file.
	 * @param ae {@link javax.faces.event.ActionEvent} - represents the activation of a user interface component (such as a {@link javax.faces.component.UIComponent}).
	 */
	public void downloadFile(ActionEvent ae){
		File file = getFile();		
		try{
			FacesContext fc = FacesContext.getCurrentInstance();
		    ExternalContext ec = fc.getExternalContext();
		    ec.responseReset();
		    ec.setResponseContentType("multipart/form-data");
		    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		    
		    OutputStream output = ec.getResponseOutputStream();
		    output.write(file.getFile());
		    fc.responseComplete();
		} catch(IOException ioe){
			System.out.println("Upload IOException");
		} catch(Exception e){
			System.out.println("Upload Exception");
		}
	}
	
	/**
	 * Method for deleting a file.
	 * @param ae {@link javax.faces.event.ActionEvent} - represents the activation of a user interface component (such as a {@link javax.faces.component.UIComponent}).
	 */
	public void deleteFile(ActionEvent ae){
		try{
			fileDao.deleteFile(fileId);
			FacesContext.getCurrentInstance().getExternalContext().redirect("company.xhtml?compId="+compId);
		} catch(Exception e){
			System.out.println("Delete Exception");
		}
	}
}
