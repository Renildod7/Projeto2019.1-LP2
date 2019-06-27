package util;

import java.io.Serializable;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior 
 *
 * Classe ultilizada como indentificador para os contadores ultilizados
 * gerar o codigo de indentificacao de cada lei.
 */
public class ChaveLeiAno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lei;
	private int ano;

	public ChaveLeiAno(String lei, int ano) {
		this.lei = lei.toUpperCase();
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((lei == null) ? 0 : lei.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChaveLeiAno other = (ChaveLeiAno) obj;
		if (ano != other.ano)
			return false;
		if (lei == null) {
			if (other.lei != null)
				return false;
		} else if (!lei.equals(other.lei))
			return false;
		return true;
	}

}
