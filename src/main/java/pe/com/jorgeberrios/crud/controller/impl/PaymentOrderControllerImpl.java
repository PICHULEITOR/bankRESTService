package pe.com.jorgeberrios.crud.controller.impl;

import java.util.ArrayList;
import net.minidev.json.JSONObject;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
//import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.json.JSONObject;

import pe.com.jorgeberrios.crud.controller.PaymentOrderController;
import pe.com.jorgeberrios.crud.dto.PaymentOrderDto;
//import pe.com.jorgeberrios.crud.entity.Bank;
import pe.com.jorgeberrios.crud.entity.BranchOffice;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;
import pe.com.jorgeberrios.crud.entity.PaymentOrderCurrency;
import pe.com.jorgeberrios.crud.entity.PaymentOrderState;
import pe.com.jorgeberrios.crud.mapper.PaymentOrderMapper;
import pe.com.jorgeberrios.crud.service.BranchOfficeService;
import pe.com.jorgeberrios.crud.service.PaymentOrderService;
import pe.com.jorgeberrios.util.ConvertToUtils;
//import io.swagger.annotations.Api;
//import org.springframework.stereotype.Service;

//@Path("/payment_order")
//@Api("/payment_order")
//@Service
public class PaymentOrderControllerImpl implements PaymentOrderController {

	//private final ObjectMapper JSON_MAPPER=new ObjectMapper();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaymentOrderService paymentOrderService;
	@Autowired
	private BranchOfficeService branchOfficeService;
	@Autowired
	private PaymentOrderMapper paymentOrderMapper;
	
	@Override
	public Response getPaymentOrderByBranchOfficeCodeAndCurrencyType(String branchOfficeCode, String currencyType) {
		// TODO Auto-generated method stub
		logger.info("START [GET]/get-payment-order-by-code:{}");
		JSONObject obj=new JSONObject();
		//PaymentOrderDto paymentOrderDto=new PaymentOrderDto();
		List<PaymentOrderDto> listPaymentOrders;
		PaymentOrderCurrency currencyTypeEnum;
		try {
			
			if(currencyType.equals("1")) {
				currencyTypeEnum=PaymentOrderCurrency.dolares;
			}
			else if(currencyType.equals("0")) {
				currencyTypeEnum=PaymentOrderCurrency.soles;
			}
			else {
				currencyTypeEnum=PaymentOrderCurrency.soles;
			}
			
			listPaymentOrders=paymentOrderMapper.toListDto(paymentOrderService.getByBranchOfficeCodeAndCurrencyType(branchOfficeCode,currencyTypeEnum.toString()));
		    obj.put("PaymentOrders",listPaymentOrders);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		
		if(listPaymentOrders==null) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

		}else {
			logger.info("END [GET]/get-payment-order-by-code:{}",ConvertToUtils.convertFromObjectToJson(listPaymentOrders));

			return Response.status(Status.OK).entity(obj).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response getPaymentOrders() {
		// TODO Auto-generated method stub
		logger.info("START [GET]/get-payment-orders:{}");
		//JSONObject obj=new JSONObject();
		List<PaymentOrderDto> listOfPaymentOrders=new ArrayList<>();
		try {
			listOfPaymentOrders=paymentOrderMapper.toListDto(paymentOrderService.findAll());
			//obj.put("PaymentOrders",listOfPaymentOrders);

		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		logger.info("END [GET]/get-payment-orders:{}",ConvertToUtils.convertFromObjectToJson(listOfPaymentOrders));
		return Response.status(Status.OK).entity(listOfPaymentOrders).type(MediaType.APPLICATION_JSON).build();
	}

	@Override
	public Response getPaymentOrderByCode(String code) {
		logger.info("START [GET]/get-payment-order-by-code:{}");
		//JSONObject obj=new JSONObject();
		PaymentOrderDto paymentOrderDto=new PaymentOrderDto();
		try {
			paymentOrderDto=paymentOrderMapper.toDto(paymentOrderService.findByCode(code));
			//obj.put("PaymentOrder",paymentOrderDto);
			
		}catch(Exception e) {
			logger.error(e.getMessage());
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
		
		if(paymentOrderDto==null) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

		}else {
			logger.info("END [GET]/get-payment-order-by-code:{}",ConvertToUtils.convertFromObjectToJson(paymentOrderDto));

			return Response.status(Status.OK).entity(paymentOrderDto).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@Override
	public Response createPaymentOrder(String branchOfficeCode,PaymentOrderDto paymentOrderDto) {
		// TODO Auto-generated method stub
		logger.info("START [POST]/create-payment-order:{}");
		try {
			
			if(paymentOrderService.create(branchOfficeCode, paymentOrderDto)) {
				logger.info("END [POST]/create-payment-order:{}");
				return Response.status(Status.OK).entity("SUCCESS CREATE").type(MediaType.APPLICATION_JSON).build();
			}
			else {
				return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
			}
		}catch(Exception e) {
			return Response.status(Status.EXPECTATION_FAILED).entity("AN ERROR OCURRED TRYING TO CREATE").type(MediaType.APPLICATION_JSON).build();
		}
			
			
		
		
		
	}

	@Override
	public Response update(String code, PaymentOrderDto paymentOrderDto) {
		// TODO Auto-generated method stub
		logger.info("START [PUT]/update-payment-order:{}");
		
		try {
			BranchOffice branchOffice=paymentOrderService.findByCode(code).getBranchOffice();
			if(paymentOrderService.save(new PaymentOrder(paymentOrderDto.getCode(), paymentOrderDto.getAmount(), paymentOrderDto.getPaymentDate(),branchOffice,paymentOrderDto.getState(),paymentOrderDto.getCurrency()))) {
				logger.info("END [PUT]/update-payment-order:{}");
				return Response.status(Status.OK).entity("SUCCESS UPDATE").type(MediaType.APPLICATION_JSON).build();
			}else {
				return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();

			}
				
		}catch(Exception e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
		}
			
		
	}

	@Override
	public Response delete(String code) {
		// TODO Auto-generated method stub
		logger.info("START [DELETE]/delete-payment-order:{}");
		if(!paymentOrderService.existByCode(code)) {
			return Response.status(Status.NOT_FOUND).entity("NO DATA FOUND").type(MediaType.APPLICATION_JSON).build();
		}else {
			try {
				paymentOrderService.delete(code);
				logger.info("END [DELETE]/delete-payment-order:{}");
				return Response.status(Status.OK).entity("SUCCESS DELETE").type(MediaType.APPLICATION_JSON).build();
			}catch(Exception e) {
				return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();

			}
		}
		

	}
}


	
