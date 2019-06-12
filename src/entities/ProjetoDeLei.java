package entities;

public abstract class ProjetoDeLei {
	
	protected String dni;
	protected int ano;
	protected String codigo;
	protected String ementa;
	protected String interesses;
	protected String situacaoAtual;
	protected String url;
	
	
	public ProjetoDeLei(String dni, int ano, String codigo, String ementa, String interesses, String url) {
		this.dni = dni;
		this.ano = ano;
		this.codigo = codigo;
		this.ementa = ementa;
		this.interesses = interesses;
		this.url = url;
		this.situacaoAtual = "EM VOTACAO (CCJC)";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoDeLei other = (ProjetoDeLei) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
	

}
