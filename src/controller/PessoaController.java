package controller;

import java.util.HashMap;
import java.util.Map;

import entities.Pessoa;
import entities.Validacao;

public class PessoaController {
	
	private Map<String, Pessoa> pessoas;
	
	public PessoaController() {
		this.pessoas = new HashMap<>();
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		Validacao.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		Validacao.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		if (!this.pessoas.containsKey(dni)) {
			Pessoa p = new Pessoa(nome, dni, estado, interesses, partido);
			this.pessoas.put(dni, p);
		} else {
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		}
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		Validacao.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		Validacao.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		if (!this.pessoas.containsKey(dni)) {
			Pessoa p = new Pessoa(nome, dni, estado, interesses);
			this.pessoas.put(dni, p);
		} else {
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		}
	}

}
