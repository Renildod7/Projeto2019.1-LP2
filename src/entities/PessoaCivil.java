package entities;

import comparator.Constitucional;
import comparator.EstrategiaOrdenacao;
import util.Validacao;

/**
 * Classe que representa uma pessoa.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class PessoaCivil implements PessoaInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6709870141888454000L;

	/**
	 * Nome da pessoa.
	 */
	private String nome;
	
	/**
	 * DNI(identificador) da pessoa.
	 */
	private String dni;
	
	/**
	 * Estado onde mora.
	 */
	private String estado;
	
	/**
	 * Interesses que possui.
	 */
	private String interesses;
	
	/**
	 * Partido da pessoa.
	 */
	private String partido;
	
	
	private EstrategiaOrdenacao estrategiaOrdenacao;
	

	/**
	 * Construtor de principal de Pessoa.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 * @param partido		O partido da pessoa.
	 */
	public PessoaCivil(String nome, String dni, String estado, String interesses, String partido) {
		validaEntradas(nome, dni, estado);
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = partido;
		this.estrategiaOrdenacao = new Constitucional();
	}
	
	
	/**
	 * Metodo auxiliar utilizado para validar as entradas do construtor de Pessoa.
	 * 
	 * @param nome		Nome da pessoa.
	 * @param dni		Dni da pessoa.
	 * @param estado	Estado onde a pessoa mora.
	 */
	private void validaEntradas(String nome, String dni, String estado) {
		Validacao.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		Validacao.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
	}
	
	
	/**
	 * hashCode de Pessoa. Calculado a partir do DNI da pessoa.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	/**
	 * Equals de pessoa. Duas pessoas sao iguais se tiverem o mesmo DNI.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaCivil other = (PessoaCivil) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	/**
	 * Retorna a representacao em String de Pessoa.
	 */
	@Override
	public String toString() {
		String partido = (!this.partido.isEmpty()) ? " - " + this.partido :"";
		String interesses = (!this.interesses.isEmpty()) ? " - Interesses: " + this.interesses :"";
		
		return String.format("%s - %s (%s)%s%s", nome, dni, estado, partido, interesses);
	}
	
	/**
	 * Metodo que retorna o partido de uma pessoa.
	 * 
	 * @return O partido da pessoa.
	 */
	@Override
	public String getPartido() {
		return this.partido;
	}

	public String getNome() {
		return nome;
	}


	public String getDni() {
		return dni;
	}


	public String getEstado() {
		return estado;
	}


	public String getInteresses() {
		return this.interesses;
	}
	
	public EstrategiaOrdenacao getEstrategiaOrdenacao() {
		return this.estrategiaOrdenacao;
	}


	@Override
	public void setEstrategia(EstrategiaOrdenacao estrategiaOrdenacao) {
		this.estrategiaOrdenacao = estrategiaOrdenacao;
		
	}

}
