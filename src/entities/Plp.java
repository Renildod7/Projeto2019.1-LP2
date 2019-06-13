package entities;

public class Plp extends ProjetoDeLei {
	
	private String artigos;

	public Plp(String dni, int ano, String codigo, String ementa, String interesses, String url, String artigos) {
		super(dni, ano, codigo, ementa, interesses, url);
		this.artigos = artigos;
	}

	@Override
	public String toString() {
		return "Projeto de Lei Complementar" + " - " +  this.codigo + " - " + this.dni + " - " + this.ementa + " - " + this.artigos + " - " + this.situacaoAtual;   
	}
	
	

}
