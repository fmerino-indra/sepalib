package org.stasiena.sepa.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SEPAUtil {
	public static char[] DCListLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static int[] DCListValues = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
			30, 31, 32, 33, 34, 35 };
	public static Map<Character, Integer> intercambio = new HashMap<Character, Integer>();

	static {
		int i = 0;
		for (char aux : DCListLetters) {
			intercambio.put(aux, DCListValues[i++]);
		}
	}

	/**
	 * Devuelve el identificador con las letras cambiadas por su valor.
	 * 
	 * @param id
	 *            Identificador a cambiar
	 * @return Devuelve el identificador modificado.
	 */
	public static String cambiarLetrasPorNumeros(String id) {
		String retorno = "";
		int next;
		char[] aux = null;
		if (id != null) {
			aux = id.toCharArray();
			for (char c : aux) {
				if (!Character.isDigit(c)) {
					next = intercambio.get(c);
				} else
					next = Integer.valueOf("" + c);
				retorno += "" + next;
			}
		}
		return retorno;
	}

	public static String calcularDC(String isoPais, String id) {
		String retorno = "";
		String aux = null;
		// long auxN;
		BigInteger bi = null;

		long dcN;
		String dc = null;
		aux = id + isoPais + "00";
		aux = cambiarLetrasPorNumeros(aux);
		// auxN = Long.valueOf(aux);
		bi = new BigInteger(aux);
		// dcN = 98-(auxN % 97);
		dcN = 98 - (bi.mod(new BigInteger("97")).longValue());
		dc = String.format("%02d", dcN);
		retorno = dc;
		return retorno;
	}

	public static String rPad(String str, int length, char car) {
		return str + String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car));
	}

	/**
	 * Añade el caracter <code>car</code> por la izquierda hasta alcanzar una
	 * longitud <code>length</code>
	 * 
	 * @param str
	 * @param length
	 * @param car
	 * @return
	 */
	public static String lPad(String str, int length, char car) {
		if (length - str.length() > 0)
			return String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car)) + str;
		else
			return str;
	}
}
