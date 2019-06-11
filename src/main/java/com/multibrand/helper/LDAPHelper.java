package com.multibrand.helper;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.AttributeModificationException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multibrand.dataObjects.People;
import com.multibrand.service.BaseAbstractService;
import com.multibrand.util.CommonUtil;
import com.multibrand.util.Constants;
import com.multibrand.util.GuidGenerator;
import com.multibrand.util.JNDILDAPConnectionManager;
import com.multibrand.util.XmlUtil;
import com.multibrand.vo.request.ChangeEmailUsrNameRequest;
import com.multibrand.vo.request.DeleteUsrNameRequest;
import com.multibrand.vo.request.UserRegistrationRequest;
import com.multibrand.vo.request.ValidateUserNameRequest;
import com.multibrand.vo.response.ChangeUsernameResponse;
import com.multibrand.vo.response.UserInfoResponse;
import com.multibrand.vo.response.ValidateUsrNameResponse;
import com.multibrand.vo.response.webagent.UserInfoResponseWebAgent;

/**
 * 
 * @author ahanda1
 * 
 *         Helper class for LDAP related functionalities
 * 
 */
@Component
public class LDAPHelper extends BaseAbstractService{

	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
		
	@Autowired
	private UtilityLoggerHelper utilityloggerHelper;
	
	@Autowired
	private JNDILDAPConnectionManager jndiLdapConnectionManager;
	
	/**
	 * This will return DirContext
	 * 
	 * @return dirContext
	 * @throws NamingException
	 */
	protected DirContext getLdapConnection() throws NamingException {
		return jndiLdapConnectionManager.getLDAPDirContext();
	}
	
	/**
	 * This will return DirContext
	 * 
	 * @return dirContext
	 * @throws NamingException
	 */
	protected DirContext getLDAPDirContextCSLR() throws NamingException {
		return jndiLdapConnectionManager.getLDAPDirContextCSLR();
	}	

	
	public String createUser(UserRegistrationRequest usrRegrequest)
			throws RemoteException {

		boolean isUserNotCreated = false;
		try {

			DirContext ctx = getLdapConnection();
			ValidateUserNameRequest validate = new ValidateUserNameRequest();
			validate.setStrLDAPOrg(Constants.LDAP_ORGANISATION);
			validate.setStrUserName(usrRegrequest.getUserName());

			isUserNotCreated = this.validateUsername(validate).isbSuccessFlag();
			if (isUserNotCreated) {

				String uniqueId = GuidGenerator.getGuid(true);
				String groupDN = "uid=" + usrRegrequest.getUserName()
						+ ",ou=people," + "o=" + Constants.LDAP_ORGANISATION;
				Attributes attrs = new BasicAttributes(true);
				Attribute objclass = new BasicAttribute("objectclass");
				objclass.add("top");
				objclass.add("person");
				objclass.add("organizationalperson");
				objclass.add("inetorgperson");
				objclass.add("gmeresperson");
				attrs.put(objclass);

				attrs.put("sn", usrRegrequest.getLastName());
				attrs.put("userPassword", usrRegrequest.getPassword());
				attrs.put("email",usrRegrequest.getEmail());
				attrs.put("uniqueid", uniqueId);
				attrs.put("cn",usrRegrequest.getFirstName()+" "+usrRegrequest.getLastName());
				attrs.put("accountnumber",usrRegrequest.getAccountNumber());
				ctx.createSubcontext(groupDN, attrs);

				return uniqueId;
			}

		} catch (Exception e) {
			logger.error("Exception Occurred in createUser ::: " +e);
		}

		return "";
	}

