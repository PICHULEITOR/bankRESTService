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

import pe.com.jorgeberrios.crud.dto.BankDto;

@Path("/banco")
public interface BankController {
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getBanks();
	 
	 @GET @Path("/{code}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getBankByCode(@PathParam("code")String code);
	 

	 @POST
	 @Produces( MediaType.APPLICATION_JSON )
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response createBank(BankDto BankDto);
	 
	 @PUT
	 @Path("/{code}")
	 @Consumes( MediaType.APPLICATION_JSON)
	 @Produces( MediaType.APPLICATION_JSON)
	 public Response update(@PathParam("code") String code, BankDto BankDto);
	 
	 @DELETE
	 @Path("/{code}")
	 @Produces( MediaType.APPLICATION_JSON)
	 public Response delete(@PathParam("code") String code);
}
