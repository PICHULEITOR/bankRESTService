package pe.com.jorgeberrios.crud.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import pe.com.jorgeberrios.crud.controller.PaymentOrderController;
import pe.com.jorgeberrios.crud.dto.PaymentOrderDto;
import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;
import pe.com.jorgeberrios.crud.mapper.PaymentOrderMapper;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;
import pe.com.jorgeberrios.crud.service.PaymentOrderService;
import pe.com.jorgeberrios.util.ConvertToUtils;

public class PaymentOrderControllerImpl implements PaymentOrderController {

	//private final ObjectMapper JSON_MAPPER=new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaymentOrderService PaymentOrderService;
	@Autowired
	private BranchOfficeService branchOfficeService;
	@Autowired
	private PaymentOrderMapper PaymentOrderMapper;
	
	@Override
	public Response getPaymentOrderByBranchOfficeCodeAndCurrencyType(String branchOfficeCode, String currencyType) {
		// TODO Auto-generated method stub
		logger.info("START [GET]/get-payment-order-by-code:{}");
		JSONObject obj=new JSONObject();
		PaymentOrderDto PaymentOrderDto=new PaymentOrderDto();
		try {
			List<PaymentOrderDto> listPaymentOrders=PaymentOrderMapper.toListDto(PaymentOrderService.getByBranchOfficeCodeAndCurrencyType(branchOfficeCode, currencyType));
			obj.put("PaymentOrders",PaymentOrderDto);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		
		if(PaymentOrderDto==null) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

		}else {
			logger.info("END [GET]/get-payment-order-by-code:{}",ConvertToUtils.convertFromObjectToJson(PaymentOrderDto));

			return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response getPaymentOrders() {
		// TODO Auto-generated method stub
		logger.info("START [GET]/get-payment-orders:{}");
		JSONObject obj=new JSONObject();
		List<PaymentOrderDto> listOfPaymentOrders=new ArrayList<>();
		try {
			listOfPaymentOrders=PaymentOrderMapper.toListDto(PaymentOrderService.findAll());
			obj.put("PaymentOrders",listOfPaymentOrders);

		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		logger.info("END [GET]/get-payment-orders:{}",ConvertToUtils.convertFromObjectToJson(listOfPaymentOrders));
		return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response getPaymentOrderByCode(String code) {
		logger.info("START [GET]/get-payment-order-by-code:{}");
		JSONObject obj=new JSONObject();
		PaymentOrderDto PaymentOrderDto=new PaymentOrderDto();
		try {
			PaymentOrderDto=PaymentOrderMapper.toDto(PaymentOrderService.findByCode(code));
			obj.put("PaymentOrder",PaymentOrderDto);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		
		if(PaymentOrderDto==null) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

		}else {
			logger.info("END [GET]/get-payment-order-by-code:{}",ConvertToUtils.convertFromObjectToJson(PaymentOrderDto));

			return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response createPaymentOrder(String branchOfficeCode,PaymentOrderDto paymentOrderDto) {
		// TODO Auto-generated method stub
		logger.info("START [POST]/create-payment-order:{}");
		
		if(!branchOfficeService.existByCode(branchOfficeCode)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			BranchOffice searchedBranchOffice=branchOfficeService.findByCode(branchOfficeCode);
			
			if(PaymentOrderService.create(new PaymentOrder(paymentOrderDto.getCode(), paymentOrderDto.getAmount(), paymentOrderDto.getPaymentDate(),searchedBranchOffice,paymentOrderDto.getState(),paymentOrderDto.getCurrency()))) {
				logger.info("END [POST]/create-payment-order:{}");
				return Response.status(Status.OK).entity("SUCCESS CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			
		}
		
		
	}

	@Override
	public Response update(String code, PaymentOrderDto paymentOrderDto) {
		// TODO Auto-generated method stub
		logger.info("START [PUT]/update-payment-order:{}");
		if(!PaymentOrderService.existByCode(code)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			BranchOffice branchOffice=PaymentOrderService.findByCode(code).getBranchOffice();
			if(PaymentOrderService.save(new PaymentOrder(paymentOrderDto.getCode(), paymentOrderDto.getAmount(), paymentOrderDto.getPaymentDate(),branchOffice,paymentOrderDto.getState(),paymentOrderDto.getCurrency()))) {
				logger.info("END [PUT]/update-payment-order:{}");
				return Response.status(Status.OK).entity("SUCCESS UPDATE").type(MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			
		}
	}

	@Override
	public Response delete(String code) {
		// TODO Auto-generated method stub
		logger.info("START [DELETE]/delete-payment-order:{}");
		if(!PaymentOrderService.existByCode(code)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			try {
				PaymentOrderService.delete(code);
				logger.info("END [DELETE]/delete-payment-order:{}");
				return Response.status(Status.OK).entity("SUCCESS DELETE").type(MediaType.APPLICATION_JSON).build();
			}catch(Exception e) {
				return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();

			}
		}
		

	}
}


	
