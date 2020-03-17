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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.bo.GMDBO;
import com.multibrand.vo.response.gmd.GMDStatementBreakDownResponse;


/** This Resource is to handle all the GMD APP Related API calls.
 * 
 * @author rpendur1
 */
@Component
@Path("gmdResource")
public class GMDResource extends BaseResource {
	
	private static Logger logger = LogManager.getLogger(GMDResource.class);
	
	@Context 
	private HttpServletRequest httpRequest;
	
	/** Object of BillingBO class. */
	@Autowired
	private GMDBO gmdBO;
	
	
	//This service is to provide GMD Statement details from CCS Service
	
	

	@POST
	@Path(API_GET_GMD_STATEMENT_DATA)
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	
	public Response getGMDStatementDetails(@FormParam("contractAccountNumber") String accountNumber,@FormParam("companyCode") String companyCode, @FormParam("brandName") String brandName,
			@FormParam("esiId") String esiId, @FormParam("year") String year, @FormParam("month") String month) {
		
		logger.info(" START ******* getGMDStatementDetails API**********");
		Response response = null;
		GMDStatementBreakDownResponse gmdStatementBreakDownResp = gmdBO.getGMDStatementDetails(accountNumber, companyCode, esiId, year, month, httpRequest.getSession(true).getId());
		
		response = Response.status(200).entity(gmdStatementBreakDownResp).build();
		
		logger.info("END of the getGMDStatementDetails API*************");
		return response;
		
	}
}	
