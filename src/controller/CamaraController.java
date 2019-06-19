package controller;

import java.util.Map;
import java.util.Set;

import entities.Comissao;
import entities.Deputado;

import entities.Plenario;
import entities.ProjetoDeLei;

import java.util.HashMap;

/**
 * Classe que representa o controlador de comissoes.
 */
public class CamaraController {
	
	private Plenario plenario;
	
	/**
	 * Map de comissoes cadastradas no sistema.
	 */
	private Map<String, Comissao> comissoes;
	
	/**
	 * Construtor do controlador. Cria um novo HashMap.
	 */
	public CamaraController() {
		this.plenario = new Plenario();
		this.comissoes = new HashMap<>();
	}

	public void cadastrarDeputado(Deputado deputado) {
		this.plenario.cadastrarDeputado(deputado);
		
	}
	
	/**
	 * Metodo responsavel por cadastrar uma comissao de votacao no sistema.
	 * 
	 * @param tema		O tema da comissao.
	 * @param politicos Os dni's dos politicos que farao parte da comissao.
	 */
	public void cadastrarComissao(String tema, Set<Deputado> politicos) {		
		this.comissoes.put(tema, new Comissao(politicos));
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

	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, String localDeVotacao, Set<String> base) {
		return this.comissoes.get(localDeVotacao).votarComissao(statusGovernista, proximoLocal, lei, autorDaLei, base);
		
	}	
	
	
	public boolean votarPlenario(String statusGovernista, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputadosPresentes, Set<String> base) {
		return this.plenario.votarPlenario(statusGovernista, lei, autorDaLei, deputadosPresentes, base);
	}	
}
