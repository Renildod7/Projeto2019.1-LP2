package entities;

import enums.TipoDeLei;

public class Pl extends ProjetoDeLei {
	
	private boolean conclusivo;

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
	
	public boolean getConclusivo() {
		return this.conclusivo;
	}

}
