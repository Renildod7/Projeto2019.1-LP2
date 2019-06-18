package entities;

import enums.TipoDeLei;

public class Pec extends ProjetoDeLei{

	private String artigos;

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
