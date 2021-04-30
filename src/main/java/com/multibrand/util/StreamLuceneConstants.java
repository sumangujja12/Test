package com.multibrand.util;

/** This class defines the Analyzer Stop Words 
 * 
 * @author RKiran
 */

public class StreamLuceneConstants {
	

	 public static final String[] streetSuffixes = new String[] {
    		
	   		 "ALY", "ANX", "ARC", "AVE", "BYU", "BCH", "BND", "BLF", "BLFS", "BTM", 
	            "BLVD", "BR", "BRG", "BRK", "BRKS", "BG", "BGS", "BYP", "CP", "CYN", 
	            "CPE", "CSWY", "CTR", "CTRS", "CIR", "CIRS", "CLF", "CLFS", "CLB", 
	            "CMN", "COR", "CORS", "CRSE", "CT", "CTS", "CV", "CVS", "CRK", "CRES", 
	            "CRST", "XING", "XRD", "CURV", "DL", "DM", "DV", "DR", "DRS", "EST", 
	            "ESTS", "EXPY", "EXT", "EXTS", "FALL", "FLS", "FRY", "FLD", "FLDS", 
	            "FLT", "FLTS", "FRD", "FRDS", "FRST", "FRG", "FRGS", "FRK", "FRKS", 
	            "FT", "FWY", "GDN", "GDNS", "GTWY", "GLN", "GLNS", "GRN", "GRNS", 
	            "GRV", "GRVS", "HBR", "HBRS", "HVN", "HTS", "HWY", "HL", "HLS", 
	            "HOLW", "INLT", "IS", "ISS", "ISLE", "JCT", "JCTS", "KY", "KYS", 
	            "KNL", "KNLS", "LK", "LKS", "LAND", "LNDG", "LN", "LGT", "LGTS", "LF",
	            "LCK", "LCKS", "LDG", "LOOP", "MALL", "MNR", "MNRS", "MDW", "MDWS", 
	            "MEWS", "ML", "MLS", "MSN", "MTWY", "MT", "MTN", "MTNS", "NCK", 
	            "ORCH", "OVAL", "OPAS", "PARK", "PKWY", "PASS", "PSGE", "PATH", 
	            "PIKE", "PNE", "PNES", "PL", "PLN", "PLNS", "PLZ", "PT", "PTS", "PRT",
	            "PRTS", "PR", "RADL", "RAMP", "RNCH", "RPD", "RPDS", "RST", "RDG", 
	            "RDGS", "RIV", "RD", "RDS", "RTE", "ROW", "RUE", "RUN", "SHL", "SHLS",
	            "SHR", "SHRS", "SKWY", "SPG", "SPGS", "SPUR", "SQ", "SQS", "STA", 
	            "STRA", "STRM", "STRM ", "ST", "STS", "SMT", "TER", "TRWY", "TRCE",
	            "TRAK", "TRFY", "TRL", "TRL ", "TUNL", "TPKE", "UPAS", "UN", "UNS", 
	            "VLY", "VLYS", "VIA", "VW", "VWS", "VLG", "VLGS", "VL", "VIS", "WALK",
	            "WALL", "WAY", "WAYS", "WL", "WLS" 
	   };
		
	
	 public static final String[] supportedStates = new String[] {
			 "TX", "GA", "PA", "MA", "NJ", "NY", "DC"
	 };
	 
	 public static final String[] apartmenPreFixes = new String[] {
			 "APT", "UNIT", "STE"
	 };
	
	/* Constants Related to Lucene Search Query*/
	
	public static String STATE = "State" ;
	public static String CUSTOMERTYPE = "CustomerType" ;
	public static String EXACT = "Exact" ;
	public static String CANONICAL = "Canonical" ;
	public static String EMPTY_STR = "" ;

}
