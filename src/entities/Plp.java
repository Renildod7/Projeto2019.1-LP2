package entities;

import others.TipoDeLei;

public class Plp extends ProjetoDeLei {
	
	private String artigos;

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
