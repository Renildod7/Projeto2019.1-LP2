package controller;

import java.util.Set;

import entities.Comissao;
import entities.pessoa.Deputado;
import entities.Plenario;
import entities.projetodelei.ProjetoDeLei;
import util.Dados;

/**
 * Classe que representa o controlador de comissoes.
 */
public class CamaraController {

	/**
	 * Simula um banco de dados.
	 */
	private Dados dados;
	
	/**
	 * Plenario onde fica os deputados.
	 */
	private Plenario plenario;
	
	/**
	 * Construtor do camaraController.
	 * 
	 * @param dados simulacao de um banco de dados.
	 */
	public CamaraController(Dados dados) {
		this.dados = dados;
		this.plenario = new Plenario(dados);
	}

	/**
	 * Adiciona um deputado no plenario.
	 * 
	 * @param deputado Deputado que sera adicionado.
	 */
	public void cadastrarDeputado(Deputado deputado) {
		this.dados.adicionaDeputado(deputado);	
	}
	
	/**
	 * Metodo responsavel por cadastrar uma comissao de votacao no sistema.
	 * 
	 * @param tema O tema da comissao.
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

	/**
	 * Metodo onde e feita a votacao de uma comissao.
	 * 
	 * @param statusGovernista Status do projeto projeto de lei em relacao ao governo atual. 
	 * @param proximoLocal proximo local onde sera votada a comisssao.
	 * @param lei a leia a ser votada pela comissao.
	 * @param autorDaLei O autor da lei a ser votada.
	 * @param localDeVotacao O local onde a votacao sera votada.
	 * @param base A base governista.
	 * 
	 * @return Retorna se o projeto de lei foi aprovada ou rejeitada.
	 */
	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, String localDeVotacao, Set<String> base) {
		return this.dados.getComissoes().get(localDeVotacao).votarComissao(statusGovernista, proximoLocal, lei, autorDaLei, base);	
	}	
	
	/**
	 * Metodo onte se tem a votacao no plenario.
	 * 
	 * @param statusGovernista Status do projeto projeto de lei em relacao ao governo atual 
	 * @param lei ProjetoDeLei em votacao
	 * @param autorDaLei autor da projeto de lei em votacao
	 * @param deputadosPresentes Deputados presentes no plenario
	 * @param base A base governista.
	 * 
	 * @return Retorna um boolean com true para aprovado e false para nao aprovado
	 */
	public boolean votarPlenario(String statusGovernista, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputadosPresentes, Set<String> base) {
		return this.plenario.votarPlenario(statusGovernista, lei, autorDaLei, deputadosPresentes, base);
	}
	
}
