package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.multibrand.bo.BillingBO;
import com.multibrand.vo.request.SecurityContractCancelRequest;
import com.multibrand.vo.request.SecurityInvoiceRequest;
import com.multibrand.vo.response.SecurityContractCancelResponse;
import com.multibrand.vo.response.SecurityInvoiceResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This Resource is to handle all the Billing Related API calls.
 * 
 * @author PravinPatil
 */
@Component
@Path("securityContract")
public class SecurityContractResource {
	
	@Autowired
	private BillingBO billingBO;
	
	@Autowired
	SecurityInvoiceResponse securutyInvoiceResponse;
	
	@Autowired
	SecurityContractCancelResponse securityContractCancelResponse;
	
	@Context 
	private HttpServletRequest httpRequest;
	
	@POST
	@Path("cancelContract")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEmpDetails(@RequestBody SecurityContractCancelRequest request) {
		Response response = null;
		if(request.getsTraxLeadId()!= null && request.getsTraxLeadId() != null && request.getEndDate() != null )
		{
			securityContractCancelResponse.setStatus("true");
		}
		else
		{
			securityContractCancelResponse.setStatus("false");
		}
		//SecurityContractCancelRequest storeUpdatePayAccountResponse = billingBO.storePayAccount(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(securityContractCancelResponse.getStatus()).build();
		return response;

	}
	
	
	@POST
	@Path("invoice")
	@Consumes({ MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_JSON})
	public Response getInvoice(@RequestBody SecurityInvoiceRequest request){
		Response response = null;
		
		securutyInvoiceResponse.setMsgCode(request.getCaNumber()+request.getTotalAmount());
		
		//SecurityContractCancelRequest storeUpdatePayAccountResponse = billingBO.storePayAccount(request, httpRequest.getSession(true).getId());
		response = Response.status(200).entity(securutyInvoiceResponse.getMsgCode()).build();
				
		return response;
		
	}
}
