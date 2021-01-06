package pe.com.jorgeberrios.crud.service;

import java.util.List;

import pe.com.jorgeberrios.crud.dto.BranchOfficeDto;
import pe.com.jorgeberrios.crud.entity.BranchOffice;

public interface BranchOfficeService {
	boolean existByCode(String code);
    public BranchOffice findByCode(String code);
    public List<BranchOffice> findAll();
    public boolean create(String bankCode,BranchOfficeDto b);
    public boolean save(BranchOffice b);    
    public void delete(String code);
}
