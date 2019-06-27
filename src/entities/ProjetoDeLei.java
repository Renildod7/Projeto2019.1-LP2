package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.StatusDaLei;
import enums.StatusPlenario;
import enums.TipoDeLei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */
public abstract class ProjetoDeLei implements Serializable {
	
	/**
	 * Usado para gravar os dados simulando um banco de dados.
	 */
	private static final long serialVersionUID = 1L;
	
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
	private Date dataDeCadastro;
	
	
	
	/**
	 * Construtor de um Projeto de Lei
	 * 
	 * @param dni codigo indentificador unico de que criou o projeto de lei.
	 * @param ano ano em que o projeto de lei foi criado.
	 * @param codigo codigo do projeto de lei.
	 * @param ementa 
	 * @param interesses
	 * @param url
	 * @param tipoDeLei
	 */
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
		this.dataDeCadastro = new Date();
		
	}
	
	/**
	 * Metodo onde se pega o interesse do projeto de lei.
	 * 
	 * @return retorna em formato de String o interesse do projeto de lei.
	 */
	public String getInteresses() {
		return this.interesses;
	}
	
	/**
	 * Metodo onde se pega o tipo da lei.
	 * @return Retorna o tipo da lei.
	 */
	public TipoDeLei getTipoDeLei() {
		return this.tipoDeLei;
	}
	
	/**
	 * Metodo onde se pega o local de votacao.
	 * 
	 * @return retorna o local de votacao em formato de String
	 */
	public String getLocalDeVotacao() {
		return this.localDeVotacao;
	}
	
	/**
	 * Metodo onde se seta o local de voltacao.
	 * @param novoLocal novo local de votacao a ser setado.
	 */
	private void setLocalDeVotacao(String novoLocal) {
		this.localDeVotacao = novoLocal;
		this.situacaoAtual = "EM VOTACAO (" + novoLocal + ")";
		this.tramitacao.add("EM VOTACAO (" + novoLocal + ")");
	}
	
	/**
	 * Metodo onde Retorna que foi o autor da lei.
	 * 
	 * @return Retorna em formato de String o autor da lei.
	 */
	public String getAutorDaLei() {
		return this.dni;
	}
	
	/**
	 * Metodo onde Retorna o Status da Lei.
	 * 
	 * @return Retorna o Status da Lei.
	 */
	public StatusDaLei getStatus() {
		return this.statusDaLei;
	}
	
	/**
	 * Metodo que Retorna o codigo da Lei.
	 * @return E retornado o codigo da Lei
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Metodo onde se retorna a quantidade de aprovacoes da Lei.
	 * 
	 * @return Retorna um numero inteiro com a quantidade de vezes que a lei foi aprovada.
	 */
	public int getAprovacoes() {
		return this.aprovacoes;
	}
	
	/**
	 * Metodo onde se retorna a tramitacao.
	 * 
	 * @return Retorna uma lista de String com a tramitacao.
	 */
	public List<String> getTramitacao(){
		return this.tramitacao;
	}
	
	/**
	 * Metodo onde adiciona novo local de votacao para uma comissao aprovada
	 * 
	 * @param novoLocal Novo local de votacao.
	 */
	public void votacaoComissaoAprovado(String novoLocal) {
		this.aprovacoes ++;
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("APROVADO (" + this.localDeVotacao + ")");
		if(novoLocal.equals("plenario")) {
			plenario1oTurno();
		} else setLocalDeVotacao(novoLocal);
		
		if(novoLocal.equals("-")) this.tramitacao.remove(this.tramitacao.size()-1);
	}
	
	/**
	 * Metodo onde adiciona novo local de votacao para uma comissao rejeitada.
	 * 
	 * @param novoLocal novo local de votacao 
	 */
	public void votacaoComissaoRejeitado(String novoLocal) {
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("REJEITADO (" + this.localDeVotacao + ")");
		if(novoLocal.equals("plenario")) {
			plenario1oTurno();
		} else setLocalDeVotacao(novoLocal);
		this.tramitacao.remove(this.tramitacao.size()-1);
		
	}
	
	/**
	 * Metodo onde retorna o Status do plenario.
	 * 
	 * @return Retorna o Status do plenario.
	 */
	public StatusPlenario getStatusPlenario() {
		return this.statusPlenario;
	}
	
	/**
	 * Metodo onde indica que a votacao foi aprovada no plenario e a passa para o segundo turno
	 * 
	 */
	public void votacaoPlenarioAprovado() {
		this.aprovacoes ++;
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("APROVADO (" + this.localDeVotacao + ")");
		if(this.statusPlenario.equals(StatusPlenario.PRIMEIRO_TURNO)) {
			plenario2oTurno();
		}

	}
	
	/**
	 * Metodo onde indica que a votacao foi rejeitada e a manda ser arquivada.
	 * 
	 */
	public void votacaoPlenarioRejeitado() {
		this.tramitacao.remove(this.tramitacao.size()-1);
		this.tramitacao.add("REJEITADO (" + this.localDeVotacao + ")");
	}
	
	/**
	 * Metodo onde seta o local de votacao para o plenario no 1 turno
	 */
	private void plenario1oTurno() {
		this.statusPlenario = StatusPlenario.PRIMEIRO_TURNO;
		if(this.tipoDeLei.equals(TipoDeLei.PL)) {
			setLocalDeVotacao("Plenario");
		}else setLocalDeVotacao("Plenario - 1o turno");
	}
	
	/**
	 * Metodo onde seta o local de votacao para o plenario no 2 turno.
	 * 
	 */
	private void plenario2oTurno() {
		this.statusPlenario = StatusPlenario.SEGUNDO_TURNO;
		setLocalDeVotacao("Plenario - 2o turno");
	}
	
	/**
	 * Metodo onde altera a situacao da lei para aprovada
	 * 
	 */
	public void aprovarLei() {
		this.statusDaLei = StatusDaLei.ENCERRADA;
		this.situacaoAtual = "APROVADO";
	}
	
	/**
	 * metodo onde altera a situacao de uma lei para arquivada.
	 */
	public void rejeitarLei() {
		this.statusDaLei = StatusDaLei.ENCERRADA;
		this.situacaoAtual = "ARQUIVADO";
	}
	

	/**
	 * Hascode para comparacao adequadra com o equal.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	
	
	/**
	 * Metodo para comparacao de objetos ProjetoDeLei.
	 *
	 */
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
	
	/**
	 * Metodo onde se tem a representacao de uma tramitacao.
	 * 
	 * @return retorna em formato de String uma tramitacao
	 */
	public String exibirTramitacao() {
		String retorno = "";
		
		for(int i = 0; i < this.tramitacao.size()-1; i++) {
			retorno += this.tramitacao.get(i) + ", ";
		}
		retorno += this.tramitacao.get(this.tramitacao.size()-1);
		return retorno;
	}



}
