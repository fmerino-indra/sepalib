package org.stasiena.sepa.util;

import static org.stasiena.sepa.util.SEPAUtil.lPad;

public class NIFUtil {
	public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
	
	/**
	 * Valida la letra del NIF.
	 * @param nif -> Se puede introducir un NIF de hasta 9 caracteres (8+1)
	 * @return -> Verdadero si el nif es válido
	 */
	public static boolean isNIFValid(String nif) {
		boolean retorno = false;
		char dc;
		int l = 0;
		String id = null;
		try {
			if (nif != null) {
				l = nif.length();
				if (l <= 9 && l > 1) { // 1num + 1dc
					dc = nif.charAt(l-1);
					id = nif.substring(0,l-1);
					if (id != null) { // 8num + 1dc
						return dc == letraDNI(Integer.valueOf(id));
					}	
				} // longitud incorrecta
			} // es nulo
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}
	
		 
	/**
	 * Devuelve un NIF completo a partir de un DNI. Es decir, añade la letra del NIF
	 * @param dni dni al que se quiere añadir la letra del NIF
	 * @return NIF completo.
	 */
	public static char letraDNI(int id) {
		return NIF_STRING_ASOCIATION.charAt(id % 23);
	}	
	  
	public static String calcNIF(int id) {
		char dc = letraDNI(id);
		String nif = rellenaCeros(String.valueOf(id));
		return nif + dc;
	}
	
	public static String rellenaCeros(String nif) {
		int l=0;
		int tam=8;
		if (nif != null) {
			try {
				l = nif.length();
				if (l>1 && l<9) {
					if (!Character.isDigit(nif.charAt(l-1)))
						tam++;
					nif= lPad(nif, tam, '0');
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		return nif;
	}
	
	public static void main(String[]args) {
		//System.out.println(rellenaCeros("02157301Q"));
		
		System.out.println(isNIFValid("01959277E"));
	}
}
