package pe.com.jorgeberrios.crud.service.impl;

import java.util.Date;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dao.BankDao;
import pe.com.jorgeberrios.crud.dao.BranchOfficeDao;
import pe.com.jorgeberrios.crud.dto.BranchOfficeDto;
import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;
import pe.com.jorgeberrios.crud.mapper.PaymentOrderMapper;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;
import pe.com.jorgeberrios.crud.service.PaymentOrderService;
@Service
public class BranchOfficeServiceImpl implements BranchOfficeService{
	@Autowired
	BranchOfficeDao branchOfficeDao;
	@Autowired
	BankDao bankDao;
	@Autowired
	PaymentOrderService paymentOrderService;
	@Autowired
	PaymentOrderMapper paymentOrderMapper;
	//private final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public BranchOffice findByCode(String code) {
		// TODO Auto-generated method stub
		return branchOfficeDao.findByCode(code);
	}

	@Override
	public List<BranchOffice> findAll() {
		// TODO Auto-generated method stub
		return branchOfficeDao.findAll();
	}

	@Override
	public boolean create(String bankCode,BranchOfficeDto branchOfficeDto) {
		// TODO Auto-generated method stub
		
		Bank searchedBank=bankDao.findByCode(bankCode);
		if(searchedBank==null) {
			return false;
		}
		else {
			//	branchOfficeDao.save(.get),searchedBank.getAddress(),searchedBank.getRegistrationDate()))));
			String code=branchOfficeDto.getCode();
			String name=branchOfficeDto.getName();
			String address=branchOfficeDto.getAddress();
			Date registrationDate=branchOfficeDto.getRegistrationDate();
			BranchOffice branchOffice=new BranchOffice(code, name, address, registrationDate,searchedBank);
			List<PaymentOrder> listOfPaymentOrders=branchOffice.getPaymentOrder();
			if(listOfPaymentOrders!=null) {
				for(PaymentOrder p:listOfPaymentOrders) {
					paymentOrderService.create(code,paymentOrderMapper.toDto(p));
				}
			}
			branchOfficeDao.save(branchOffice);			
			return true;
		}
	
	}

	@Override
	public boolean save(BranchOffice b) {
		// TODO Auto-generated method stub
		Long id=branchOfficeDao.findIdByCode(b.getCode());
		if(id==null) {
			return false;
		}else {
			b.setId(id);
			branchOfficeDao.save(b);
			return true;
		}
		
	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		branchOfficeDao.deleteByCode(code);
			
	}

	@Override
	public boolean existByCode(String code) {
		// TODO Auto-generated method stub
		return branchOfficeDao.existsByCode(code);
	}

}
