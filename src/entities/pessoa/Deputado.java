package entities.pessoa;

import interfaces.EstrategiaOrdenacao;
import interfaces.PessoaInterface;
import util.Validacao;

/**
 * Classe que representa um deputado.
 */
public class Deputado implements PessoaInterface {
	
	/**
	 * Usado para o banco de dados.
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
	
	/**
	 * Pessoa que foi usada como base para a criacao do deputado.
	 */
	private PessoaCivil pessoa;
	
	/**
	 * Construtor de Deputado.
	 * 
	 * @param dataDeInicio A data de inicio do mandato.
	 * @param pessoa Pessoa a virar deputado.
	 */
	public Deputado(String dataDeInicio, PessoaCivil pessoa) {
		Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
		Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");	
		this.dataDeInicio = dataDeInicio;
		this.leisAprovadas = 0;
		this.pessoa = pessoa;
	}
	
	/**
	 * Metodo onde retorna a data de inicio do mandato.
	 * 
	 * @return  retorna a data de inicio do mandato.
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
	 * Metodo onde se retorna a quantidade de leis aprovadas.
	 * 
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
		
	/**
	 * Retorna a representacao em String de um deputado.
	 *
	 */
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
	
	public EstrategiaOrdenacao getEstrategiaOrdenacao() {
		return this.pessoa.getEstrategiaOrdenacao();
	}

	@Override
	public void setEstrategia(EstrategiaOrdenacao estrategiaOrdenacao) {
		this.pessoa.setEstrategia(estrategiaOrdenacao);
		
	}
	
}
