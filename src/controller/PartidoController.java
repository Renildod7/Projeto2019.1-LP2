package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import entities.Partido;
import entities.Validacao;

/**
 * Clase Partido Controller onde se opera sobre a Clase Partido.
 * 
 * @author Wander Medeiros de Brito Junior
 *
 */
public class PartidoController {
	
	private Map<String, Partido> partidos;
	
	public PartidoController() {
		this.partidos = new TreeMap<>();		
	}
	
	/**
	 * Metodo onde se Cadastra um Partido
	 * @param nome E o nome que o partido assumira na sua criacao
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
	 * @return Retorna uma uma String com os Nomes dos Partidos Existentes de forma ordenada
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
