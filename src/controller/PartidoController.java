package controller;

import java.util.Set;

import util.Dados;
import util.Validacao;

/**
 * Clase Partido Controller onde se opera sobre a Classe Partido.
 */
public class PartidoController {
	
	/**
	 * Simula um banco de dados.
	 */
	private Dados dados;
	
	/**
	 * Construtor de partidoController.
	 * 
	 * @param dados simulacao de um banco de dados.
	 */
	public PartidoController(Dados dados) {
		this.dados = dados;
	}
	
	/**
	 * Metodo onde se cadastra um Partido.
	 * 
	 * @param nome E o nome que o partido assumira na sua criacao.
	 */
	public void cadastrarPartido(String nome) {
		Validacao.validaString(nome, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		if (!this.dados.getPartidos().contains(nome)) {
			this.dados.adicionaPartido(nome);
		} else {
			throw new IllegalArgumentException("Erro ao cadastrar partido: partido ja cadastrado");
		}
	}
	
	/**
	 * Metodo onde exibe os a base governista.
	 * 
	 * @return Retorna uma String com os nomes dos Partidos Existentes de forma ordenada.
	 */
	public String exibirBase() {		
		String retorno = "";
		for (String w : this.dados.getPartidos()) {
			retorno += w + ",";
		}
		if (this.dados.getPartidos().size() > 0) {
			return retorno.substring(0, retorno.length() -1);
		}
		return retorno;
	}
	
	/**
	 * Metodo onde se retorna os partidos.
	 * 
	 * @return retorna os partidos.
	 */
	public Set<String> getBase(){
		return this.dados.getPartidos();
	}
	
}
