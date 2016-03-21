package org.stasiena.sepa.util;

import static org.stasiena.sepa.util.SEPAUtil.calcularDC;

public class IBANUtil {
	public static String calcular(String isoPais, String banco, String sucursal, String DC, String cuenta) {
		return calcular(isoPais, banco+sucursal+DC+cuenta);
	}
	public static String calcular(String isoPais, String CCC) {
		String retorno = null;
		String dc=null;

		dc = calcularDC(isoPais, CCC);
		retorno = isoPais+dc+CCC; //(2+2+20)
		
		return retorno;
	}
	
	public static void main(String []args) {
		System.out.println("IBAN:" + calcular("ES", "20381015916000398831"));
	}

	// CCC:			 2038 1015 91 6000398831
	// IBAN: 	ES52 2038 1015 91 6000398831
	
	// CCC:			 0012 0345 03 0000067890
	// IBAN:	ES07 0012 0345 03 0000067890
	
	// CCC:			 0030 1122 61 0004228271
	// IBAN:	ES24 0030 1122 61 0004228271
	
// Otro ejemplo: 00301122610004228271
	
}
