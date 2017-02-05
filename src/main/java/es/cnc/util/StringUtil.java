package es.cnc.util;

import java.text.Normalizer;

public class StringUtil {
	public static String quitarAcentosTotal(String texto) {
		if (texto != null) {
			
			texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
//			Pattern p = Pattern.compile("[\\p{InCombiningDiacriticalMarks}]");
			// \p{InCombiningDiacriticalMarks} -> Esta expresión representa los caracteres UTF-8
			// comprendidos entre: U+0300 - U+036F
	        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	        texto = texto.replaceAll("ª", ".");
	        
	        return texto;
		}
		return texto;
	}
	
	public static String quitarAcentosEspañol(String texto) {
		if (texto != null) {
//			texto = texto.toUpperCase();
			texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
	        // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
//												   N~       n~       ¡       ¿       °       º       U¨       u¨       ª
//	        texto = texto.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(\u00BA)(U\u0308)(u\u0308)(\u00AA)]", "");
	        
//			                                       N~       n~       ¡       ¿       º       ª
	        texto = texto.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00BA)(\u00AA)]", "");
	        
	        texto = texto.replaceAll("[\u00AA\u00BA]", ".");
	        // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
	        texto = Normalizer.normalize(texto, Normalizer.Form.NFC);
	        return texto;
		}
		return texto;
	}
	
	public static void main(String[] args) {
		//Unicode code point
		//U+00AA
		// UTF-8 (hex)
		//c2 aa
//		Pattern p = Pattern.compile("[\\p{InCombiningDiacriticalMarks}]");

		char aUx = 'ª';
		System.out.println(aUx);
		Integer code = (int) aUx;
		System.out.println(code);
		System.out.println(String.format("%04X", code));
		
		aUx = '\u00B0';
		System.out.println(aUx);
		code = (int) aUx;
		System.out.println(code);
		System.out.println(String.format("%04X", code));
		
		aUx = 'º';
		System.out.println(aUx);
		code = (int) aUx;
		System.out.println(code);
		System.out.println(String.format("%04X", code));
		

		
//		System.out.println(Integer.toHexString(code));
//		System.out.println(String.format("%04X", code));
		
		String aux = "Holª Mª Ññ Mº ? ¿ ¡";
		String aux2;//, aux3;
		System.out.println(aux);
		aux2 = quitarAcentos(aux);
		System.out.println(aux2);
		
//		System.out.println("Decomposed");
//		for (int i=0;i<aux.length();i++) {
//			System.out.println(Integer.toHexString((int)aux.charAt(i)));
//		}
//		aux3 =	Normalizer.normalize(aux, Normalizer.Form.NFD);
//		System.out.println("Composed");
//		for (int i=0;i<aux3.length();i++) {
//			System.out.println(Integer.toHexString((int)aux3.charAt(i)));
//		}
		
	}

	public static String quitarAcentos(String aux) {
		return quitarAcentosEspañol(aux);
	}
}
