package org.stasiena.sepa.util;

import java.util.HashMap;
import java.util.Map;

public class BICUtil {
	public static Map<String, Map<String, String>> BIC = new HashMap<String, Map<String, String>>();
	public static Map<String, String> ES_BIC = new HashMap<String, String>();

	static {
		ES_BIC.put("0019", "DEUTESBBASS");
		ES_BIC.put("0031", "ETCHES2GXXX");
		ES_BIC.put("0046", "GALEES2GXXX");
		ES_BIC.put("0049", "BSCHESMMXXX");
		ES_BIC.put("0058", "BNPAESMZXXX");
		ES_BIC.put("0059", "MADRESMMXXX");
		ES_BIC.put("0061", "BMARES2MXXX");
		ES_BIC.put("0065", "BARCESMMXXX");
		ES_BIC.put("0075", "POPUESMMXXX");
		ES_BIC.put("0078", "BAPUES22XXX");
		ES_BIC.put("0081", "BSABESBBXXX");
		ES_BIC.put("0083", "RENBESMMXXX");
		ES_BIC.put("0094", "BVALESMMXXX");
		ES_BIC.put("0108", "SOGEESMMAGM");
		ES_BIC.put("0122", "CITIES2XXXX");
		ES_BIC.put("0128", "BKBKESMMXXX");
		ES_BIC.put("0130", "CGDIESMMXXX");
		ES_BIC.put("0131", "BESMESMMXXX");
		ES_BIC.put("0136", "AREBESMMXXX");
		ES_BIC.put("0138", "BKOAES22XXX");
		ES_BIC.put("0144", "PARBESMHXXX");
		ES_BIC.put("0149", "BNPAESMHXXX");
		ES_BIC.put("0152", "BPLCESMMXXX");
		ES_BIC.put("0154", "BSUIESMMXXX");
		ES_BIC.put("0155", "BRASESMMXXX");
		ES_BIC.put("0156", "ABNAESMMXXX");
		ES_BIC.put("0159", "COBAESMXTMA");
		ES_BIC.put("0160", "BOTKESMXXXX");
		ES_BIC.put("0162", "MIDLESMXXXX");
		ES_BIC.put("0167", "GEBAESMMBIL");
		ES_BIC.put("0168", "BBRUESMXXXX");
		ES_BIC.put("0169", "NACNESMMXXX");
		ES_BIC.put("0182", "BBVAESMMXXX");
		ES_BIC.put("0186", "BFIVESBBXXX");
		ES_BIC.put("0188", "ALCLESMMXXX");
		ES_BIC.put("0190", "BBPIESMMXXX");
		ES_BIC.put("0196", "WELAESMMFUN");
		ES_BIC.put("0198", "BCOEESMMXXX");
		ES_BIC.put("0211", "PROAESMMXXX");
		ES_BIC.put("0216", "POHIESMMXXX");
		ES_BIC.put("0219", "BMCEESMMXXX");
		ES_BIC.put("0226", "UBSWESMMNPB");
		ES_BIC.put("0229", "POPLESMMXXX");
		ES_BIC.put("0231", "DSBLESMMXXX");
		ES_BIC.put("0232", "INVLESMMXXX");
		ES_BIC.put("0233", "POPIESMMXXX");
		ES_BIC.put("0234", "CCOCESMMXXX");
		ES_BIC.put("0236", "LOYIESMMXXX");
		ES_BIC.put("0237", "CSURES2CXXX");
		ES_BIC.put("0487", "GBMNESMMXXX");
		ES_BIC.put("0488", "BFASESMMXXX");
		ES_BIC.put("1000", "ICROESMMXXX");
		ES_BIC.put("1459", "PRABESMMXXX");
		ES_BIC.put("1460", "CRESESMMXXX");
		ES_BIC.put("1465", "INGDESMMXXX");
		ES_BIC.put("1467", "EHYPESMXXXX");
		ES_BIC.put("1474", "CITIESMXSEC");
		ES_BIC.put("1479", "NATXESMMXXX");
		ES_BIC.put("1497", "ESSIESMMXXX");
		ES_BIC.put("1524", "UBIBESMMXXX");
		ES_BIC.put("2000", "CECAESMMXXX");
		ES_BIC.put("2013", "CESCESBBXXX");
		ES_BIC.put("2038", "CAHMESMMXXX");
		ES_BIC.put("2045", "CECAESMM045");
		ES_BIC.put("2048", "CECAESMM048");
		ES_BIC.put("2056", "CECAESMM056");
		ES_BIC.put("2080", "CAGLESMMVIG");
		ES_BIC.put("2085", "CAZRES2ZXXX");
		ES_BIC.put("2086", "CECAESMM086");
		ES_BIC.put("2095", "BASKES2BXXX");
		ES_BIC.put("2100", "CAIXESBBXXX");
		ES_BIC.put("2103", "UCJAES2MXXX");
		ES_BIC.put("2108", "CSPAES2L108");
		ES_BIC.put("3025", "CDENESBBXXX");
		ES_BIC.put("3035", "CLPEES2MXXX");
		ES_BIC.put("3058", "CCRIES2AXXX");
		ES_BIC.put("3081", "BCOEESMM081");
		ES_BIC.put("3183", "CASDESBBXXX");
		ES_BIC.put("3501", "RENTESMMXXX");
		ES_BIC.put("3524", "AHCFESMMXXX");
		ES_BIC.put("3563", "MISVESMMXXX");
		ES_BIC.put("3575", "INSGESMMXXX");
		ES_BIC.put("3604", "CAPIESMMXXX");
		ES_BIC.put("3641", "LISEESMMXXX");
		ES_BIC.put("3656", "CSSOES2SFIN");
		ES_BIC.put("3661", "MLCEESMMXXX");
		ES_BIC.put("3669", "IVALESMMXXX");
		ES_BIC.put("3682", "GVCBESBBETB");
		ES_BIC.put("9000", "ESPBESMMXXX");
		ES_BIC.put("9020", "IPAYESMMXXX");
		ES_BIC.put("9091", "XBCNESBBXXX");
		ES_BIC.put("9092", "XRBVES2BXXX");
		ES_BIC.put("9093", "XRVVESVVXXX");
		ES_BIC.put("9094", "MEFFESBBXXX");
		ES_BIC.put("9096", "IBRCESMMXXX");
		BIC.put("ES", ES_BIC);
	}
	
	/**
	 * 
	 * @param Banco, [Country]
	 * @return
	 */
	public static String getBIC(String...strings) {
		if (strings.length <1 && strings.length >2)
			throw new RuntimeException("Use: (Bank code [,ISO2 Country code])");
		String ISOCountry = strings[0];
		String ISO2 = "ES";
		if (strings.length == 2) 
			ISO2 = strings[1];
		
		return BIC.get(ISO2).get(ISOCountry);
	}
}
