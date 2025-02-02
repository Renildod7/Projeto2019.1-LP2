package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import enums.StatusDaLei;

/**
 * Classe responsavel pela validacao de atributos. 
 */
public class Validacao {
	
	/**
	 * Verifica se uma String passada eh nula ou vazia. Caso seja nula ou vazia, lanca excecao.
	 * 
	 * @param atributo	O atributo avaliado.
	 * @param mensagem	A mensagem de erro que sera lancada caso o atributo seja invalido.
	 */
	public static void validaString(String atributo, String mensagem) {
		if (atributo == null) {
			throw new NullPointerException(mensagem);
		}
		if (atributo.trim().isEmpty()) {
			throw new IllegalArgumentException(mensagem);
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
	
	/**
	 * Metodo responsavel por validar uma ano. 
	 * 
	 * @param ano o ano a ser avaliado.
	 * @param msgErr A mensagem de erro caso o ano seja invalido.
	 */
	public static void validaAno(int ano, String msgErr) {
		if(ano < 1988 ) {
			throw new IllegalArgumentException(msgErr + "ano anterior a 1988");
		}
		if (ano > 2019) {
			throw new IllegalArgumentException(msgErr + "ano posterior ao ano atual");
		}
			
	}
	
	/**
	 * Metodo responsavel por validar um Status de lei
	 * @param status Status a ser validado
	 */
	public static void validaStatusLei(StatusDaLei status) {
		if(StatusDaLei.ENCERRADA.equals(status)) {
			throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		}
	}
	
	/**
	 * Metodo responsavel por validar um Status de um Governista.
	 * 
	 * @param status Status do governista a ser avaliado.
	 */
	public static void validaStatusGovernista(String status) {
		if(status.equals("GOVERNISTA") || status.equals("OPOSICAO") || status.equals("LIVRE")) {
		} else throw new IllegalArgumentException("Erro ao votar proposta: status invalido");
		
	}
	
	/**
	 * Metodo responsavel por validar um local de votacao.
	 * 
	 * @param local de votacao a ser validado.
	 */
	public static void validaLocalDeVotacao(String local) {
		if(local.equals("Plenario - 1o turno") || local.equals("Plenario - 2o turno") || local.equals("Plenario")) {
			throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
		}
	}	
	
	/**
	 * Verifica se uma estrategia de ordenacao e valida.
	 * 
	 * @param estrategia Esrategia que sera verificada.
	 */
	public static void validaEstrategiaOrdenacao(String estrategia) {
		if(estrategia.isEmpty()) throw new IllegalArgumentException("Erro ao configurar estrategia: estrategia vazia");
		if(!("CONCLUSAO".equals(estrategia) || "APROVACAO".equals(estrategia) || "CONSTITUCIONAL".equals(estrategia))) {
			throw new IllegalArgumentException("Erro ao configurar estrategia: estrategia invalida");
		}
	}

}
