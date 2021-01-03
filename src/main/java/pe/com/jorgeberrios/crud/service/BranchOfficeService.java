package pe.com.jorgeberrios.crud.service;

import pe.com.jorgeberrios.crud.entity.BranchOffice;

public interface BranchOfficeService extends GenericService<BranchOffice,String> {
	boolean existByCode(String code);
}
