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

import pe.com.jorgeberrios.crud.controller.BankController;
import pe.com.jorgeberrios.crud.dto.BankDto;
import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.mapper.BankMapper;
import pe.com.jorgeberrios.crud.service.BankService;
import pe.com.jorgeberrios.util.ConvertToUtils;

//import io.swagger.annotations.Api;
//import org.springframework.stereotype.Service;
//@Path("/bank")
//@Service
public class BankControllerImpl implements BankController {
	//private final ObjectMapper JSON_MAPPER=new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BankService bankService;
	@Autowired
	private BankMapper bankMapper;
	
	@Override
	public Response getBanks() {
		// TODO Auto-generated method stub
		logger.info("START [GET]/get-banks:{}");
		Object obj=new JSONObject();
		List<BankDto> listOfBanks=new ArrayList<>();
		try {
			listOfBanks=bankMapper.toListDto(bankService.findAll());
			//ConvertToUtils.
			//obj.put("banks",listOfBanks);

		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		logger.info("END [GET]/get-banks:{}",ConvertToUtils.convertFromObjectToJson(listOfBanks));
		return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response getBankByCode(String code) {
		logger.info("START [GET]/get-bank-by-code:{}");
		JSONObject obj=new JSONObject();
		BankDto bankDto=new BankDto();
		try {
			bankDto=bankMapper.toDto(bankService.findByCode(code));
			obj.put("bank",bankDto);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		if(bankDto==null) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

		}else {
			logger.info("END [GET]/get-bank-by-code:{}",ConvertToUtils.convertFromObjectToJson(bankDto));
			return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response createBank(BankDto bankDto) {
		// TODO Auto-generated method stub
		logger.info("START [POST]/create-bank:{}");
		JSONObject obj=new JSONObject();
		if(bankService.create(new Bank(bankDto.getCode(), bankDto.getName(), bankDto.getAddress(),bankDto.getRegistrationDate()))) {
			logger.info("END [POST]/create-bank:{}");
			return Response.status(Status.OK).entity("SUCCESS CREATE").type(MediaType.APPLICATION_JSON).build();

		}
		else {
			return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
		}
		
	}

	@Override
	public Response update(String code, BankDto bankDto) {
		// TODO Auto-generated method stub
		logger.info("START [PUT]/update-bank:{}");
		if(bankService.save(new Bank(bankDto.getCode(), bankDto.getName(), bankDto.getAddress(),bankDto.getRegistrationDate()))) {
			logger.info("END [PUT]/update-bank:{}");
			return Response.status(Status.OK).entity("SUCCESS UPDATE").type(MediaType.APPLICATION_JSON).build();
		}
		else {
			return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response delete(String code) {
		// TODO Auto-generated method stub
		logger.info("START [DELETE]/delete-bank:{}");
		if(bankService.existByCode(code)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			try{
				bankService.delete(code);
				logger.info("END [DELETE]/delete-bank:{}");
				return Response.status(Status.OK).entity("SUCCESS DELETE").type(MediaType.APPLICATION_JSON).build();
			}catch(Exception e) {
				return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
			}
			
			
		}
		

	}

}
