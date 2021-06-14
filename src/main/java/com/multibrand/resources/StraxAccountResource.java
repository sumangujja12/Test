package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.multibrand.bo.StraxBO;
import com.multibrand.util.CommonUtil;
import com.multibrand.vo.request.StraxContractCancelRequest;
import com.multibrand.vo.request.StraxInvoiceRequest;
import com.multibrand.vo.response.StraxContractCancelResponse;
import com.multibrand.vo.response.StraxInvoiceResponse;

/**
 * This Resource is to handle the SecurityTrax requests.
 * 
 */
@Component
@Path("straxResource")
public class StraxAccountResource {
		
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private StraxBO straxBO;
	
	@Context 
	private HttpServletRequest httpRequest;
	
	@POST
	@Path("/protected/cancelContract")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON })
	public Response cancelStraxAccount(@RequestBody StraxContractCancelRequest request) {
		
		Response response = null;
		StraxContractCancelResponse straxContractCancelResponse = straxBO.cancelStraxContract(request, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(straxContractCancelResponse).build();
		return response;

	}
	
	
	@POST
	@Path("/protected/invoice")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Response invoiceStraxAccount(@RequestBody StraxInvoiceRequest request){
		
		Response response = null;
		StraxInvoiceResponse straxInvoiceResponse = straxBO.invoiceStraxContract(request,  httpRequest.getSession(true).getId());
				
		response = Response.status(200).entity(straxInvoiceResponse).build();
				
		return response;
		
	}
}