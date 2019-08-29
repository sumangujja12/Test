/**
 * 
 */
package com.multibrand.resources;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.service.TPVService;
import com.multibrand.vo.response.tpv.TransUpdResponseVO;
import com.multibrand.dto.request.TPVRequest;



/**
 * 
 * TPV Service Implmentation
 * @author kbhulla1
 * @version 1.0
 */
@Component("tpvResource")
@Path("tpvApi")
public class TPVResource {

	/**
	 * 
	 */
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	@Autowired
	private TPVService tpvService;
	
	/**
	 * @author kbhulla1
	 * @param IvAcctNum
	 * @param IvApprovedBy
	 * @param IvDateTime
	 * @param IvReason
	 * @param IvResult
	 * @param IvTransactionid
	 * @return Response
	 * @throws RemoteException 
	 * @throws ServiceException 
	 */
	@POST
	@Path("tpvApiTransUpd")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response tpvApiTransUpd(@Context HttpServletRequest request){
		
		logger.debug("Inside tpvApiTransUpd in TPV Resource");
		Response response = null;
		TransUpdResponseVO responseVO = null;
		try{
			
			logger.info("START-tpvApiTransUpd");
			@SuppressWarnings("rawtypes")
			Enumeration params = request.getParameterNames(); 
			while(params.hasMoreElements()){
			 String paramName = (String)params.nextElement();
			 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
			}
			responseVO = tpvService.tpvTransUpd(request.getParameter("IvAcctNum"),
					request.getParameter("IvApprovedBy"),
					request.getParameter("IvDateTime"),
					request.getParameter("IvReason"),
					request.getParameter("IvResult"),
					request.getParameter("IvTransactionid"));
					
			response = Response.status(200).entity(responseVO).build();
			logger.info("END-tpvApiTransUpd");
			
		}catch(Exception e){
			responseVO = new TransUpdResponseVO();
			responseVO.setType(e.getMessage());
			responseVO.setMessage(e.getMessage());
			response = Response.status(200).entity(responseVO).build();
		}
				
		return response;
		
	}
	
	/**
	 * @author kbhulla1
	 * @param IvAcctNum
	 * @param IvApprovedBy
	 * @param IvDateTime
	 * @param IvReason
	 * @param IvResult
	 * @param IvTransactionid
	 * @return Response
	 * @throws RemoteException 
	 * @throws ServiceException 
	 */
	@POST
	@Path("tpvApiTransUpdate")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response tpvApiTransUpdate(@Valid TPVRequest tpvReq){
		
		logger.debug("Inside tpvApiTransUpd in TPV Resource");
		Response response = null;
		TransUpdResponseVO responseVO = null;
		try{
			
			logger.info("START-tpvApiTransUpd");
			
			logger.info(tpvReq.getIvAcctNum()+","+
					tpvReq.getIvApprovedBy()+","+
					tpvReq.getIvDateTime()+","+
					tpvReq.getIvReason()+","+
					tpvReq.getIvResult()+","+
					tpvReq.getIvTransactionid());

			responseVO = tpvService.tpvTransUpd(tpvReq.getIvAcctNum(),
					tpvReq.getIvApprovedBy(),
					tpvReq.getIvDateTime(),
					tpvReq.getIvReason(),
					tpvReq.getIvResult(),
					tpvReq.getIvTransactionid());
					
			response = Response.status(200).entity(responseVO).build();
			logger.info("END-tpvApiTransUpd");
			
		}catch(Exception e){
			responseVO = new TransUpdResponseVO();
			responseVO.setType(e.getMessage());
			responseVO.setMessage(e.getMessage());
			response = Response.status(200).entity(responseVO).build();
		}
				
		return response;
		
	}
	
	
}
