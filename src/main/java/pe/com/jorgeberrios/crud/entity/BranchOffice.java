package pe.com.jorgeberrios.crud.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="branch_office")
public class BranchOffice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String  name;
	private String  address;
	@Temporal(TemporalType.DATE)
	@Column(name="registration_date")
	private Date registrationDate;
	@JsonManagedReference
	@ManyToOne
	private Bank bank;
	@JsonBackReference
	@OneToMany(mappedBy="branchOffice")
	private List<PaymentOrder> paymentOrder;
	public BranchOffice() {
		super();
	}
	
	
	public BranchOffice(String code, String name, String address, Date registrationDate, Bank bank) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.registrationDate = registrationDate;
		this.bank = bank;
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
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public List<PaymentOrder> getPaymentOrder() {
		return paymentOrder;
	}
	public void setPaymentOrder(List<PaymentOrder> paymentOrder) {
		this.paymentOrder = paymentOrder;
	}
	
	

}
