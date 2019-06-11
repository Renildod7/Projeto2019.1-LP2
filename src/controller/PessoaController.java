package controller;

import java.util.HashMap;
import java.util.Map;

import entities.Deputado;
import entities.Pessoa;
import others.Validacao;

/**
 * Classe que representa o Controlador de pessoas.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class PessoaController {
	
	/**
	 * Mapa responsavel por armazenar as pessoas cadastradas. As chaves do mapa sao dni's de pessoas.
	 */
	private Map<String, Pessoa> pessoas;
	
	/**
	 * Construtor de PessoaController. Cria um novo HashMap.
	 */
	public PessoaController() {
		this.pessoas = new HashMap<>();
	}
	
	/**
	 * Metodo responsavel por cadastrar uma pessoa que possui partido.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 * @param partido		O partido da pessoa.
	 */
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
	
	/**
	 * Metodo responsavel por cadastrar uma pessoa que nao possui partido.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 */
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

	/**
	 * Metodo responsavel por cadastrar um deputado.
	 * 
	 * @param dni			O dni da pessoa em questao.
	 * @param dataDeInicio	A data de inicio do mandato.
	 */
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		Validacao.validaString(dni, "Erro ao cadastrar deputado: dni nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");
		
		if(this.pessoas.containsKey(dni)) {
			if(this.pessoas.get(dni).getPartido().equals("")) throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
			Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
			Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");
			this.pessoas.get(dni).alteraCargoPolitico(dataDeInicio);
		}else throw new NullPointerException("Erro ao cadastrar deputado: pessoa nao encontrada");
	}
	
	/**
	 * Metodo que retorna a representacao em String de uma pessoa cadastrada.
	 * 
	 * @param dni O dni da pessoa em questao.
	 * 
	 * @return Retorna a representacao em String da pessoa.
	 */
	public String exibirPessoa(String dni) {
		Validacao.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		if(this.pessoas.containsKey(dni)) {
			return this.pessoas.get(dni).toString();
		}else throw new NullPointerException("Erro ao exibir pessoa: pessoa nao encontrada");
	}

	public boolean containsPessoa(String dni) {
		return this.pessoas.containsKey(dni);
	}

}
