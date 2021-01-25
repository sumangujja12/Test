package com.multibrand.resources;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import com.multibrand.dto.request.NEIPaypalPaymentRequest;
import com.multibrand.dto.response.NEIPaypalPaymentResponse;
import com.multibrand.helper.ErrorContentHelper;
import com.multibrand.service.NEIPaypalPaymentService;
import com.nrg.cxfstubs.nei.paypal.ZEISUNEIPAYPALPAYMENTResponse;

@Component
@Path("/" + "neipaypalPaymentResource")
public class NEIPaypalPaymentResource extends BaseResource {

	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");

	boolean thrown = false;

	@Autowired
	public NEIPaypalPaymentService paypalPaymentService;

	@Context
	public HttpServletRequest httpRequest;

	@Autowired
	ErrorContentHelper errorContentHelper;

	public enum neiPaypalStatus {

		SUCCESSFULPAYMENT, RETRYPAYMENT, DUPLICATEPAYMENT, BANKROUTINGERROR, FAILEDPAYMENT, INVBANKROUTENUMBER,
		INVBANKACCTNUMBER, SYSTEMERROR, USERNOTALLOWED, NUMBEROFMAXLINESEXCEEDED, NUMBERRANGEERROR

	}

	@GET
	@Path("health")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getLive() {
		Response response = null;
		response = Response.status(200).entity("PayPal Pay Bill Resouse Health Up and Running").build();
		return response;
	}

	@POST
	@Path("neipaypalPayment")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response paypalPayment(@Valid NEIPaypalPaymentRequest request) {

		logger.info(" START ******* paypalPayment API**********");
		Response response = null;
		ZEISUNEIPAYPALPAYMENTResponse ccsResponse = null;

		NEIPaypalPaymentResponse neiPaypalPaymentResponse = new NEIPaypalPaymentResponse();

		try {
			ccsResponse =paypalPaymentService.paypalBillPayment(request,"SeesionId1");

			if (ccsResponse != null) {
				if (ccsResponse.getXCODE().equals("00")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.SUCCESSFULPAYMENT.toString());

				} else if (ccsResponse.getXCODE().equals("01")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.RETRYPAYMENT.toString());

				} else if (ccsResponse.getXCODE().equals("03")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.INVBANKROUTENUMBER.toString());

				}

				else if (ccsResponse.getXCODE().equals("04")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.INVBANKACCTNUMBER.toString());

				} else if (ccsResponse.getXCODE().equals("07")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.SYSTEMERROR.toString());

				} else if (ccsResponse.getXCODE().equals("08")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.NUMBERRANGEERROR.toString());

				} else if (ccsResponse.getXCODE().equals("52")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.DUPLICATEPAYMENT.toString());

				} else if (ccsResponse.getXCODE().equals("53")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.BANKROUTINGERROR.toString());

				} else if (ccsResponse.getXCODE().equals("54")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.FAILEDPAYMENT.toString());

				} else if (ccsResponse.getXCODE().equals("98")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.USERNOTALLOWED.toString());

				} else if (ccsResponse.getXCODE().equals("99")) {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage(neiPaypalStatus.NUMBEROFMAXLINESEXCEEDED.toString());

				} else {
					neiPaypalPaymentResponse.setEotbdId(ccsResponse.getEOTBDID());
					neiPaypalPaymentResponse.setXcode(ccsResponse.getXCODE());
					neiPaypalPaymentResponse.setMessage("UNSUCCESSFULL");
				}

				//response = Response.status(Response.Status.OK).entity(neiPaypalPaymentResponse).build();
			}

		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("").build();
			logger.error(e.fillInStackTrace());
		}
		
		response = Response.status(Response.Status.OK).entity(neiPaypalPaymentResponse).build();
		logger.info("END of the paypalPayment API*************");
		return response;

	}

	public void setHttpRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

}
