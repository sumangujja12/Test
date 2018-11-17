package com.multibrand.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * 
 * @author ahanda1
 * This class is for copying JavaBean objects from Redbull service responses to objects of JavaBeans for GMR REST service response
 * 
 * This class will do a deep copy for Javabean objects and set all the properties in the destination JavaBean object
 * This class will take care for copying properties of following types:
 * 1) Primitives/ Primitive Wrappers/ String
 * 2) User defined java beans
 * 3) Arrays of types in 1) or Arrays of UserDefined java bean objects
 * 4) Lists of types in 1) or Lists of UserDefined java bean objects 
 *
 * While copying NRGWS response objects to objects of java beans for NRGREST service response it will replace the null values
 * for a property by empty tags or default values like 0, 0.0 or false for primitive types (or wrappers of primitive types) of 
 * properties. For string type properties it will put empty strings.
 */
public class JavaBeanUtil {
	
	private static Logger logger = LogManager.getLogger("NRGREST_LOGGER");
	
	private static Set<String> TYPE1= new HashSet<String>();
	static {
		
		TYPE1.add("short");
		TYPE1.add("int");
		TYPE1.add("float");
		TYPE1.add("double");
		TYPE1.add("long");
		TYPE1.add("boolean");
		TYPE1.add("byte");
		TYPE1.add("java.lang.Integer");
		TYPE1.add("java.lang.Float");
		TYPE1.add("java.lang.Double");
		TYPE1.add("java.lang.Long");
		TYPE1.add("java.lang.Boolean");
		TYPE1.add("java.lang.String");
		TYPE1.add("java.math.BigDecimal");
		TYPE1.add("java.math.BigInteger");
		TYPE1.add("java.lang.Short");
		TYPE1.add("java.util.Calendar");
		
		
	}	
	

	public static void copy(Object obj, Object obj_copy) throws Exception{
		
		try {
			copyObject(obj,obj_copy);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			throw new Exception(e.getMessage());
		}
		
	}
	
