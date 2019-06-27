package controller;

import java.util.HashSet;
import java.util.Set;

import entities.Deputado;
import entities.PessoaCivil;
import entities.PessoaInterface;
import util.Dados;
import util.Validacao;

/**
 * Classe que representa o Controlador de pessoas.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */
public class PessoaController {
	
	private Dados dados;
	
	/**
	 * Construtor de PessoaController. Cria um novo HashMap.
	 *
	 * @param dados simulacao de um banco de dados.
	 */
	public PessoaController(Dados dados) {
		this.dados = dados;
	}
	
	/**
	 * Metodo responsavel por cadastrar uma pessoa que possui partido.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 * @param partido		O partido da pessoa.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		Validacao.validaString(nome, "Erro ao cadastrar pessoa: nome nao pode ser vazio ou nulo");
		Validacao.validaString(dni, "Erro ao cadastrar pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaString(estado, "Erro ao cadastrar pessoa: estado nao pode ser vazio ou nulo");
		if (!dados.getPessoas().containsKey(dni)) {
			this.dados.adicionaPessoa(dni, new PessoaCivil(nome, dni, estado, interesses, partido));
		} else {
			throw new IllegalArgumentException("Erro ao cadastrar pessoa: dni ja cadastrado");
		}
	}
	
	/**
	 * Metodo responsavel por cadastrar uma pessoa que nao possui partido.
	 * 
	 * @param nome			O nome da pessoa.
	 * @param dni			O dni da pessoa.
	 * @param estado		O estado onde mora.
	 * @param interesses	Os interesses que possui.
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		cadastrarPessoa(nome, dni, estado, interesses, "");
	}

	/**
	 * Metodo responsavel por cadastrar um deputado.
	 * 
	 * @param dni			O dni da pessoa em questao.
	 * @param dataDeInicio	A data de inicio do mandato.
	 * @return d e retornado o deputado em caso de cadastro.
	 */
	public Deputado cadastrarDeputado(String dni, String dataDeInicio) {
		Validacao.validaString(dni, "Erro ao cadastrar deputado: dni nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar deputado: dni invalido");
		
		if(this.dados.getPessoas().containsKey(dni)) {
			PessoaCivil p = (PessoaCivil) this.dados.getPessoas().get(dni);
			if(p.getPartido().isEmpty()) throw new IllegalArgumentException("Erro ao cadastrar deputado: pessoa sem partido");
			Validacao.validaString(dataDeInicio, "Erro ao cadastrar deputado: data nao pode ser vazio ou nulo");
			Validacao.validaData(dataDeInicio, "Erro ao cadastrar deputado: ");
			Deputado d = new Deputado(dataDeInicio, p);
			this.dados.adicionaPessoa(dni, d);
			return d;
		}else throw new NullPointerException("Erro ao cadastrar deputado: pessoa nao encontrada");
	}
	
	/**
	 * Metodo que retorna a representacao em String de uma pessoa cadastrada.
	 * 
	 * @param dni O dni da pessoa em questao.
	 * 
	 * @return Retorna a representacao em String da pessoa.
	 */
	public String exibirPessoa(String dni) {
		Validacao.validaString(dni, "Erro ao exibir pessoa: dni nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao exibir pessoa: dni invalido");
		if(this.dados.getPessoas().containsKey(dni)) {
			return this.dados.getPessoas().get(dni).toString();
		}else throw new NullPointerException("Erro ao exibir pessoa: pessoa nao encontrada");
	}
	
	/**
	 * Metodo que identifica se uma pessoa esta cadastrada non sistema.
	 * 
	 * @param dni O dni da pessoa em questao.
	 * 
	 * @return true caso a pessoa esteja cadastrada e false caso contrario.
	 */
	public boolean containsPessoa(String dni) {
		return this.dados.getPessoas().containsKey(dni);
	}

	/**
	 * Metodo que identifica se o cargo politico de uma pessoa eh deputado.
	 * 
	 * @param dni O dni da pessoa em questao.
	 * 
	 * @return true caso o cargo politico seja deputado e false caso contrario.
	 */
	public boolean ehDeputado(String dni) {
		return (this.dados.getPessoas().get(dni).getClass().equals(Deputado.class));	
	}
	
	/**
	 * Metodo onde retorna o dni da pessoa.
	 * 
	 * @param dni e o dni da pesso
	 * @return retorna o dni da pessoa.
	 */
	public PessoaInterface getPessoa(String dni) {
		return this.dados.getPessoas().get(dni);
	}
	
	/**
	 * Metodo onde retorna um Set de deputados.
	 * 
	 * @return e retornado os deputados
	 */
	public Set<Deputado> getDeputados(){
		Set<Deputado> deputados = new HashSet<>();
		
		for(String dni : this.dados.getPessoas().keySet()){
			if(ehDeputado(dni)) deputados.add((Deputado) this.dados.getPessoas().get(dni));
		}
		return deputados;
	}

}
