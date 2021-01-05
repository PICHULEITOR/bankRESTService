package pe.com.jorgeberrios.crud.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import pe.com.jorgeberrios.crud.entity.PaymentOrderCurrency;
import pe.com.jorgeberrios.crud.entity.PaymentOrderState;
import javax.xml.bind.annotation.XmlRootElement;
/*
import org.codehaus.jackson.annote.JsonIgnoreProperties;
import org.codehaus.jackson.annote.JsonProperty;
import org.codehaus.jackson.annote.JsonPropertyOrder;
@JsonPropertyOrder("code")
@JsonIgnoreProperties(ignoreUnkown=true)
*/
@XmlRootElement
public class PaymentOrderDto {
	//@JsonProperty("code")
	private String code;
	//@JsonProperty("amount")
	private Double  amount;
	//@JsonProperty("paymentDate")
	private Date paymentDate;
	//@JsonProperty("state")
	private PaymentOrderState state;
	//@JsonProperty("currency")
	private PaymentOrderCurrency currency;
	
	public PaymentOrderDto() {
		super();
	}

	public PaymentOrderDto(String code, Double amount, Date paymentDate,
			PaymentOrderState state, PaymentOrderCurrency currency) {
		super();
		this.code = code;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.state = state;
		this.currency = currency;
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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
