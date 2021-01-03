package pe.com.jorgeberrios.crud.controller;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.com.jorgeberrios.crud.dto.PaymentOrderDto;
@Path("/orden-de-pago")
public interface PaymentOrderController {
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getPaymentOrders();
	 
	 @GET @Path("/{code}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getPaymentOrderByCode(@PathParam("code")String code);
	 
	 @GET @Path("codigo-sucursal/{branchOfficeCode}/tipo-moneda/{currencyType}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getPaymentOrderByBranchOfficeCodeAndCurrencyType(@PathParam("branchOfficeCode")String branchOfficeCode,@PathParam("currencyType")String currencyType);
	 
	 @POST
	 @Path("/codigo-sucursal/{branchOfficeCode}")
	 @Produces( MediaType.APPLICATION_JSON )
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response createPaymentOrder(@PathParam("branchOfficeCode")String branchOfficeCode,PaymentOrderDto paymentOrderDto);
	 
	 @PUT
	 @Path("/{code}")
	 @Consumes( MediaType.APPLICATION_JSON)
	 @Produces( MediaType.APPLICATION_JSON)
	 public Response update(@PathParam("code") String code, PaymentOrderDto paymentOrderDto);
	 
	 @DELETE
	 @Path("/{code}")
	 @Produces( MediaType.APPLICATION_JSON)
	 public Response delete(@PathParam("code") String code);
	 
}
