package controller;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

/**
 * Classe que representa o controlador de comissoes.
 */
public class ComissaoController {
	
	/**
	 * Map de comissoes cadastradas no sistema.
	 */
	private Map<String, Set<String>> comissoes;
	
	/**
	 * Construtor do controlador. Cria um novo HashMap.
	 */
	public ComissaoController() {
		this.comissoes = new HashMap<>();
	}

	/**
	 * Metodo responsavel por cadastrar uma comissao de votacao no sistema.
	 * 
	 * @param tema		O tema da comissao.
	 * @param politicos Os dni's dos politicos que farao parte da comissao.
	 */
	public void cadastrarComissao(String tema, Set<String> politicos) {		
		this.comissoes.put(tema, politicos);
	}
	
	/**
	 * Metodo que identifica se uma comissao esta cadastrada no sistema.
	 * 
	 * @param tema O tema da comissao.
	 * 
	 * @return true caso a comissao esteja cadastrada e false caso contrario.
	 */
	public boolean containsComissao(String tema) {
		return this.comissoes.containsKey(tema);
	}  
	
	public Set<String> getComissao(String tema){
		return this.comissoes.get(tema);
	}

}
