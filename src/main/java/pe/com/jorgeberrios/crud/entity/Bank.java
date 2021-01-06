package pe.com.jorgeberrios.crud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Bank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	private String address;
	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;
	@JsonBackReference
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="bank")
	private List<BranchOffice> branchOffice;
	public Bank() {
		
	}
	
	public Bank( String code, String name, String address, Date registrationDate) {
		super();
		
		this.code = code;
		this.name = name;
		this.address = address;
		this.registrationDate = registrationDate;
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public List<BranchOffice> getBranchOffice() {
		return branchOffice;
	}
	public void setBranchOffice(List<BranchOffice> branchOffice) {
		this.branchOffice = branchOffice;
	}
	
	

}


			