	public ValidateUsrNameResponse validateUsername(
			ValidateUserNameRequest validate) throws RemoteException {
		ValidateUsrNameResponse response = new ValidateUsrNameResponse();
		DirContext ctx = null;
		String ldapOrg = validate.getStrLDAPOrg();
		try {

			if (ldapOrg != null && Constants.LDAP_ORG_KEY_CSLR.equalsIgnoreCase(ldapOrg)) {
				ctx = getLDAPDirContextCSLR();
			} 
			else
			{
				ctx = getLdapConnection();
			} 
			String searchFilter = "uid=" + validate.getStrUserName();
			String searchBase = "o=" + validate.getStrLDAPOrg();

			NamingEnumeration searchResults = getSearchResults(ctx,
					searchFilter, searchBase);
			response.setbSuccessFlag(!isSearchResultAvailable(searchResults));

			if (!response.isbSuccessFlag())
				response.setStrErrorCode(Constants.MSG_USER_EXISTS);

			ctx.close();

		} catch (NamingException e) {
			logger.info("::::::Exception occured while making connection:::::::::");
			logger.error("Exception Occured in validateUsername ::: " +e);
			
		} catch (Exception e) {
			logger.info(":::::::Service Exception Occured::::::::");
		}
		return response;
	}
	
	public ChangeUsernameResponse changeUserName(
			ChangeEmailUsrNameRequest request) throws RemoteException {
		
		
		DirContext dirCtx = null;
		String ldapOrg = request.getStrLDAPOrg();
		ChangeUsernameResponse response = new ChangeUsernameResponse();
		try {

			if (ldapOrg != null && Constants.LDAP_ORG_KEY_CSLR.equalsIgnoreCase(ldapOrg)) {
				dirCtx = getLDAPDirContextCSLR();
			} 
			else
			{
				dirCtx = getLdapConnection();
			} 

			String ppl = People.class.getSimpleName();
			//String oldGroupDn = "uid=" + changeEmailUserNameRequest.getStrOldUsrName() + ",ou=" + ppl + ",o=" + changeEmailUserNameRequest.getStrLDAPOrg();
			StringBuilder oldGroupDn = new StringBuilder();
			oldGroupDn.append("uid=");
			oldGroupDn.append(request.getStrOldUsrName());
			oldGroupDn.append(",ou=");
			oldGroupDn.append(ppl);
			oldGroupDn.append(",o=");
			oldGroupDn.append(request.getStrLDAPOrg());
			
			//String newGroupDn = "uid=" + changeEmailUserNameRequest.getStrNewEmailUserName() + ",ou=" + ppl + ",o=" + changeEmailUserNameRequest.getStrLDAPOrg();
			StringBuilder newGroupDn = new StringBuilder();
			newGroupDn.append("uid=");
			newGroupDn.append(request.getStrNewEmailUserName());
			newGroupDn.append(",ou=");
			newGroupDn.append(ppl);
			newGroupDn.append(",o=");
			newGroupDn.append(request.getStrLDAPOrg());
			
			dirCtx.rename(oldGroupDn.toString(), newGroupDn.toString());
			response.setErrorCode(Constants.SUCCESS_CODE);
		} catch (NamingException e) {
			logger.info("::::::Exception occured while making connection:::::::::");
			logger.error("Exception Ocuured in changeUserName ::: " +e);
			response.setErrorCode(Constants.MSG_ERR_UPDATE_USER);
		} catch (Exception e) {
			logger.info(":::::::Service Exception Occured::::::::");
			response.setErrorCode(Constants.MSG_ERR_UPDATE_USER);
		}
		return response;
	}
	
	

	private NamingEnumeration getSearchResults(final DirContext dirContext,
			final String searchFilter, final String searchBase)
			throws NamingException, RemoteException {

		final SearchControls constraints = new SearchControls();
		constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
		final NamingEnumeration searchResults = dirContext.search(searchBase,
				searchFilter, constraints);
		return searchResults;
	}

	private boolean isSearchResultAvailable(NamingEnumeration searchResults)
			throws NamingException, RemoteException {
		if (searchResults != null && searchResults.hasMore()) {

			Object obj = searchResults.nextElement();
			logger.info("obj :: " + obj);
			SearchResult object = (SearchResult) obj;
			logger.info("SearchResult object :: " + object);
			
			Attributes attribs = object.getAttributes();
			logger.info("Attributes :: " + attribs);
			NamingEnumeration<Attribute> attribEnumeration = (NamingEnumeration<Attribute>) attribs
					.getAll();
			logger.info("NamingEnumeration<Attribute> :: " + attribEnumeration);
			while (attribEnumeration.hasMore()) {
				Attribute attrib = attribEnumeration.next();
				logger.info(attrib.getID() + " : " + attrib.get().toString());
			}
			return true;
		} else
			return false;
	}

