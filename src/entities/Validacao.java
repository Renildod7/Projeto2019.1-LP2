package entities;

public class Validacao {
	
	public static void validaString(String atributo, String mensagem) {
		if (atributo == null || atributo.trim().equals("")) {
			throw new NullPointerException(mensagem);
		}
	}
	
	public static boolean ehInteiro(String s) {
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
		return result;		
	}

}
