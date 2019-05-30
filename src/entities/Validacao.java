package entities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Validacao {
	
	public static void validaString(String atributo, String mensagem) {
		if (atributo == null || atributo.trim().equals("")) {
			throw new NullPointerException(mensagem);
		}
	}
	
	public static void validaDni(String s, String msgErr) {
		char[] c = s.toCharArray();
		boolean result = true;		
		
		for (int i = 0; i < 9; i++) {
			if (!Character.isDigit(c[i])) {
				result = false;
				break;
			} 
		}	
		if (c[9] != '-' || !Character.isDigit(c[10])) {
			result = false;
		}
		if(result == false) throw new IllegalArgumentException(msgErr);		
	}
	
	public static void validaData(String data, String msgErr) {
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
