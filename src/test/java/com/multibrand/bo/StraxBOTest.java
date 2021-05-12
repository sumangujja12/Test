package com.multibrand.bo;

import static org.mockito.Mockito.when;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.multibrand.domain.StraxCancelAccountRequest;
import com.multibrand.domain.StraxCancelAccountResponse;
import com.multibrand.domain.StraxInvoiceAccountRequest;
import com.multibrand.domain.StraxInvoiceAccountResponse;
import com.multibrand.service.StraxAccountService;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.vo.request.InvoiceItemCategory;
import com.multibrand.vo.request.StraxContractCancelRequest;
import com.multibrand.vo.request.StraxInvoiceRequest;
import com.multibrand.vo.response.StraxContractCancelResponse;
import com.multibrand.vo.response.StraxInvoiceResponse;


@Test(singleThreaded = true)
public class StraxBOTest implements Constants{

	@InjectMocks
	StraxBO straxBO;

	@Mock
	StraxAccountService straxAccountService;

	@Mock
	WebServiceTemplate webServiceTemplateForStraxInvoiceRequest;

	@Mock
	WebServiceTemplate webServiceTemplateForStraxCancelRequest;
	
	@Mock
	EnvMessageReader envMessageReader;

	@Mock
	ReloadableResourceBundleMessageSource environmentMessageSource;
	
	

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testStraxCancelRequest() {
		
		StraxContractCancelRequest request = new StraxContractCancelRequest();
		request.setCaNumber("0000000000");
		request.setStraxLeadID("09090");
		request.setCancellationDate("1990/09/09");
		
		StraxCancelAccountResponse straxCancelAccountResponse = new StraxCancelAccountResponse();
		straxCancelAccountResponse.setErrorCode("");
		try {
			Mockito.when(envMessageReader.getMessage("CCSUSERNAME")).thenReturn("testUser");
			Mockito.when(envMessageReader.getMessage("CCSPASSWORD")).thenReturn("testPwd");
			
			when(straxAccountService.cancelStraxContract(Matchers.any(StraxCancelAccountRequest.class), Matchers.anyString(), Matchers.anyString())).thenReturn(straxCancelAccountResponse);
			StraxContractCancelResponse straxContractCancelResponse = straxBO.cancelStraxContract(request, "1");
			Assert.assertEquals(null, straxContractCancelResponse.getErrorCode());
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStraxCancelRequest_Exception() {
		
		StraxContractCancelRequest request = new StraxContractCancelRequest();
		request.setCaNumber("0000000000");
		request.setStraxLeadID("09090");
		request.setCancellationDate("1990/09/09");
		
		StraxCancelAccountResponse straxCancelAccountResponse = new StraxCancelAccountResponse();
		straxCancelAccountResponse.setErrorCode("");
		try {
			Mockito.when(envMessageReader.getMessage("CCSUSERNAME")).thenReturn("testUser");
			Mockito.when(envMessageReader.getMessage("CCSPASSWORD")).thenReturn("testPwd");
			
			when(straxAccountService.cancelStraxContract(Matchers.any(StraxCancelAccountRequest.class), Matchers.anyString(), Matchers.anyString())).thenThrow(new RemoteException());
			StraxContractCancelResponse straxContractCancelResponse = straxBO.cancelStraxContract(request, "1");
			Assert.assertEquals(Constants.RESULT_CODE_EXCEPTION_FAILURE, straxContractCancelResponse.getResultCode());
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testStraxInvoice() {
		StraxInvoiceRequest request = new StraxInvoiceRequest();
		request.setCaNumber("3434324232");
		request.setStraxLeadID("23423");
		request.setTotalAmount("24");
		request.setInvoiceID("23");
		List<InvoiceItemCategory> invoiceItems = new ArrayList<>();
		InvoiceItemCategory item1 = new InvoiceItemCategory();
		item1.setAmount("10");
		item1.setItemCategory("ret");
		InvoiceItemCategory item2 = new InvoiceItemCategory();
		item2.setAmount("14");
		item2.setItemCategory("tet");
		invoiceItems.add(item1);
		invoiceItems.add(item2);
		request.setInvoiceItems(invoiceItems);
		
		
		StraxInvoiceAccountResponse straxInvoiceAccountResponse = new StraxInvoiceAccountResponse();
		
		
		
		try {
			Mockito.when(envMessageReader.getMessage("CCSUSERNAME")).thenReturn("testUser");
			Mockito.when(envMessageReader.getMessage("CCSPASSWORD")).thenReturn("testPwd");
			
			when(straxAccountService.invoiceStraxContract(Matchers.any(StraxInvoiceAccountRequest.class), Matchers.anyString(), Matchers.anyString())).thenReturn(straxInvoiceAccountResponse);
			StraxInvoiceResponse straxInvoiceResponse = straxBO.invoiceStraxContract(request, "1");
			//Assert.assertEquals(Constants.RESULT_CODE_EXCEPTION_FAILURE, straxContractCancelResponse.getResultCode());
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