	public boolean validateUser(String userName, String password) throws NamingException{

		Hashtable env = new Hashtable();
		DirContext ctx = null;
		env.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, this.envMessageReader.getMessage(LDAP_PROVIDER_URL));
		env.put(Context.SECURITY_AUTHENTICATION, this.envMessageReader.getMessage(LDAP_SECURITY_AUTHENTICATION));
		env.put(Context.SECURITY_PRINCIPAL, "uid="+userName+",ou=People,o="+Constants.LDAP_ORGANISATION);
		env.put(Context.SECURITY_CREDENTIALS, password);		
		try
		{
			ctx = new InitialDirContext(env);
		}
		catch (Exception e) 
		{
			return false;
		}
		ctx.close();		
		return true;
	}

    public void deleteUser(String userName)throws NamingException{
		
		DirContext ctx = getLdapConnection();
		
		logger.info("uid to be deleted in LDAPHElper[deleteUser()]...."+userName);
		ctx.unbind("uid=" + userName + ", ou=people,o="+Constants.LDAP_ORGANISATION);
		
		ctx.close();
	}
    
    public void deleteUser(DeleteUsrNameRequest request)throws NamingException{
		
    	DirContext dirCtx = null;
		String ldapOrg = request.getStrLDAPOrg();
		ChangeUsernameResponse response = new ChangeUsernameResponse();
		try {

			if (ldapOrg != null && Constants.LDAP_ORG_KEY_CSLR.equalsIgnoreCase(ldapOrg)) {
				dirCtx = getLDAPDirContextCSLR();
			} 
			else
			{
				dirCtx = getLdapConnection();
				
			} 

			logger.info("uid to be deleted in LDAPHElper[deleteUser()]...."+request.getStrUsrName());
	 		dirCtx.unbind("uid=" + request.getStrUsrName() + ", ou=people,o="+ldapOrg);
	 		response.setErrorCode(Constants.SUCCESS_CODE);
	 		
	 		dirCtx.close();
	 		
		} catch (NamingException e) {
			logger.info("::::::Exception occured while making connection:::::::::");
			logger.error("Exception occured in deleteUser ::: "+e);
			response.setErrorCode(Constants.MSG_ERR_DELETE_USER);
		} catch (Exception e) {
			logger.info(":::::::Service Exception Occured::::::::");
			response.setErrorCode(Constants.MSG_ERR_DELETE_USER);
		}
    	
 		
 	}
	/**
	 * This function modification of password for default user information from
	 * LDAP for given user id
	 * 
	 * @author kdeshmu1
	 * @param uid
	 * @param dircode
	 * @param email
	 * @return Attributes
	 * @throws NamingException
	 */
	public Attributes modPasswordUserinfo(final String uid, final int dircode,
			final String newPassword) {

		try {
			DirContext ctx = getLdapConnection();
			
			/*
			ModificationItem[] modslist = new ModificationItem[1];
			modslist[0] = new ModificationItem(dircode, new BasicAttribute("userPassword", newPassword));
			*/
			
			ModificationItem[] modslist = new ModificationItem[3];
			modslist[0] = new ModificationItem(dircode, new BasicAttribute("userPassword", newPassword));
			modslist[1] = new ModificationItem(dircode, new BasicAttribute(LDAP_ATTRIB_CUSTOM_LOCK_OUT, ZERO));
			modslist[2] = new ModificationItem(dircode, new BasicAttribute(LDAP_ATTRIB_INVALID_LOGIN_COUNT, ZERO));

			/**
			ctx.modifyAttributes("uid=" + uid + ",ou=People,o=pennywise.com",modslist);
			Attributes attrs = ctx.getAttributes("uid=" + uid + ",ou=People,o=pennywise.com");
			*/
			ctx.modifyAttributes("uid=" + uid + ",ou=People,o="+Constants.LDAP_ORGANISATION,modslist);
			Attributes attrs = ctx.getAttributes("uid=" + uid+ ",ou=People,o="+Constants.LDAP_ORGANISATION);
			ctx.close();

			return attrs;
		} catch (AttributeModificationException e) {
			logger.error("Problem default modifying " + uid + ":" + dircode
					+ " attributes: ", e);
		} catch (NamingException e) {
			logger.error("Problem default modifying " + uid + ":" + dircode
					+ " attributes: ", e);
		} catch (Exception e) {
			logger.error("Problem default modifying " + uid + ":" + dircode
					+ " attributes: ", e);
		}
		return null;
	}
	
	/**
	 * Change the details provided in the argument
	 * 
	 * @param bindKeyValue
	 *            the id to which the LDAP entry is binded to.
	 * @param attributes
	 *            the key and values map
	 * @throws NamingException
	 */
	public Attributes replaceExistingUserDetails(String bindKeyName, String bindKeyValue, String domain,
			Map<String, String> attributes) throws NamingException, ServiceException, RemoteException {
		
		System.out.println("inside replaceExistingUserDetails");
		DirContext dirCtx = null;
		StringBuilder modifyAttribString = new StringBuilder();
		
		try {

			if (domain != null && Constants.LDAP_ORG_KEY_CSLR.equalsIgnoreCase(domain)) {
				dirCtx = getLDAPDirContextCSLR();
			} 
			else
			{
				dirCtx = getLdapConnection();
			} 


			int noOfModficationItems = attributes.size();
			ModificationItem[] modificationItems = new ModificationItem[noOfModficationItems];
		
			
			Set<String> set = attributes.keySet();
			int itemListCount = 0;
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {

				//String key = (String) iterator.next();
				String key = iterator.next();
				String value = attributes.get(key);

				modificationItems[itemListCount++] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(key, value));
			}
			modifyAttribString.append(bindKeyName);
			modifyAttribString.append("=");
			modifyAttribString.append(bindKeyValue.trim());
			modifyAttribString.append(",ou=");
			modifyAttribString.append(People.class.getSimpleName());
			modifyAttribString.append(",o=");
			modifyAttribString.append(domain);
			dirCtx.modifyAttributes(modifyAttribString.toString(), modificationItems);
			
			Attributes attrs = dirCtx.getAttributes("uid=" + bindKeyValue+ ",ou=People,o="+domain);
			dirCtx.close();

			return attrs;
			
		} catch (AttributeModificationException e) {
			System.out.println(e);
			logger.error("Problem default modifying attributes: ", e);
		} catch (NamingException e) {
			System.out.println(e);
			logger.error("Problem default modifying attributes ", e);
		} catch (Exception e) {
			System.out.println(e);
			logger.error("Problem default modifying  attributes: ", e);
		}
		return null;
	}
	
	/**
	 * This function is determining whether userid or account number
	 * exist based on userId parameter
	 * @author mshukla1
	 * @param userId
	 * @param companyCode
	 * @return userInfoResponse
	 * @throws NamingException 
	 * @throws RemoteException 
	 * 
	 * */
		public UserInfoResponse getUserorAcctInfo(String userId,String companyCode) throws NamingException, RemoteException{
		// User info response with empty.
		UserInfoResponse response = new UserInfoResponse();
		
		DirContext ctx = getLdapConnection();
		
		String accountnumber = userId;
		String regex = "\\d+";
		if(accountnumber.matches(regex))
			accountnumber = CommonUtil.paddedCa(userId.trim());
			
		String searchBase = "o=" + Constants.LDAP_ORGANISATION;
		String uidSearchFilter="uid="+userId;
		String acctSearchFilter="accountnumber="+accountnumber;
		String searchFilter="(|("+uidSearchFilter+")  ("+acctSearchFilter+"))";
				
		NamingEnumeration searchResults=getSearchResults(ctx, searchFilter, searchBase);
		
		if (searchResults != null && searchResults.hasMore()) {

			Object obj = searchResults.nextElement();
			logger.info("obj :: " + obj);
			SearchResult object = (SearchResult) obj;
			logger.info("SearchResult object :: " + object);

			Attributes attribs = object.getAttributes();
			logger.info("Attributes :: " + attribs);
			NamingEnumeration<Attribute> attribEnumeration = (NamingEnumeration<Attribute>) attribs.getAll();
			logger.info("NamingEnumeration<Attribute> :: " + attribEnumeration);
			while (attribEnumeration.hasMore()) {
				Attribute attrib = attribEnumeration.next();
				if(attrib.getID().equals(Constants.LDAP_UID_ATTRIBUTE))
				{
					response.setUserName(attrib.get().toString());
				}
				if(attrib.getID().equals(Constants.LDAP_ACCOUNTNUMBER_ATTRIBUTE)){
					response.setAccountNumber(attrib.get().toString());
				}
				if(attrib.getID().equals(Constants.LDAP_UNIQUEID_ATTRIBUTE)){
					response.setUserUniqueID(attrib.get().toString());
				}
				logger.info(attrib.getID() + " : " + attrib.get().toString());
				ctx.close();
			}
		}		
		return response;
	}
	
