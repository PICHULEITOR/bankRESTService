package pe.com.jorgeberrios.crud.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dao.BankDao;
import pe.com.jorgeberrios.crud.dto.BankDto;
import pe.com.jorgeberrios.crud.dto.BranchOfficeDto;
//import pe.com.jorgeberrios.crud.dao.BranchOfficeDao;
import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.mapper.BranchOfficeMapper;
import pe.com.jorgeberrios.crud.service.BankService;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;

@Service
public class BankServiceImpl  implements BankService{
	@Autowired
	BankDao bankDao;
	@Autowired
	BranchOfficeService branchOfficeService;
	@Autowired
	BranchOfficeMapper branchOfficeMapper;
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
	public void create(BankDto b) {
	
			String code=b.getCode();
			String name=b.getName();
			String address=b.getAddress();
			Date registrationDate=b.getRegistrationDate();
			
			bankDao.save(new Bank(code, name, address, registrationDate));
			List<BranchOfficeDto> listOfBranchOffice=b.getBranchOffice();
			if(listOfBranchOffice!=null) {
				for(BranchOfficeDto bo:listOfBranchOffice) {
					branchOfficeService.create(code,bo);
				}
			}

	}

	@Override
	public boolean save(Bank b) {
		// TODO Auto-generated method stub
		Long id=bankDao.findIdByCode(b.getCode());
		
		if(id==null) {
			return false;
		}else {
			b.setId(id);
			bankDao.save(b);
			return true;
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
