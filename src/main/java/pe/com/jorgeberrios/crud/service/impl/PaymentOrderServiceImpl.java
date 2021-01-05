package pe.com.jorgeberrios.crud.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.jorgeberrios.crud.dao.PaymentOrderDao;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;
import pe.com.jorgeberrios.crud.service.PaymentOrderService;
import org.springframework.stereotype.Service;
@Service
public class PaymentOrderServiceImpl implements PaymentOrderService {

	@Autowired
	PaymentOrderDao paymentOrderDao;
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
	public boolean create(PaymentOrder p) {
		// TODO Auto-generated method stub
		try {
			paymentOrderDao.save(p);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());	
			return false;
		}
	}

	@Override
	public boolean save(PaymentOrder p) {
		// TODO Auto-generated method stub
		Long id=paymentOrderDao.findIdByCode(p.getCode());
		p.setId(id);
		try {
			paymentOrderDao.save(p);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());	
			return false;
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
