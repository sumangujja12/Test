package com.multibrand.dataObjects;

import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.InitialDirContext;

public class People extends InitialDirContext {
	BasicAttributes myAttrs;
	public People(	String uniqueId,
			String sn,
			String userPassword,					
			String email,														
			String userName,
			String cn,			
			String firstInvalidLoginTime,
			String invalidLoginCount,
			String lockedFlag,
			String lockoutTime,
			String reliantRoleDN,
			String bpId
			)throws NamingException
	{
		 myAttrs = new BasicAttributes(true);  //Basic Attributes
		 Attribute objectClass = new BasicAttribute("objectclass"); //Adding Object Classes
		 objectClass.add("top");
		 objectClass.add("person");
		 objectClass.add("organizationalperson");
		 objectClass.add("inetorgperson");
		 objectClass.add("ReliantPerson");

		 myAttrs.put("sn",sn);
		 myAttrs.put("userPassword",userPassword);		
		 myAttrs.put("email",email);		
		 myAttrs.put("uid",userName);
		 myAttrs.put("cn",cn);
		 myAttrs.put("firstinvalidlogintime", firstInvalidLoginTime);
		 myAttrs.put("invalidlogincount", invalidLoginCount);
		 myAttrs.put("lockedflag", lockedFlag);
		 myAttrs.put("lockouttime", lockoutTime);
//		 myAttrs.put("reliantroledn", reliantRoleDN);
		 myAttrs.put("bpid", bpId);
		 myAttrs.put(objectClass);
		 myAttrs.put("uniqueid", uniqueId);	 		 
		 
	}
	public People(	String uniqueId,
			String sn,
			String userPassword,					
			String email,														
			String userName,
			String cn,			
			String firstInvalidLoginTime,
			String invalidLoginCount,
			String lockedFlag,
			String lockoutTime,
			String reliantRoleDN,
			String bpId,
			String catype
			)throws NamingException {
		 myAttrs = new BasicAttributes(true);  //Basic Attributes
		 Attribute objectClass = new BasicAttribute("objectclass"); //Adding Object Classes
		 objectClass.add("top");
		 objectClass.add("person");
		 objectClass.add("organizationalperson");
		 objectClass.add("inetorgperson");
		 objectClass.add("ReliantPerson");

		 myAttrs.put("sn",sn);
		 myAttrs.put("userPassword",userPassword);		
		 myAttrs.put("email",email);		
		 myAttrs.put("uid",userName);
		 myAttrs.put("cn",cn);
		 myAttrs.put("firstinvalidlogintime", firstInvalidLoginTime);
		 myAttrs.put("invalidlogincount", invalidLoginCount);
		 myAttrs.put("lockedflag", lockedFlag);
		 myAttrs.put("lockouttime", lockoutTime);
//		 myAttrs.put("reliantroledn", reliantRoleDN);
		 myAttrs.put("bpid", bpId);
		 myAttrs.put(objectClass);
		 myAttrs.put("uniqueid", uniqueId);	 		 
		 myAttrs.put("catype", catype);
	}
	/*
	 * Implemented methods of DirContext
	 */
	
	public People(Hashtable<?,?> environment)
    throws NamingException{
		super(environment);
	}
	
	
	/*@Override
	public Attributes getAttributes(Name name) throws NamingException {
		return getAttributes(name.toString());

	}
	@Override
	public Attributes getAttributes(String name) throws NamingException {
		if (! name.equals("")){
	         throw new NameNotFoundException();
	      }
	      return myAttrs;
	}
	@Override
	public Attributes getAttributes(Name name, String[] ids)
			throws NamingException {
		
		return getAttributes(name.toString(), ids);

	}
	@Override
	public Attributes getAttributes(String name, String[] ids)
			throws NamingException {
		if(! name.equals(""))
	         throw new NameNotFoundException();
	      Attributes answer = new BasicAttributes(true);
	      Attribute target;
	      for (int i = 0; i < ids.length; i++){
	         target = myAttrs.get(ids[i]);
	         if (target != null){
	            answer.put(target);
	         }
	      }
	      return answer;
	}*/
	
	
	
}
