package entidades;

import outros.Funcao;

public class Pessoa {
	
	
	private String nome;
	private String dni;
	private String estado;
	private String interesses;
	private String partido;
	private Funcao cargo;

	public Pessoa(String nome, String dni, String estado,String interesses, String partido) {
		this.nome = nome;
		this.dni = dni;
		this.estado = estado;
		this.interesses = interesses;
		this.partido = partido;
		
	}
	public Pessoa(String nome, String dni, String estado, String interesses) {
		this(nome, dni, estado, interesses, "");
	}
}
