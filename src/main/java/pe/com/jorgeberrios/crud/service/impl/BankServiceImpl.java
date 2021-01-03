package pe.com.jorgeberrios.crud.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dao.BankDao;
import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.service.BankService;

@Service
public class BankServiceImpl  implements BankService{
	@Autowired
	BankDao bankDao;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public Bank findByCode(String code) {
		// TODO Auto-generated method stub
		return bankDao.findByCode(code);
	}

	@Override
	public List<Bank> findAll() {
		// TODO Auto-generated method stub
		return bankDao.findAll();
	}

	@Override
	public boolean create(Bank b) {
		// TODO Auto-generated method stub
		try {
			bankDao.save(b);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());	
			return false;
		}
	}

	@Override
	public boolean save(Bank b) {
		// TODO Auto-generated method stub
		Long id=bankDao.findIdByCode(b.getCode());
		b.setId(id);
		try {
			bankDao.save(b);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());	
			return false;
		}
		
	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		bankDao.deleteByCode(code);			
	}

	@Override
	public boolean existByCode(String code) {
		// TODO Auto-generated method stub
		return bankDao.existsByCode(code);
	}
	
		
	

	

}
