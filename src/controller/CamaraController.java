package controller;

import java.util.Set;

import entities.Comissao;
import entities.Deputado;

import entities.ProjetoDeLei;
import util.Dados;

/**
 * Classe que representa o controlador de comissoes.
 */
public class CamaraController {

	private Dados dados;
	
	/**
	 * Construtor do controlador. Cria um novo HashMap.
	 * @param dados 
	 */
	public CamaraController(Dados dados) {
		this.dados = dados;
	}

	public void cadastrarDeputado(Deputado deputado) {
		this.dados.adicionaDeputado(deputado);	
	}
	
	/**
	 * Metodo responsavel por cadastrar uma comissao de votacao no sistema.
	 * 
	 * @param tema		O tema da comissao.
	 * @param politicos Os dni's dos politicos que farao parte da comissao.
	 */
	public void cadastrarComissao(String tema, Set<Deputado> politicos) {		
		this.dados.adicionaComissao(tema, new Comissao(politicos));
	}
	
	/**
	 * Metodo que identifica se uma comissao esta cadastrada no sistema.
	 * 
	 * @param tema O tema da comissao.
	 * 
	 * @return true caso a comissao esteja cadastrada e false caso contrario.
	 */
	public boolean containsComissao(String tema) {
		return this.dados.getComissoes().containsKey(tema);
	}  

	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, String localDeVotacao, Set<String> base) {
		return this.dados.getComissoes().get(localDeVotacao).votarComissao(statusGovernista, proximoLocal, lei, autorDaLei, base);	
	}	
	
	public boolean votarPlenario(String statusGovernista, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputadosPresentes, Set<String> base) {
		return this.dados.getPlenario().votarPlenario(statusGovernista, lei, autorDaLei, deputadosPresentes, base);
	}
	
}
