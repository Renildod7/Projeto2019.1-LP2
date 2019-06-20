package entities;

import java.util.ArrayList;
import java.util.List;

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
	private List<String> tramitacao;
	private int aprovacoes;
	
	
	
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
		this.tramitacao = new ArrayList<>();
		this.tramitacao.add(this.situacaoAtual);
		this.aprovacoes = 0;
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
	
	private void setLocalDeVotacao(String novoLocal) {
		this.localDeVotacao = novoLocal;
		this.situacaoAtual = "EM VOTACAO (" + novoLocal + ")";
		this.tramitacao.add("EM VOTACAO (" + novoLocal + ")");
	}
	
	public String getAutorDaLei() {
		return this.dni;
	}
	
	public StatusDaLei getStatus() {
		return this.statusDaLei;
	}
	
	public void votacaoComissaoAprovado(String novoLocal) {
		this.aprovacoes ++;
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("APROVADO (" + this.localDeVotacao + ")");
		if(novoLocal.equals("plenario")) {
			plenario1oTurno();
		} else setLocalDeVotacao(novoLocal);
	}
	
	public void votacaoComissaoRejeitado(String novoLocal) {
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("REJEITADO (" + this.localDeVotacao + ")");
		if(novoLocal.equals("plenario")) {
			plenario1oTurno();
		} else setLocalDeVotacao(novoLocal);
		if(this.tipoDeLei.equals(TipoDeLei.PL)) this.tramitacao.remove(this.tramitacao.size()-1);
		
	}
	
	public StatusPlenario getStatusPlenario() {
		return this.statusPlenario;
	}
	
	public void votacaoPlenarioAprovado() {
		this.aprovacoes ++;
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("APROVADO (" + this.localDeVotacao + ")");
		if(this.statusPlenario.equals(StatusPlenario.PRIMEIRO_TURNO)) {
			plenario2oTurno();
		}
		if(this.tipoDeLei.equals(TipoDeLei.PL)) this.tramitacao.remove(this.tramitacao.size()-1);
	}
	
	public void votacaoPlenarioRejeitado() {
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("REJEITADO (" + this.localDeVotacao + ")");
		if(this.tipoDeLei.equals(TipoDeLei.PL)) this.tramitacao.remove(this.tramitacao.size()-1);
	}
	
	private void plenario1oTurno() {
		this.statusPlenario = StatusPlenario.PRIMEIRO_TURNO;
		if(this.tipoDeLei.equals(TipoDeLei.PL)) {
			setLocalDeVotacao("Plenario");
		}else setLocalDeVotacao("Plenario - 1o turno");
	}
	
	private void plenario2oTurno() {
		this.statusPlenario = StatusPlenario.SEGUNDO_TURNO;
		setLocalDeVotacao("Plenario - 2o turno");
	}
	
	public void aprovarLei() {
		this.statusDaLei = StatusDaLei.ENCERRADA;
		this.situacaoAtual = "APROVADO";
	}
	
	public void rejeitarLei() {
		this.statusDaLei = StatusDaLei.ENCERRADA;
		this.situacaoAtual = "ARQUIVADO";
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
	
	public String exibirTramitacao() {
		String retorno = "";
		
		for(int i = 0; i < this.tramitacao.size()-1; i++) {
			retorno += this.tramitacao.get(i) + ", ";
		}
		retorno += this.tramitacao.get(this.tramitacao.size()-1) + ".";
		return retorno;
	}



}
