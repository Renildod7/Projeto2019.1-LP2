package controller;

import java.util.HashMap;
import java.util.Map;

import entities.Pessoa;

public class PessoaController {
	
	private Map<String, Pessoa> pessoas;
	
	public PessoaController() {
		this.pessoas = new HashMap<>();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		if (!this.pessoas.containsKey(dni)) {
			Pessoa p = new Pessoa(nome, dni, estado, interesses, partido);			
		}
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		if (!this.pessoas.containsKey(dni)) {
			Pessoa p = new Pessoa(nome, dni, estado, interesses);			
		}
	}

}
