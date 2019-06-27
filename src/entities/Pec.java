package entities;

import enums.TipoDeLei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */
public class Pec extends ProjetoDeLei{

	private String artigos;

	/**
	 * Construtor da Pec
	 * 
	 * @param dni numero indentificador do autor da pec
	 * @param ano ano em que a pec foi criada.
	 * @param codigo codigo de indentificacao da pec.
	 * @param ementa descricao ao que se refere o projeto de lei.
	 * @param interesses interesses relacionados a lei, ex: saude, educacao etc..
	 * @param url endereco do documento onde se descreve o teor do projeto de lei.
	 * @param artigos Artigo da constituicao se esta sendo emendado ou complementado.
	 */
	public Pec(String dni, int ano, String codigo, String ementa, String interesses, String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url, TipoDeLei.PEC);
		this.artigos = artigos;
	}

	
	@Override
	public String toString() {
		return String.format("Projeto de Emenda Constitucional - %s - %s - %s - %s - %s", 
				this.codigo, this.dni, this.ementa, this.artigos.replace(",", ", "), this.situacaoAtual);
	}
}
