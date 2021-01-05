package pe.com.jorgeberrios.crud.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
//import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import pe.com.jorgeberrios.crud.controller.BranchOfficeController;
import pe.com.jorgeberrios.crud.dto.BranchOfficeDto;
import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.mapper.BranchOfficeMapper;
import pe.com.jorgeberrios.crud.service.BankService;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;
import pe.com.jorgeberrios.util.ConvertToUtils;
//import io.swagger.annotations.Api;
//import org.springframework.stereotype.Service;
//@Path("/branch_office")
//@Api("/branch_office")
//@Service
public class BranchOfficeControllerImpl implements BranchOfficeController {
	//private final ObjectMapper JSON_MAPPER=new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BranchOfficeService branchOfficeService;
	@Autowired
	private BankService bankService;
	@Autowired
	private BranchOfficeMapper BranchOfficeMapper;
	
	@Override
	public Response getBranchOffices() {
		// TODO Auto-generated method stub
		logger.info("START [GET]/get-branch-offices:{}");
		JSONObject obj=new JSONObject();
		List<BranchOfficeDto> listOfBranchOffices=new ArrayList<>();
		try {
			listOfBranchOffices=BranchOfficeMapper.toListDto(branchOfficeService.findAll());
			obj.put("BranchOffices",listOfBranchOffices);

		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		logger.info("END [GET]/get-branch-offices:{}",ConvertToUtils.convertFromObjectToJson(listOfBranchOffices));
		return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response getBranchOfficeByCode(String code) {
		logger.info("START [GET]/get-branch-office-by-code:{}");
		JSONObject obj=new JSONObject();
		BranchOfficeDto BranchOfficeDto=new BranchOfficeDto();
		try {
			BranchOfficeDto=BranchOfficeMapper.toDto(branchOfficeService.findByCode(code));
			obj.put("BranchOffice",BranchOfficeDto);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		
		if(BranchOfficeDto==null) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

		}else {
			logger.info("END [GET]/get-branch-office-by-code:{}",ConvertToUtils.convertFromObjectToJson(BranchOfficeDto));

			return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response createBranchOffice(String bankCode,BranchOfficeDto branchOfficeDto) {
		// TODO Auto-generated method stub
		logger.info("START [POST]/create-branch-office:{}");
		
		if(!bankService.existByCode(bankCode)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			Bank searchedBank=bankService.findByCode(bankCode);
			
			if(branchOfficeService.create(new BranchOffice(branchOfficeDto.getCode(),branchOfficeDto.getName(),branchOfficeDto.getAddress(),branchOfficeDto.getRegistrationDate(),new Bank(searchedBank.getCode(),searchedBank.getName(),searchedBank.getAddress(),searchedBank.getRegistrationDate())))) {
				logger.info("END [POST]/create-branch-office:{}");
				return Response.status(Status.OK).entity("SUCCESS CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			
		}
		
		
	}

	@Override
	public Response update(String code, BranchOfficeDto branchOfficeDto) {
		// TODO Auto-generated method stub
		logger.info("START [PUT]/update-branch-office:{}");
		if(!branchOfficeService.existByCode(code)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			Bank bank=branchOfficeService.findByCode(code).getBank();
			if(branchOfficeService.save(new BranchOffice(branchOfficeDto.getCode(),branchOfficeDto.getName(),branchOfficeDto.getAddress(),branchOfficeDto.getRegistrationDate(), bank))) {
				return Response.status(Status.OK).entity("SUCCESS UPDATE").type(MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			
		}
	}

	@Override
	public Response delete(String code) {
		// TODO Auto-generated method stub
		logger.info("START [DELETE]/delete-branch-office:{}");
		if(!branchOfficeService.existByCode(code)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			try {
				branchOfficeService.delete(code);
				logger.info("END [DELETE]/delete-branch-office:{}");
				return Response.status(Status.OK).entity("SUCCESS DELETE").type(MediaType.APPLICATION_JSON).build();
			}catch(Exception e) {
				return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();

			}
		}
		

	}

}
