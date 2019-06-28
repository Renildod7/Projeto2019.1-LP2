package entities.pessoa;


import comparator.Constitucional;
import interfaces.EstrategiaOrdenacao;
import interfaces.PessoaInterface;
import util.Validacao;

/**
 * Classe que representa uma pessoa.
 */
public class PessoaCivil implements PessoaInterface{
	
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
	
	
	/**
	 * comparator para se usar a estrategia de ordenacao.
	 */
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

	/**
	 * Metodo onde se retorna o nome da Pessoa.
	 * 
	 * @return retorna o nome da pessoa.
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Metodo onde se retorna o dni da pessoa.
	 * 
	 * @return retorna em formato de String a indentificacao da pessoa.
	 */
	public String getDni() {
		return dni;
	}


	/**
	 * Metodo onde se retorna o Estado de uma pessoa.
	 * 
	 * @return retorna em formato de String o Estado da pessoa.
	 */
	public String getEstado() {
		return estado;
	}


	

	/**
	 * metodo onde se retorna os interesses de uma pessoa
	 * 
	 * @return retorna em formato de String os interesses de uma pessoa. 
	 */
	public String getInteresses() {
		return this.interesses;
	}
	
	
	
	/**
	 * Metodo onde se retorna a estrategia de ordenacao
	 * @return E retornado a estrategia de ordenacao.
	 */
	public EstrategiaOrdenacao getEstrategiaOrdenacao() {
		return this.estrategiaOrdenacao;
	}


	/**
	 * Metodo onde e setado a estrategia de ordenacao.
	 *
	 */
	@Override
	public void setEstrategia(EstrategiaOrdenacao estrategiaOrdenacao) {
		this.estrategiaOrdenacao = estrategiaOrdenacao;
		
	}

}
