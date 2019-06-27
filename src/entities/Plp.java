package entities;

import enums.TipoDeLei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */
public class Plp extends ProjetoDeLei {
	
	private String artigos;

	/**
	 * Construtor da PL
	 * 
	 * @param dni indentificador unico do criador da plp.
	 * @param ano ano em que a pl foi criada.
	 * @param codigo codigo de indentificao de uma plp
	 * @param ementa descricao ao que se refere o projeto de lei.
	 * @param interesses interesses relacionados a lei, ex: saude, educacao etc..
	 * @param url endereco do documento onde se descreve o teor do projeto de lei.
	 * @param artigos Artigo da constituicao se esta sendo emendado ou complementado.
	 */
	public Plp(String dni, int ano, String codigo, String ementa, String interesses, String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url, TipoDeLei.PLP);
		this.artigos = artigos;
	}

	
	@Override
	public String toString() {
		return String.format("Projeto de Lei Complementar - %s - %s - %s - %s - %s", 
				this.codigo, this.dni, this.ementa, this.artigos, this.situacaoAtual);
	}

}
