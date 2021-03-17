package com.multibrand.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.multibrand.service.BaseAbstractService;

@Component
public class JNDILDAPConnectionManager extends BaseAbstractService{

	
	Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	
    public DirContext getLDAPDirContext()throws NamingException{
    	
    	logger.info("Get LDAPDir Context ::::::: Start:::::::");
    	final Hashtable<String,String> env = new Hashtable<String,String>();
    	
    	String providerUrl = this.envMessageReader.getMessage(LDAP_PROVIDER_URL);
    	String securityAuthentication = this.envMessageReader.getMessage(LDAP_SECURITY_AUTHENTICATION);
		String securityPrincipal = this.envMessageReader.getMessage(LDAP_ADMIN_UID);
		String securityCredentials = this.envMessageReader.getMessage(LDAP_SECURITY_CREDENTIALS);
		
		logger.debug("LDAP: providerUrl:{}" ,providerUrl);
		logger.debug("LDAP: securityAuthentication:{}" ,securityAuthentication);
		logger.debug("LDAP: securityPrincipal:{}" ,securityPrincipal);

		
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT_FACTORY);
    	env.put(Context.PROVIDER_URL, providerUrl);
    	env.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
		env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, securityCredentials);
		
    	return new InitialDirContext(env);
    }
    /**
	 * START - Community Solar Implementation : This method used to get the DirContext through the JNDI API for Community Solar applications
	 * @return DirContext 
	 * @throws NamingException
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public DirContext getLDAPDirContextCSLR() throws NamingException {
		
		final Hashtable env = new Hashtable();    // Assign the JNDI environment values in Map
		
		String providerUrl = this.envMessageReader.getMessage(LDAP_PROVIDER_URL_CSLR);
    	String securityAuthentication = this.envMessageReader.getMessage(LDAP_SECURITY_AUTHENTICATION_CSLR);
		String securityPrincipal = this.envMessageReader.getMessage(LDAP_ADMIN_UID_CSLR);
		String securityCredentials = this.envMessageReader.getMessage(LDAP_SECURITY_CREDENTIALS_CSLR);
		
		logger.debug("LDAP: providerUrl:{}" ,providerUrl);
		logger.debug("LDAP: securityAuthentication:{}" ,securityAuthentication);
		logger.debug("LDAP: securityPrincipal:{}" ,securityPrincipal);
		
		
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, Constants.INITIAL_CONTEXT_FACTORY);

		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, securityAuthentication);
		env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, securityCredentials);
		
		
		return new InitialDirContext(env);	
	}
    
    
}
