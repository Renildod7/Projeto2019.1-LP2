package entities;

import others.Funcao;

/**
 * 
 * @author Renildo Dantas Melo
 *
 */
public class Deputado implements Funcao {
	
	private String dataDeInicio;
	private int leisAprovadas;
	
	public Deputado(String dataDeInicio) {
		Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");
		
		this.dataDeInicio = dataDeInicio;
		this.leisAprovadas = 0;
	}
	
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
	
	public int getLeisAprovadas() {
		return this.leisAprovadas;
	}
}
