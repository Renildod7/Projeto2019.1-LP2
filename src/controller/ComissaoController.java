package controller;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import entities.Comissao;

public class ComissaoController {
	
	private Map<String, Comissao> comissoes;
	
	public ComissaoController() {
		this.comissoes = new HashMap<>();
	}

	public void cadastrarComissao(String tema, Set<String> politicos) {		
		if (!this.comissoes.containsKey(tema)) {
			Comissao c = new Comissao(politicos);
			this.comissoes.put(tema, c);
		}
	}

}
