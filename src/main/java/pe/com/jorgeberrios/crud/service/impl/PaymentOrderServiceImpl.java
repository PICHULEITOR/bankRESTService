package pe.com.jorgeberrios.crud.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.jorgeberrios.crud.dao.BranchOfficeDao;
import pe.com.jorgeberrios.crud.dao.PaymentOrderDao;
import pe.com.jorgeberrios.crud.dto.PaymentOrderDto;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;
import pe.com.jorgeberrios.crud.entity.PaymentOrderCurrency;
import pe.com.jorgeberrios.crud.entity.PaymentOrderState;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;
import pe.com.jorgeberrios.crud.service.PaymentOrderService;
import org.springframework.stereotype.Service;
@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

	@Autowired
	PaymentOrderDao paymentOrderDao;
	@Autowired
	BranchOfficeDao branchOfficeDao;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public PaymentOrder findByCode(String code) {
		// TODO Auto-generated method stub
		return paymentOrderDao.findByCode(code);
	}

	@Override
	public List<PaymentOrder> findAll() {
		// TODO Auto-generated method stub
		return paymentOrderDao.findAll();
	}
	@Override
	public boolean create(String branchOfficeCode, PaymentOrderDto p) {
		// TODO Auto-generated method stub
		BranchOffice branchOffice=branchOfficeDao.findByCode(branchOfficeCode);
		if(branchOffice!=null) {
			return false;
		}
		else {
			String code=p.getCode();
			Double amount=p.getAmount();
			Date paymentDate=p.getPaymentDate();
			PaymentOrderState state=p.getState();
			PaymentOrderCurrency currency=p.getCurrency();
			PaymentOrder paymentOrder=new PaymentOrder(code, amount, paymentDate, branchOffice, state, currency);
			paymentOrderDao.save(paymentOrder);
			return true;
		}
		
	}
	
	@Override
	public boolean save(PaymentOrder p) {
		// TODO Auto-generated method stub
		Long id=paymentOrderDao.findIdByCode(p.getCode());
		if(id!=null) {
			return false;
		}
		else {
			p.setId(id);
			paymentOrderDao.save(p);
			return true;
		}
		
	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		paymentOrderDao.deleteByCode(code);
			
	}

	@Override
	public List<PaymentOrder> getByBranchOfficeCodeAndCurrencyType(String branchOfficeCode, String currencyType) {
		// TODO Auto-generated method stub
		return paymentOrderDao.findByBranchOfficeCodeAndCurrencyType(branchOfficeCode, currencyType);
	}

	@Override
	public boolean existByCode(String code) {
		// TODO Auto-generated method stub
		return paymentOrderDao.existsByCode(code);
	}



}
