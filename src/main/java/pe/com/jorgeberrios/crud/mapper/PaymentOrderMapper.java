package pe.com.jorgeberrios.crud.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pe.com.jorgeberrios.crud.dto.PaymentOrderDto;
import pe.com.jorgeberrios.crud.entity.PaymentOrder;

@Service
public class PaymentOrderMapper {
	public PaymentOrderDto toDto(PaymentOrder paymentOrder) {
		if(paymentOrder==null) {
			return null;
		}else {
			return new PaymentOrderDto(paymentOrder.getCode(),paymentOrder.getAmount(),paymentOrder.getPaymentDate(),paymentOrder.getState(),paymentOrder.getCurrency());
		}
	}
	public List<PaymentOrderDto> toListDto(List<PaymentOrder> listPaymentOrder){
		return listPaymentOrder.stream().map(p->toDto(p)).collect(Collectors.toList());
	}
	
}
