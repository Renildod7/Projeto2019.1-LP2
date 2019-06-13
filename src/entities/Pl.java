package entities;

public class Pl extends ProjetoDeLei {
	
	private boolean conclusivo;

	public Pl(String dni, int ano, String codigo, String ementa, String interesses, String url, boolean conclusivo) {
		super(dni, ano, codigo, ementa, interesses, url);
		this.conclusivo = conclusivo;
		
	}

	@Override
	public String toString() {
		if (this.conclusivo) {
			return "Projeto de Lei - " + this.codigo + " - " + this.dni + " - " + this.ementa + " - " + "Conclusiva" + " - " +  this.situacaoAtual ;	
		}
		return "Projeto de Lei - " + this.codigo + " - " + this.dni + " - " + this.ementa + " - " +  this.situacaoAtual ;
	}
	
	
	
	
	

}
