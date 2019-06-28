package entities.projetodelei;

import enums.TipoDeLei;

/**
 * Classe que representa um Projeto de Lei Complementar.
 */
public class Plp extends ProjetoDeLei {
	
	private static final long serialVersionUID = 8468005664943636600L;
	private String artigos;

	/**
	 * Construtor da PLP
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

	
	/**
	 * Metodo onde se tem a representacao em formato de string de una PLP
	 * 
	 * @return retorna em formato de Strin a representacao de uma PLP
	 */
	@Override
	public String toString() {
		return String.format("Projeto de Lei Complementar - %s - %s - %s - %s - %s", 
				this.codigo, this.dni, this.ementa, this.artigos, this.situacaoAtual);
	}

}
