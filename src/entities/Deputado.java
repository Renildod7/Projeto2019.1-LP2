package entities;

import java.util.Comparator;

import util.Validacao;

/**
 * Classe que representa um deputado.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class Deputado implements PessoaInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7861222841082267736L;

	/**
	 * Data de inicio do mandato do deputado.
	 */
	private String dataDeInicio;
	
	/**
	 * Total de leis aprovadas pelo deputado.
	 */
	private int leisAprovadas;
	
	
	private PessoaCivil pessoa;
	
	/**
	 * Construtor de Deputado.
	 * 
	 * @param dataDeInicio A data de inicio do mandato.
	 */
	public Deputado(String dataDeInicio, PessoaCivil pessoa) {
		Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");	
		this.dataDeInicio = dataDeInicio;
		this.leisAprovadas = 0;
		this.pessoa = pessoa;
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

	
	/**
	 * Metodo one se retorna a quantidade de leis aprovadas.
	 * @return retorna a quantidade de leis aprovadas. 
	 */
	public int getLeisAprovadas() {
		return this.leisAprovadas;
	}
	
	/**
	 * Metodo onde se incrementa o numero de leis aprovadas.
	 * 
	 */
	public void adicionaLeiAprovada() {
		this.leisAprovadas += 1;
	}
	

	
	public String toString() {

		String interesses = (!this.pessoa.getInteresses().isEmpty()) ? " - Interesses: " + this.pessoa.getInteresses() :"";
		
		return String.format("POL: %s - %s (%s) - %s%s - %s - %d Leis", this.pessoa.getNome(), this.pessoa.getDni(), this.pessoa.getEstado(), 
				this.pessoa.getPartido(), interesses, getDataDeInicio(), leisAprovadas);
	}

	
	@Override
	public String getPartido() {
		return this.pessoa.getPartido();
	}

	@Override
	public String getInteresses() {
		return this.pessoa.getInteresses();
	}

	@Override
	public Comparator getEstrategiaOrdenacao() {
		return this.pessoa.getEstrategiaOrdenacao();
	}

	@Override
	public void setEstrategia(Comparator estrategiaOrdenacao) {
		this.pessoa.setEstrategia(estrategiaOrdenacao);
		
	}
	
}
