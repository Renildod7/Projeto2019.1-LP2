package entities;

import others.Validacao;

/**
 * Classe que representa um deputado.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class Deputado extends Pessoa {
	
	/**
	 * Data de inicio do mandato do deputado.
	 */
	private String dataDeInicio;
	
	/**
	 * Total de leis aprovadas pelo deputado.
	 */
	private int leisAprovadas;
	
	/**
	 * Construtor de Deputado.
	 * 
	 * @param dataDeInicio A data de inicio do mandato.
	 */
	public Deputado(String nome, String dni, String estado, String interesses, String partido, String dataDeInicio) {
		super(nome, dni, estado, interesses, partido);
		Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");	
		this.dataDeInicio = dataDeInicio;
		this.leisAprovadas = 0;
	}
	
	/**
	 * Metodo que retorna a data de inicio do mandato do deputado.
	 */
	public String getDataDeInicio() {
		String retorno = "";
		int cont = 0;
		for(String letra : this.dataDeInicio.split("")) {
			cont ++;
			if(cont == 3 || cont == 5) {
				retorno += "/" + letra;
			}else retorno += letra;
		}
		return retorno;
	}
	
	@Override
	public String toString() {
		String retorno = "";
		
		retorno += "POL: " + this.getNome() + " - " + this.getDni() + " (" + this.getEstado() + ") - " + this.getPartido();
		if(!this.getInteresses().equals("")) retorno += " - Interesses: " + this.getInteresses();
		retorno += " - " + this.getDataDeInicio() + " - " + this.leisAprovadas + " Leis";
		
		return retorno;
	}
	
}
