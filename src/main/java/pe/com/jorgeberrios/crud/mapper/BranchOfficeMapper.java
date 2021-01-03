package pe.com.jorgeberrios.crud.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dto.BranchOfficeDto;
import pe.com.jorgeberrios.crud.entity.BranchOffice;

@Service
public class BranchOfficeMapper {
	@Autowired
	private PaymentOrderMapper paymentOrderMapper;
	public BranchOfficeDto toDto(BranchOffice branchOffice) {
		if(branchOffice==null) {
			return null;
		}else {
			return new BranchOfficeDto(branchOffice.getCode(),branchOffice.getName(),branchOffice.getAddress(),branchOffice.getRegistrationDate(),paymentOrderMapper.toListDto(branchOffice.getPaymentOrder()));
		}
	}
	public List<BranchOfficeDto> toListDto(List<BranchOffice> listBranchOffice){
		return listBranchOffice.stream().map(b->toDto(b)).collect(Collectors.toList());
	}
}
