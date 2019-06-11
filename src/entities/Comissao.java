package entities;

import java.util.Set;

/**
 * Classe que representa uma Comissao de votacao.
 */
public class Comissao {
	
	/**
	 * Set com o dni dos deputados que fazem parte da comissao.
	 */
	private Set<String> politicos;
	
	/**
	 * Construtor de uma comissao. Cria uma nova comissao adicionando dni's de deputados.
	 * @param politicos
	 */
	public Comissao(Set<String> politicos) {
		this.politicos = politicos;
	}

}
