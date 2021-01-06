package pe.com.jorgeberrios.crud.service;

import java.util.List;

import pe.com.jorgeberrios.crud.dto.BankDto;
import pe.com.jorgeberrios.crud.entity.Bank;

public interface BankService {
	boolean existByCode(String code);
    public Bank findByCode(String code);
    public List<Bank> findAll();
    public void create(BankDto b);
    public boolean save(Bank b);   
    public void delete(String code);
}