    public static void copyObject(Object obj, Object obj_copy) throws SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException, InstantiationException{
		
		
		Field[] fields = obj.getClass().getDeclaredFields();
		
		// declared fields are categorized into 3 types :
		// 1) Primitives/ Primitive Wrappers/ String
		// 2) User defined java beans
		// 3) Arrays of types in 1) or Arrays of UserDefined java beans
		// 4) List 
		
		for(Field field : fields){
			
			// if field is of Type1 
			if(TYPE1.contains(field.getType().getCanonicalName())){
				
				Field memberVar = null;
				try {
					memberVar = obj_copy.getClass().getDeclaredField(field.getName());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					logger.debug("Property not found in target object :: "+ field.getName());
					continue;
				}
				
				logger.debug("Found field :: "+ memberVar.getName());
				
				String temp = memberVar.getName().substring(0, 1);
				StringBuffer buffer = new StringBuffer();
				buffer.append("set");
				buffer.append(temp.toUpperCase());
				buffer.append(memberVar.getName().substring(1));
				
				String setterName = buffer.toString();
				logger.debug("SetterName : "+ setterName);
				
				Method setter = obj_copy.getClass().getDeclaredMethod(setterName, memberVar.getType());
				
				
				String getterName = setterName.replace("set", "get");
				if(field.getType().getCanonicalName().equalsIgnoreCase("boolean")) getterName = setterName.replace("set", "is");
				
				logger.debug("Getter Name ::: " + getterName);
				Method getter = obj.getClass().getDeclaredMethod(getterName);
				
				Object value = getter.invoke(obj);
				if(value == null){
				
					if(field.getType().getCanonicalName().equalsIgnoreCase("java.lang.Integer")) value = 0;
					if(field.getType().getCanonicalName().equalsIgnoreCase("java.lang.Float")) value = 0.0f;
					if(field.getType().getCanonicalName().equalsIgnoreCase("java.lang.Double")) value = 0.0;
					if(field.getType().getCanonicalName().equalsIgnoreCase("java.lang.Long")) value = 0l;
					if(field.getType().getCanonicalName().equalsIgnoreCase("java.lang.Boolean")) value = false;
					if(field.getType().getCanonicalName().equalsIgnoreCase("java.lang.String")) value = "";
					
				}				
				setter.invoke(obj_copy,  value);
				
			}
			///////////////////////////////// processing of arrays begin///////////////////////////////			
			else if(field.getType().getCanonicalName().endsWith("[]")){
				
                Field memberVar = null;
				try {
					memberVar = obj_copy.getClass().getDeclaredField(field.getName());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					logger.debug("Property not found in target object :: "+ field.getName());
					continue;
				}
				
				logger.debug("Found field :: "+ memberVar.getName());
				
				String temp = memberVar.getName().substring(0, 1);
				StringBuffer buffer = new StringBuffer();
				buffer.append("set");
				buffer.append(temp.toUpperCase());
				buffer.append(memberVar.getName().substring(1));
				
				String setterName = buffer.toString();
				logger.debug("SetterName : "+ setterName);
				
				Method setter = obj_copy.getClass().getDeclaredMethod(setterName, memberVar.getType());
				
				
				String getterName = setterName.replace("set", "get");
				//if(field.getType().getCanonicalName().equalsIgnoreCase("boolean")) getterName = setterName.replace("set", "is");
				
				logger.debug("Getter Name ::: " + getterName);
				Method getter = obj.getClass().getDeclaredMethod(getterName);
				
				//logger.debug("component class :: " +getter.invoke(obj).getClass().getComponentType().getCanonicalName());
				//String arrayType = getter.invoke(obj).getClass().getComponentType().getCanonicalName();
				
				String arrayType = memberVar.getType().getCanonicalName().replace("[]", "").trim(); // getting the array type from the Destination Object hierarchy 
				
				//logger.debug("component type :: " +getter.invoke(obj).getClass().getComponentType());
				//logger.debug("length of array :: " + ((Object[])getter.invoke(obj)).length);
				
			    if(TYPE1.contains(arrayType)){
			    	   logger.debug("array component type belongs to TYPE1");  
			    	   
			    	
			    	   if(getter.invoke(obj) == null){
			    		   // for processing null values in the array type property :: Start 		
			    		   logger.debug("array component type belongs to TYPE1 is null"); 
			    		   Class destCompClass = Class.forName(arrayType);
			    		   int length =0;
			    		   Object dest =  Array.newInstance(destCompClass, length);
			    		   setter.invoke(obj_copy, dest);
						    // for processing null values in the array type property :: End				    		   
			    	   }else{
			    		   logger.debug("array component type belongs to TYPE1 is NOT null");
			    		    // call the setter for setting up the new array in parent object
			    		   setter.invoke(obj_copy,  getter.invoke(obj));
			    	   }
			    	   	   
			    } else{
			    	// array component is of type Java Bean
			    	logger.debug("array component type is of user defined JavaBean :: "+ arrayType);
			    	
			    	// get the array object which is to be copied 
			    	if(getter.invoke(obj)!= null){
			         logger.debug("array component type is of user defined JavaBean :: "+ arrayType +" is NOT null");
			    	Object orig[] = (Object[])getter.invoke(obj);
			    	int length = orig.length;
			    	Class destCompClass = Class.forName(arrayType);
			    	
			    	Object dest =  Array.newInstance(destCompClass, length);
			    	 
			    	   for(int i =0; i<length; i++){
			    		   
			    		   Object replica = destCompClass.newInstance();
			    		   Object original = orig[i];
			    		   
			    		   // do the recursive call for copying java bean
			    		   copyObject(original, replica);
			    		   
			    		   // add the replica in new Array
			    		   Array.set(dest, i, replica);
			    		
			    	   }
			    	   
			    	// call the setter for setting up the new array in parent object
			    	   setter.invoke(obj_copy, dest );
			    	
			    	}else{
			    		
			    		// for processing null values in the array type property :: Start 		
			    		   logger.debug("array component type is of user defined JavaBean :: "+ arrayType +" is null"); 
			    		   Class destCompClass = Class.forName(arrayType);
			    		   int length =0;
			    		   Object dest =  Array.newInstance(destCompClass, length);
			    		   setter.invoke(obj_copy, dest);
						    // for processing null values in the array type property :: End	
			    		
			    	}
			    	
			    	
			    }
				
			}
			
	///////////////////////////////// processing of arrays complete///////////////////////////////
			
			
   ////////////////////////////////// processing Lists ///////////////////////////////////////////			
			else if(field.getType().getCanonicalName().equalsIgnoreCase("java.util.List")){
				
                Field memberVar= null;
				try {
					memberVar = obj_copy.getClass().getDeclaredField(field.getName());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					logger.debug("Property not found in target object :: "+ field.getName());
					continue;
				}
				
				logger.debug("Found field :: "+ memberVar.getName());
				
				String temp = memberVar.getName().substring(0, 1);
				StringBuffer buffer = new StringBuffer();
				buffer.append("set");
				buffer.append(temp.toUpperCase());
				buffer.append(memberVar.getName().substring(1));
				
				String setterName = buffer.toString();
				logger.debug("SetterName : "+ setterName);
				
				Method setter = obj_copy.getClass().getDeclaredMethod(setterName, memberVar.getType());
				
				
				String getterName = setterName.replace("set", "get");
				//if(field.getType().getCanonicalName().equalsIgnoreCase("boolean")) getterName = setterName.replace("set", "is");
				
				logger.debug("Getter Name ::: " + getterName);
				Method getter = obj.getClass().getDeclaredMethod(getterName);
				
				List orig = (List) getter.invoke(obj);
				
				if(orig != null && (orig.size()>0)){
					logger.debug("List property is not null");
					// if List of types TYPE1
					String listCompType = orig.get(0).getClass().getCanonicalName();
					logger.debug("listCompType :::: " + listCompType);
					
					if(TYPE1.contains(listCompType)){
						// processing the list of any type listed in TYPE1
						logger.debug("Processing the list of any type in TYPE1");
					    	List destList = new ArrayList();
					    	for(Object object : orig){
					    		destList.add(object);
					    	}
					    // calling the setter
					    setter.invoke(obj_copy, destList);
					} else{
						// processing the list of component type being some JavaBean
						logger.debug("Processing list of the some JavaBean type");
					    String packageForListComp = obj_copy.getClass().getPackage().getName();
					    logger.debug("packageForListcomp :: " + packageForListComp);
					    String className = orig.get(0).getClass().getSimpleName();
					    logger.debug("className :: "+ className);
					        
					    List destList = new ArrayList();
					    for(Object object : orig){
					    	
					    	Object replica = Class.forName(packageForListComp+"."+className).newInstance();
					    	// recursive call
					    	copyObject(object, replica);
					    	
					    	destList.add(replica);
					    	
					    }
					    setter.invoke(obj_copy, destList);
					    
					}
										
					
				}else{
					// for processing empty list tag
					logger.debug("List property is null");
					List destList = new ArrayList();
					setter.invoke(obj_copy, destList);
					
				}
				
			}
   ////////////////////////////////// processing Lists Ends ///////////////////////////////////////////
   ////////////////////////////////// processing JavaBean objects Starts///////////////////////////////			
			else{
                Field memberVar = null;
				try {
					memberVar = obj_copy.getClass().getDeclaredField(field.getName());
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					logger.debug("Property not found in target object :: "+ field.getName());
					continue;
				}
				
				logger.debug("Found field :: "+ memberVar.getName());
				
				String temp = memberVar.getName().substring(0, 1);
				StringBuffer buffer = new StringBuffer();
				buffer.append("set");
				buffer.append(temp.toUpperCase());
				buffer.append(memberVar.getName().substring(1));
				
				String setterName = buffer.toString();
				logger.debug("SetterName : "+ setterName);
				
				Method setter = obj_copy.getClass().getDeclaredMethod(setterName, memberVar.getType());
				
				
				String getterName = setterName.replace("set", "get");
				//if(field.getType().getCanonicalName().equalsIgnoreCase("boolean")) getterName = setterName.replace("set", "is");
				
				logger.debug("Getter Name ::: " + getterName);
				Method getter = obj.getClass().getDeclaredMethod(getterName);
				
				Object orig = getter.invoke(obj);
				
				if(orig != null){
					
					logger.debug("Processing JavaBean type");
				    Object replica = memberVar.getType().newInstance();
					logger.debug("replica ::: " + replica.getClass());
			    	// recursive call
			    	copyObject(orig, replica);
			    	
			    	setter.invoke(obj_copy, replica);
				}
				
			}
   ////////////////////////////////// processing JavaBean objects Ends///////////////////////////////
		}
		
	}

}
