/**
 * 
 */
package com.multibrand.resources;

import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multibrand.dto.request.TPVRequest;
import com.multibrand.service.TPVService;
import com.multibrand.vo.response.tpv.TransUpdResponseVO;



/**
 * 
 * TPV Service Implmentation
 * @author kbhulla1
 * @version 1.0
 */
@RestController("tpvResource")
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
	@PostMapping(value = "/tpvApi/tpvApiTransUpd", consumes = {	MediaType.APPLICATION_FORM_URLENCODED_VALUE,MediaType.APPLICATION_JSON_VALUE }, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public TransUpdResponseVO tpvApiTransUpd(HttpServletRequest request){
		
		logger.debug("Inside tpvApiTransUpd in TPV Resource");
		//Response response = null;
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
					
			//response = Response.status(200).entity(responseVO).build();
			logger.info("END-tpvApiTransUpd");
			
		}catch(Exception e){
			responseVO = new TransUpdResponseVO();
			responseVO.setType(e.getMessage());
			responseVO.setMessage(e.getMessage());
			//response = Response.status(200).entity(responseVO).build();
		}
				
		return responseVO;
		
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
	@PostMapping(value = "/tpvApi/tpvApiTransUpdate", consumes = {	MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TransUpdResponseVO tpvApiTransUpdate(@Valid TPVRequest tpvReq){
		
		logger.debug("Inside tpvApiTransUpd in TPV Resource");
		//Response response = null;
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
					
			//response = Response.status(200).entity(responseVO).build();
			logger.info("END-tpvApiTransUpd");
			
		}catch(Exception e){
			responseVO = new TransUpdResponseVO();
			responseVO.setType(e.getMessage());
			responseVO.setMessage(e.getMessage());
			//response = Response.status(200).entity(responseVO).build();
		}
				
		return responseVO;
		
	}
	
	
}
