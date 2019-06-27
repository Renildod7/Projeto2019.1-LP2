package entities;

import enums.TipoDeLei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */
public class Pl extends ProjetoDeLei {
	
	private boolean conclusivo;

	/**
	 * Cosntrutor de uma pl.
	 * @param dni codigo indentificador unico de quem criou a pl.
	 * @param ano ano em que a pl foi criada.
	 * @param codigo codigo de indentificacao de uma pl.
	 * @param ementa descricao ao que se refere o projeto de lei.
	 * @param interesses interesses relacionados a lei, ex: saude, educacao etc..
	 * @param url endereco do documento onde se descreve o teor do projeto de lei.
	 * @param conclusivo informa se o projeto de lei e conclusivo ou nao.
	 */
	public Pl(String dni, int ano, String codigo, String ementa, String interesses, String url, boolean conclusivo) {
		super(dni, ano, codigo, ementa, interesses, url, TipoDeLei.PL);
		this.conclusivo = conclusivo;
		
	}

	
	@Override
	public String toString() {
		String conclusivo = (this.conclusivo) ? " - Conclusiva" : "";
		
		return String.format("Projeto de Lei - %s - %s - %s%s - %s", 
				this.codigo, this.dni, this.ementa, conclusivo, this.situacaoAtual);
	}
	
	/**
	 * Metodo onde Retorna se a pl e conclusiva ou nao
	 * @return Retorna um boolean com true se for conclusiva  ou false se nao for conclusiva.
	 */
	public boolean getConclusivo() {
		return this.conclusivo;
	}

}
