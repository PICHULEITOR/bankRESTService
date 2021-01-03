package pe.com.jorgeberrios.crud.service;

import pe.com.jorgeberrios.crud.entity.Bank;

public interface BankService extends GenericService<Bank,String>{
	boolean existByCode(String code);
}
