package org.stasiena.sepa.util;

import static org.stasiena.sepa.util.SEPAUtil.calcularDC;

public class AT02Util {

	/**
	 * Calcula el identificador AT02
	 * @param isoPais
	 * @param sufijo
	 * @param id
	 * @return
	 */
	public static String calcular(String isoPais, String sufijo, String id) {
		String retorno = "";
		String dc=null;

		dc = calcularDC(isoPais, id);
		retorno = isoPais+dc+sufijo+id; //(2+2+3+9)
		return retorno;
	}
	
	
	public static void main(String[] args) {
		// Sample: 			  0262256826142800
		// Long.MAX_VALUE: 9223372036854775807

		System.out.println(calcular("ES","ZZZ","R7800543F"));
		System.out.println(calcular("ES","000","02622568Q"));
	}
}
