package pe.com.jorgeberrios.crud.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dto.BankDto;
import pe.com.jorgeberrios.crud.entity.Bank;

@Service
public class BankMapper {
	@Autowired
	private BranchOfficeMapper branchOfficeMapper;
	public BankDto toDto(Bank bank) {
		if(bank==null) {
			return null;
		}else {
			return new BankDto(bank.getCode(),bank.getName(),bank.getAddress(),bank.getRegistrationDate(), branchOfficeMapper.toListDto(bank.getBranchOffice()));
		}
	}
	public List<BankDto> toListDto(List<Bank> listBank){
		return listBank.stream().map(b->toDto(b)).collect(Collectors.toList());
	}
}
