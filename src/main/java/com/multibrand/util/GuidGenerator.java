package com.multibrand.util;


import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.Random;

/**
 * For generating transaction GUID for Logging or Email Service 
 * @author ahanda1
 *
 */

public class GuidGenerator {
	private static Random myRand;

	/*
	 * Static block to take care of one time secureRandom seed.
	 * It takes a few seconds to initialize SecureRandom.  You might
	 * want to consider removing this static block or replacing
	 * it with a "time since first loaded" seed to reduce this time.
	 * This block will run only once per JVM instance.
	 */

	static {
		myRand = new Random();
	}

	/*
	 * Default constructor.
	 */
	private GuidGenerator() {
	}

	/*
	 * Method to generate the random GUID
	 */
	public static final String getGuid(boolean dashes) throws Exception {
		String raw = null;

		MessageDigest md5 = null;
		InetAddress id = null;

		long time = 0;
		long rand = 0;

		byte array[] = null;

		md5 = MessageDigest.getInstance("MD5");

		id = InetAddress.getLocalHost();
		time = System.currentTimeMillis();
		rand = 0;

		rand = myRand.nextLong();
		StringBuilder sb = new StringBuilder();
		sb.append( id.toString() );
		sb.append(":");
		sb.append( Long.toString(time) );
		sb.append(":");
		sb.append( Long.toString(rand) );

		md5.update( sb.toString().getBytes() );

		array =  md5.digest();
		sb.setLength(0);

		for (int j = 0; j < array.length; ++j) {
			int b = array[j]&0xFF;

			if ( b < 0x10 ) {
				sb.append('0');
			}

			sb.append( Integer.toHexString(b) );
		}

		raw = sb.toString().toUpperCase();
		if (dashes){
		    sb.setLength(0);
		    sb.append( raw.substring(0,8) );
		    sb.append("-");
		    sb.append( raw.substring(8,12) );
		    sb.append("-");
		    sb.append( raw.substring(12,16) );
		    sb.append("-");
		    sb.append( raw.substring(16,20) );
		    sb.append("-");
		    sb.append( raw.substring(20) );
		}

		return sb.toString().toUpperCase();
	}
}
