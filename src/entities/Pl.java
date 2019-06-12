package entities;

public class Pl extends ProjetoDeLei {
	
	private boolean conclusivo;

	public Pl(String dni, int ano, String codigo, String ementa, String interesses, String url, boolean conclusivo) {
		super(dni, ano, codigo, ementa, interesses, url);
		this.conclusivo = conclusivo;
		
	}

	@Override
	public String toString() {
		return "Projeto de Lei Pl [conclusivo=" + conclusivo + ", dniAutor=" + dni + ", ano=" + ano + ", codigo=" + codigo
				+ ", ementa=" + ementa + ", interessesRelacionados=" + interesses + ", situacaoAtual="
				+ situacaoAtual + ", enderecoDocumento=" + url + "]";
	}
	
	
	
	
	

}
