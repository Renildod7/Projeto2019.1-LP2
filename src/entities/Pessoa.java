package entities;

import others.Funcao;

public class Pessoa {
	
	private String nome;
	private String dni;
	private String estado;
	private String interesses;
	private String partido;
	private Funcao cargo;

	public Pessoa(String nome, String dni, String estado, String interesses, String partido) {
		validaEntradas(nome, dni, estado);
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = partido;		
	}
	
	public Pessoa(String nome, String dni, String estado, String interesses) {
		this(nome, dni, estado, interesses, "");
	}
	
	private void validaEntradas(String nome, String dni, String estado) {
		Validacao.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		Validacao.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar pessoa: dni invalido");
	}
	
	public void alteraFuncao(String dataDeInicio) {
		Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");
		if(!this.partido.equals("")) {
			this.cargo = new Deputado(dataDeInicio);
		}else throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}


}
