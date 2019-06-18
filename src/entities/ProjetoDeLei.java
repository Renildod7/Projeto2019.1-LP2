package entities;

import enums.StatusDaLei;
import enums.StatusPlenario;
import enums.TipoDeLei;

public abstract class ProjetoDeLei {
	
	protected String dni;
	private int ano;
	protected String codigo;
	protected String ementa;
	private String interesses;
	protected String situacaoAtual;
	private String url;
	private String localDeVotacao;
	private TipoDeLei tipoDeLei;
	private StatusDaLei statusDaLei;
	private StatusPlenario statusPlenario;
	
	
	
	public ProjetoDeLei(String dni, int ano, String codigo, String ementa, String interesses, String url, TipoDeLei tipoDeLei) {
		this.dni = dni;
		this.ano = ano;
		this.codigo = codigo;
		this.ementa = ementa;
		this.interesses = interesses;
		this.url = url;
		this.situacaoAtual = "EM VOTACAO (CCJC)";
		this.localDeVotacao = "CCJC";
		this.tipoDeLei = tipoDeLei;
		this.statusDaLei = StatusDaLei.EM_VOTACAO;
		this.statusPlenario = StatusPlenario.NAO_ESTA;
	}
	
	public String getInteresses() {
		return this.interesses;
	}
	
	public TipoDeLei getTipoDeLei() {
		return this.tipoDeLei;
	}
	
	public String getLocalDeVotacao() {
		return this.localDeVotacao;
	}
	
	public void setLocalDeVotacao(String novoLocal) {
		this.localDeVotacao = novoLocal;
		this.situacaoAtual = "EM VOTACAO (" + novoLocal + ")";
	}
	
	public String getCriadorDaLei() {
		return this.dni;
	}
	
	public StatusDaLei getStatus() {
		return this.statusDaLei;
	}
	
	public void aprovarLei() {
		this.statusDaLei = StatusDaLei.ENCERRADA;
		this.situacaoAtual = "APROVADO";
	}
	
	public void rejeitarLei() {
		this.statusDaLei = StatusDaLei.ENCERRADA;
		this.situacaoAtual = "ARQUIVADO";
	}
	
	public StatusPlenario getStatusPlenario() {
		return this.statusPlenario;
	}
	
	public void plenario1oTurno() {
		this.statusPlenario = StatusPlenario.PRIMEIRO_TURNO;
		setLocalDeVotacao("Plenario - 1o turno");
	}
	
	public void plenario2oTurno() {
		this.statusPlenario = StatusPlenario.SEGUNDO_TURNO;
		setLocalDeVotacao("Plenario - 2o turno");
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
