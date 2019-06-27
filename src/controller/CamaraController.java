package controller;

import java.util.Set;

import entities.Comissao;
import entities.Deputado;
import entities.Plenario;
import entities.ProjetoDeLei;
import util.Dados;

/**
 * Classe que representa o controlador de comissoes.
 */
public class CamaraController {

	private Dados dados;
	private Plenario plenario;
	
	/**
	 * Construtor do controlador. Cria um novo HashMap.
	 * @param dados simulacao de um banco de dados.
	 */
	public CamaraController(Dados dados) {
		this.dados = dados;
		this.plenario = new Plenario(dados);
	}

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
	 * Metodo onde é feita a votacao de uma comissao.
	 * 
	 * @param statusGovernista Se o é do governo, opisicao ou livre. 
	 * @param proximoLocal proximo local onde sera votada a comisssao.
	 * @param lei a leia a ser votada pela comissao.
	 * @param autorDaLei O autor da lei a ser votada.
	 * @param localDeVotacao O local onde a votacao sera votada.
	 * @param base A base governista.
	 * @return Retorna se o projeto de lei foi aprovada ou rejeitada.
	 */
	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, String localDeVotacao, Set<String> base) {
		return this.dados.getComissoes().get(localDeVotacao).votarComissao(statusGovernista, proximoLocal, lei, autorDaLei, base);	
	}	
	
	/**
	 * Metodo onte se tem a votacao no plenario.
	 * 
	 * @param statusGovernista Se o é do governo, opisicao ou livre 
	 * @param lei lei em votacao
	 * @param autorDaLei autor da lei em votacao
	 * @param deputadosPresentes Deputados presentes no plenario
	 * @param base A base governista.
	 * @return Retorna um boolean com true para aprovado e false para nao aprovado
	 */
	public boolean votarPlenario(String statusGovernista, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputadosPresentes, Set<String> base) {
		return this.plenario.votarPlenario(statusGovernista, lei, autorDaLei, deputadosPresentes, base);
	}
	
}
