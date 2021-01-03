package pe.com.jorgeberrios.crud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="payment_order")
public class PaymentOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code; 
	private Double  amount; 
	@Temporal(TemporalType.DATE)
	@Column(name="payment_date")
	private Date paymentDate;
	@JsonManagedReference
	@ManyToOne
	private BranchOffice branchOffice;
	@Enumerated(EnumType.ORDINAL)
	private PaymentOrderState state;
	@Enumerated(EnumType.ORDINAL)
	private PaymentOrderCurrency currency;
	
	public PaymentOrder() {
		super();
	}

	public PaymentOrder(String code, Double amount, Date paymentDate, BranchOffice branchOffice,
			PaymentOrderState state, PaymentOrderCurrency currency) {
		super();
		this.code = code;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.branchOffice = branchOffice;
		this.state = state;
		this.currency = currency;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BranchOffice getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public PaymentOrderState getState() {
		return state;
	}

	public void setState(PaymentOrderState state) {
		this.state = state;
	}

	public PaymentOrderCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(PaymentOrderCurrency currency) {
		this.currency = currency;
	}
	
}
