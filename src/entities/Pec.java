package entities;

public class Pec extends ProjetoDeLei{

	private String artigos;

	public Pec(String dni, int ano, String codigo, String ementa, String interesses, String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url);
		this.artigos = artigos;
	}

	@Override
	public String toString() {
		return "Projeto de Emenda Constitucional" + " - " + this.codigo + " - " + this.dni + " - " + this.ementa + " - " + this.artigos.replace(",", ", ") + " - " + this.situacaoAtual;
	}
	
	

}
