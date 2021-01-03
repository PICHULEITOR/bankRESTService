package pe.com.jorgeberrios.crud.service;

import java.util.List;


import pe.com.jorgeberrios.crud.entity.PaymentOrder;

public interface PaymentOrderService extends GenericService<PaymentOrder,String>{
	List<PaymentOrder> getByBranchOfficeCodeAndCurrencyType(String branchOfficeCode,String currencyType);
	boolean existByCode(String code);
}
