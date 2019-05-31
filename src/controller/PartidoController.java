package controller;

import java.util.Map;
import java.util.TreeMap;
import entities.Partido;
import entities.Validacao;

/**
 * Clase Partido Controller onde se opera sobre a Classe Partido.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class PartidoController {
	
	/**
	 * Mapa responsavel por armazenar partidos. As chaves do mapa sao nomes de partidos.
	 */
	private Map<String, Partido> partidos;
	
	/**
	 * Construtor de PartidoController. Cria um novo TreeMap.
	 */
	public PartidoController() {
		this.partidos = new TreeMap<>();		
	}
	
	/**
	 * Metodo onde se Cadastra um Partido.
	 * 
	 * @param nome E o nome que o partido assumira na sua criacao.
	 */
	public void cadastrarPartido(String nome) {
		Validacao.validaString(nome, "Erro ao cadastrar partido: partido nao pode ser vazio ou nulo");
		if (!this.partidos.containsKey(nome)) {
			Partido p = new Partido(nome);
			this.partidos.put(nome, p);
		} else {
			throw new IllegalArgumentException("Erro ao cadastrar partido: partido ja cadastrado");
		}
	}
	
	/**
	 * Metodo Onde Exibe os Partidos.
	 * 
	 * @return Retorna uma String com os nomes dos Partidos Existentes de forma ordenada.
	 */
	public String exibirBase() {		
		String retorno = "";
		for (Partido w : partidos.values()) {
			retorno += w.getNome() + ",";
		}
		if (partidos.size() > 0) {
			return retorno.substring(0, retorno.length() -1);
		}
		return retorno;
	}
	
}
