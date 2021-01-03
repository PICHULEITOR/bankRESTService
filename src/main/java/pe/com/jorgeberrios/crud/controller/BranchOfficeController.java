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

import pe.com.jorgeberrios.crud.dto.BranchOfficeDto;

@Path("/sucursal")
public interface BranchOfficeController {
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getBranchOffices();
	 
	 @GET @Path("/{code}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getBranchOfficeByCode(@PathParam("code")String code);
	 
	 @POST
	 @Path("codigo-banco/{bankCode}")
	 @Produces( MediaType.APPLICATION_JSON )
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response createBranchOffice(@PathParam("bankCode")String bankCode,BranchOfficeDto branchOfficeDto);
	 
	 @PUT
	 @Path("/{code}")
	 @Consumes( MediaType.APPLICATION_JSON)
	 @Produces( MediaType.APPLICATION_JSON)
	 public Response update(@PathParam("code") String code, BranchOfficeDto branchOfficeDto);
	 
	 @DELETE
	 @Path("/{code}")
	 @Produces( MediaType.APPLICATION_JSON)
	 public Response delete(@PathParam("code") String code);
}
