package entities;

import java.util.List;

import enums.TipoDeLei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */

public class Lei {
	
	private String codigo;
	private int qtdInteresses;
	private int aprovacoes;
	private TipoDeLei tipoDeLei;
	private int tramitacao;
	
	/**
	 * Cosntrutor da Lei.
	 * 
	 * @param codigo codigo indentificador unico da lei a ser criada
	 * @param interesses interesses relacionados a lei.
	 * @param aprovacoes numero de aprovacoes que a lei ja teve.
	 * @param tipoDelei tipo de lei.
	 */
	
	public Lei(String codigo, int interesses, int aprovacoes, TipoDeLei tipoDelei, int tramitacao) {
		this.codigo = codigo;
		this.qtdInteresses = interesses;
		this.aprovacoes = aprovacoes;
		this.tipoDeLei = tipoDelei;
		this.tramitacao = tramitacao;
	}
	
	/**
	 * Metodo onde se retorna a quantidade de interesses da lei.
	 * @return retorna em formato de inteiro a quantidade de interesses da lei.
	 */
	public int getQtdInteresses() {
		return this.qtdInteresses;
	}
	
	/**
	 * Metodo onde retorna a quantidade de  aprovacoes de determinada lei.
	 * @return retorna em formato de um inteiro a quantidade de aprovacoes da lei.
	 */
	public int getAprovacoes() {
		return this.aprovacoes;
	}

	/**
	 * Metodo onde se retorna o codigo de uma lei.
	 * @return retorna em formato de String o codigo da lei.
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Metodo onde se retorna o tipo da lei em formato de um ENUM
	 * @return retorna o tipo de lei
	 */
	public TipoDeLei geTipoDeLei() {
		return this.tipoDeLei;	
	}
	
	/**
	 * Metodo onde se retorna uma lista Tramitacao
	 * @return retorna uma lista com as tramitacoes.
	 */
	
	public int getTramitacao(){
		return this.tramitacao;
	}

}
