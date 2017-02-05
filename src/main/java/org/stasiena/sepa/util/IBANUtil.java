package org.stasiena.sepa.util;

import org.stasiena.sepa.util.SEPAUtil;

public class IBANUtil {
	public static String calcular(String isoPais, String banco, String sucursal, String DC, String cuenta) {
		return calcular(isoPais, banco+sucursal+DC+cuenta);
	}
	public static String calcular(String isoPais, String CCC) {
		String retorno = null;
		String dc=null;

		dc = SEPAUtil.calcularDC(isoPais, CCC);
		retorno = isoPais+dc+CCC; //(2+2+20)
		
		return retorno;
	}
	
	public static boolean validarIBAN(String iban) {
		boolean valido = false;
		String prefijo = null, banco = null, sucursal = null, dc = null, cuenta = null;
		String aux;
		valido = firstValidation(iban);
		if (valido) {
			prefijo = obtainPrefijo(iban);
			banco = obtainBanco(iban);
			sucursal = obtainSucursal(iban);
			dc = obtainDC(iban);
			cuenta = obtainCCC(iban);
			aux = calcular (prefijo.substring(0, 2), banco, sucursal, dc, cuenta);
			valido = valido && (aux.equals(iban));
		}
		return valido;
	}
	
	public static boolean firstValidation(String iban) {
		boolean valido = false;
		
		valido = (iban != null) && iban.length() == 24;
		return valido;
	}

	public static String obtainPrefijo(String iban) {
		String banco = null;
		if (firstValidation(iban)) {
			banco = iban.substring(0, 4);
		}
		return banco;
	}
	
	public static String obtainBanco(String iban) {
		String banco = null;
		if (firstValidation(iban)) {
			banco = iban.substring(4, 8);
		}
		return banco;
	}
	
	public static String obtainSucursal(String iban) {
		String sucursal = null;
		if (firstValidation(iban)) {
			sucursal = iban.substring(8, 12);
		}
		return sucursal;
	}
	
	public static String obtainDC(String iban) {
		String dc = null;
		if (firstValidation(iban)) {
			dc = iban.substring(12, 14);
		}
		return dc;
	}
	public static String obtainCCC(String iban) {
		String ccc = null;
		if (firstValidation(iban)) {
			ccc = iban.substring(14, 24);
		}
		return ccc;
	}
	
	
	public static void main(String []args) {
		String iban = null;
		iban = calcular("ES", "00750293170607314022");
		System.out.println("IBAN:" + iban);
		if (validarIBAN(iban))
			System.out.println("OK: " + iban);
		else
			System.out.println("Mal: " + iban);
		System.out.println(obtainBanco(iban));
		System.out.println(obtainSucursal(iban));
		System.out.println(obtainDC(iban));
		System.out.println(obtainCCC(iban));
	}

	// CCC:			 2038 1015 91 6000398831
	// IBAN: 	ES52 2038 1015 91 6000398831
	// IBAN: 	ES52 2038 1015 9160 0039 8831
	
	// CCC:			 0012 0345 03 0000067890
	// IBAN:	ES07 0012 0345 03 0000067890
	
	// CCC:			 0030 1122 61 0004228271
	// IBAN:	ES24 0030 1122 61 0004228271
	
// Otro ejemplo: 00301122610004228271
	
}
