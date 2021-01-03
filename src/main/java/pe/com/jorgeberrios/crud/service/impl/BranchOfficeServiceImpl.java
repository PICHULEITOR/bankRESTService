package pe.com.jorgeberrios.crud.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dao.BranchOfficeDao;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;
import pe.com.jorgeberrios.crud.service.GenericService;
@Service
public class BranchOfficeServiceImpl implements GenericService<BranchOffice,String>,BranchOfficeService{
	@Autowired
	BranchOfficeDao branchOfficeDao;
	private final Logger logger = LoggerFactory.getLogger(getClass());
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
	public boolean create(BranchOffice b) {
		// TODO Auto-generated method stub
		try {
			branchOfficeDao.save(b);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());	
			return false;
		}
	}

	@Override
	public boolean save(BranchOffice b) {
		// TODO Auto-generated method stub
		Long id=branchOfficeDao.findIdByCode(b.getCode());
		b.setId(id);
		try {
			branchOfficeDao.save(b);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());	
			return false;
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
