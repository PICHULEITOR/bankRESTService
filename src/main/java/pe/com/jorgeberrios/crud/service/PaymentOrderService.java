package pe.com.jorgeberrios.crud.service;

import java.util.List;

import pe.com.jorgeberrios.crud.dto.PaymentOrderDto;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;

public interface PaymentOrderService{
	List<PaymentOrder> getByBranchOfficeCodeAndCurrencyType(String branchOfficeCode,String currencyType);
	boolean existByCode(String code);
	public PaymentOrder findByCode(String code);
	public List<PaymentOrder> findAll();
	public boolean create(String branchOfficeCode,PaymentOrderDto p);
	public boolean save(PaymentOrder p);    
	public void delete(String code);
}
