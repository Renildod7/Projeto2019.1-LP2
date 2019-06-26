package entities;

import java.util.List;

import enums.TipoDeLei;

public class Lei {
	
	private String codigo;
	private int qtdInteresses;
	private int aprovacoes;
	private TipoDeLei tipoDeLei;
	private List<String> tramitacao;
	
	public Lei(String codigo, int interesses, int aprovacoes, TipoDeLei tipoDelei, List<String> tramitacao) {
		this.codigo = codigo;
		this.qtdInteresses = interesses;
		this.aprovacoes = aprovacoes;
		this.tipoDeLei = tipoDelei;
		this.tramitacao = tramitacao;
	}
	
	public int getQtdInteresses() {
		return this.qtdInteresses;
	}
	
	public int getAprovacoes() {
		return this.aprovacoes;
	}

	public String getCodigo() {
		return this.codigo;
	}
	
	public TipoDeLei geTipoDeLei() {
		return this.tipoDeLei;	
	}
	
	public List<String> getTramitacao(){
		return this.tramitacao;
	}

}