public UserInfoResponseWebAgent getUserInfoForWebAgent(String userId,String companyCode, String sessionId) throws Exception{
		
	    long startTime = CommonUtil.getStartTime();
	    UserInfoResponseWebAgent response = new UserInfoResponseWebAgent();
	    try{
	    	
		DirContext ctx = getLdapConnection();
		
		String searchBase = "o=" + Constants.LDAP_ORGANISATION;
		String uidSearchFilter="uid="+userId;
		/*String acctSearchFilter="accountnumber="+userId;
		String searchFilter="(|("+uidSearchFilter+")  ("+acctSearchFilter+"))";*/
				
		NamingEnumeration searchResults=getSearchResults(ctx, uidSearchFilter, searchBase);
		
		if (searchResults != null && searchResults.hasMore()) {

			Object obj = searchResults.nextElement();
			logger.info("obj :: " + obj);
			SearchResult object = (SearchResult) obj;
			logger.info("SearchResult object in getUserInfoForWebAgent()::::: " + object);

			Attributes attribs = object.getAttributes();
			logger.info("Attributes :: " + attribs);
			NamingEnumeration<Attribute> attribEnumeration = (NamingEnumeration<Attribute>) attribs.getAll();
			logger.info("NamingEnumeration<Attribute> :: " + attribEnumeration);
			while (attribEnumeration.hasMore()) {
				Attribute attrib = attribEnumeration.next();
				if(attrib.getID().equals(Constants.LDAP_UID_ATTRIBUTE))
				{
					response.setUserName(attrib.get().toString());
				}
				if(attrib.getID().equals(Constants.LDAP_ACCOUNTNUMBER_ATTRIBUTE)){
					response.setAccountNumber(attrib.get().toString());
				}
				if(attrib.getID().equals(Constants.LDAP_EMAIL_ATTRIBUTE))
				{
					response.setEmailID(attrib.get().toString());
				}
				if(attrib.getID().equals(Constants.LDAP_UNIQUEID_ATTRIBUTE))
				{
					response.setUniqueId(attrib.get().toString());
				}
				logger.info(attrib.getID() + " : " + attrib.get().toString());
				ctx.close();
			}
		}		
		utilityloggerHelper.logTransaction("getUserInfoForWebAgent", false, "userName="+userId+",companyCode="+companyCode,response, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
		if(logger.isDebugEnabled()){
			logger.debug(XmlUtil.pojoToXML("userName="+userId+",companyCode="+companyCode));
	    	logger.debug(XmlUtil.pojoToXML(response));
		}
		return response;
		}catch(Exception ex){
			logger.error(ex);
			utilityloggerHelper.logTransaction("getUserInfoForWebAgent", false, "userName="+userId+",companyCode="+companyCode,ex, "", CommonUtil.getElapsedTime(startTime), "", sessionId, companyCode);
			if(logger.isDebugEnabled())
				logger.debug(XmlUtil.pojoToXML("userName="+userId+",companyCode="+companyCode));
			throw ex;
		}
	}

}
