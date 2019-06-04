package others;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe responsavel pela validacao de atributos. 
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class Validacao {
	
	/**
	 * Verifica se uma String passada eh nula ou vazia. Caso seja nula ou vazia, lanca excecao.
	 * 
	 * @param atributo	O atributo avaliado.
	 * @param mensagem	A mensagem de erro que sera lancada caso o atributo seja invalido.
	 */
	public static void validaString(String atributo, String mensagem) {
		if (atributo == null || atributo.trim().equals("")) {
			throw new NullPointerException(mensagem);
		}
	}
	
	/**
	 * Metodo responsavel por validar o dni de uma pessoa.
	 * 
	 * @param dni	 O dni em questao.
	 * @param msgErr A mensagem de excecao caso o dni passado seja invalido.
	 */
	public static void validaDni(String dni, String msgErr) {
		char[] c = dni.toCharArray();
		boolean result = true;			
		for (int i = 0; i < 9; i++) {
			if (!Character.isDigit(c[i])) {
				result = false;
				break;
			} 
		}	
		if(c[9] != '-' || !Character.isDigit(c[10])) result = false;
		if(result == false) throw new IllegalArgumentException(msgErr);		
	}
	
	/**
	 * Metodo responsavel por validar uma data. 
	 * 
	 * @param data	 A data avaliada.
	 * @param msgErr A mensagem de erro caso a data seja invalida.
	 */
	public static void validaData(String data, String msgErr) {
		if(data.length() != 8 ) throw new IllegalArgumentException(msgErr + "data invalida");
		DateFormat df = new SimpleDateFormat("ddMMyyy");
		Date dateDf;
		df.setLenient(false);
		try {
			dateDf = df.parse(data);
		} catch (ParseException pe) {
			throw new IllegalArgumentException(msgErr + "data invalida");
		}
		
		Calendar dataAtual = GregorianCalendar.getInstance();
		Calendar dataC = GregorianCalendar.getInstance();
		dataC.setTime(dateDf);
		
		if(dataC.after(dataAtual)) throw new IllegalArgumentException(msgErr + "data futura");
	}

}
