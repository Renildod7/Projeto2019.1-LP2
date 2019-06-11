package entities;

import others.CargoPoliticoInteface;
import others.Validacao;

/**
 * Classe que representa uma pessoa.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class Pessoa {
	
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
	
	
	private CargoPoliticoInteface cargoPolitico;

	/**
	 * Construtor de principal de Pessoa.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 * @param partido		O partido da pessoa.
	 */
	public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
		validaEntradas(nome, dni, estado);
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = partido;		
	}
	
	/**
	 * Construtor secundario de Pessoa. Utilizado quando a pessoa em questao nao possui partido.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 */
	public Pessoa(String nome, String dni, String estado, String interesses) {
		this(nome, dni, estado, interesses, "");
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
	
	public void alteraCargoPolitico(String dataDeInicio) {
		this.cargoPolitico = new Deputado(dataDeInicio);
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
		Pessoa other = (Pessoa) obj;
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

		String retorno = "";
		
		if(this.cargoPolitico == null) {
			retorno = representacaoPessoaComum();
			
		}else if(this.cargoPolitico.getClass() == Deputado.class) {
			retorno  = representacaoPessoaDeputado();
		}
	
		return retorno;
	}
	
	private String representacaoPessoaComum() {
		String retorno = "";
		
		retorno += this.nome + " - " + this.dni + " (" + this.estado + ")";
		if(!this.partido.equals("")) retorno += " - " + this.partido;
		if(!this.interesses.equals("")) retorno += " - Interesses: " + this.interesses;
		
		return retorno;
	}
	
	private String representacaoPessoaDeputado() {
		String retorno = "";
		
		retorno += "POL: " + this.nome + " - " + this.dni + " (" + this.estado + ") - " + this.partido;
		if(!this.interesses.equals("")) retorno += " - Interesses: " + this.interesses;
		retorno += " - " + this.cargoPolitico.getDataDeInicio() + " - " + this.cargoPolitico.getLeisAprovadas() + " Leis";
		
		return retorno;
	}
	
	public String getPartido() {
		return this.partido;
	}

}
