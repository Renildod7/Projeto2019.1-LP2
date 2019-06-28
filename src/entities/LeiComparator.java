package entities;

import java.util.Date;

import enums.TipoDeLei;

/**
 * Objeto utilizado para a ordenacao dos projetos de lei utilizado no metodo pegarPropostaRelacionada.
 * Cada objeto do tipo LeiComparator e refente a um objeto do tipo ProjetoDeLei o qual possue o mesmo valor no atributo codigo.
 * Os atributos de um LeiComparator sao gerados a partir dos atributos do seu ProjetoDeLei referente e da pessoa cujo dni e passado como parametro no metodo onde o objeto e utilizado.
 */
public class LeiComparator {
	
	private String codigo;
	private int qtdInteresses;
	private int aprovacoes;
	private TipoDeLei tipoDeLei;
	private int tramitacao;
	private Date dataDeCadastro;
	
	/**
	 * Cosntrutor de LeiComparator.
	 * 
	 * @param codigo Codigo indentificador unico do projeto de lei referente a 
	 * @param interesses Quantidade de interesses em comun com a pessoa cujo dni e passado como parametro no metodo onde o objeto e utilizado.
	 * @param aprovacoes Numero de aprovacoes que o projeto de lei ja teve.
	 * @param tipoDelei Tipo do projeto de lei.
	 * @param tramitacao Numero referente a tramitacao do projeto de lei.
	 * @param dataDeCadastro Momento em que o projeto de lei foi criado.
	 */
	public LeiComparator(String codigo, int interesses, int aprovacoes, TipoDeLei tipoDelei, int tramitacao, Date dataDeCadastro) {
		this.codigo = codigo;
		this.qtdInteresses = interesses;
		this.aprovacoes = aprovacoes;
		this.tipoDeLei = tipoDelei;
		this.tramitacao = tramitacao;
		this.dataDeCadastro = dataDeCadastro;
	}
	
	/**
	 * Metodo onde se retorna a quantidade de interesses que o projeto de lei 
	 * com a pessoa cujo dni foi passado como parametro no metodo onde o objeto e utilizado.
	 * 
	 * @return retorna a quantidade de interesses que o projeto de lei possue em comum com a pessoa..
	 */
	public int getQtdInteresses() {
		return this.qtdInteresses;
	}
	
	/**
	 * Metodo onde retorna a quantidade de aprovacoes de determinado projeto de lei.
	 * @return retorna em formato de um inteiro a quantidade de aprovacoes do projeto de lei.
	 */
	public int getAprovacoes() {
		return this.aprovacoes;
	}

	/**
	 * Metodo onde se retorna o codigo de um projeto de lei.
	 * @return retorna o codigo do ptojeto de lei.
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Metodo onde se retorna o tipo da lei em formato de um ENUM
	 * @return retorna o tipo de lei
	 */
	public TipoDeLei geTipoDeLei() {
		return this.tipoDeLei;	
	}
	
	/**
	 * Metodo onde se retorna uma lista Tramitacao
	 * @return retorna uma lista com as tramitacoes.
	 */
	
	public int getTramitacao(){
		return this.tramitacao;
	}
	
	/**
	 * Retorna o momento em que o projeto de lei foi cadastrado.
	 * 
	 * @return O momento em que o projeto foi cadastrado.
	 */
	public Date getDataDeCadastro() {
		return this.dataDeCadastro;
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
		LeiComparator other = (LeiComparator) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
