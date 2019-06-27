package controller;

import java.util.Collection;

import entities.Pec;
import entities.Pl;
import entities.Plp;
import entities.ProjetoDeLei;
import util.ChaveLeiAno;
import util.Dados;
import util.Validacao;

/**
 * Controler dos Projetos de lei.
 * 
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */
public class ProjetosDeLeiController {

	private Dados dados;

	/**
	 * Construtor do Controlador Projetos de lei.
	 * 
	 * @param dados simulacao de um banco de dados.
	 */
	public ProjetosDeLeiController(Dados dados) {
		this.dados = dados;
	}

	/**
	 * Metodo onde se cadastra um projeto de Lei PL.
	 * 
	 * @param dni numero indentificador do autor da pl
	 * @param ano ano em que o proejto de lei é cadastrado
	 * @param ementa descrição do que se refere o projeto
	 * @param interesses os interesses do projeto de lei
	 * @param url E o endereco de  um documento que descreve o teor, a lei e a sua justificativa
	 * @param conclusivo indica se a pl precisa ir ou nao (boolean) ao plenario.
	 * @return O codigo que foi gerado de acordo com a ordem de cadastro.
	 */
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		String codigo = geraCodigo("PL", ano); 	
		if (!this.dados.getProjetosLei().containsKey(codigo)) {
			ProjetoDeLei pl = new Pl(dni, ano, codigo, ementa, interesses, url, conclusivo);
			this.dados.adicionaProjeto(codigo, pl);
		} else {
			throw new NullPointerException("Erro ao cadastrar PL: codigo de projeto ja cadastrado");
		} 	
		return codigo;
	}
	
	/**
	 * Metodo onde se cadastra uma PLP
	 * 
	 * @param dni numero indentificador do autor da Plp
	 * @param ano ano em que o proejto de lei é cadastrado
	 * @param ementa descrição do que se refere o projeto
	 * @param interesses os interesses do projeto de lei
	 * @param url E o endereco de  um documento que descreve o teor, a lei e a sua justificativa
	 * @param artigo Artigos da constituição sendo complementados ou emendados.
	 * @return retorna o codigo que foi gerado de acordo com a ordem de cadastro.
	 */
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url,String artigo) {
		
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		Validacao.validaString(artigo, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		
		String codigo = geraCodigo("PLP", ano); 
		if (!this.dados.getProjetosLei().containsKey(codigo)) {
			ProjetoDeLei plp = new Plp(dni, ano, codigo, ementa, interesses, url, artigo);
			this.dados.adicionaProjeto(codigo, plp);
		} else {
			throw new NullPointerException("Erro ao cadastrar PLP: codigo de projeto ja cadastrado");
		}
		return codigo;
	}
	
	/**
	 * Metodo onde se cadastra uma PEC
	 * 
	 * @param dni  numero indentificador do autor da Pec
	 * @param ano  ano em que o proejto de lei é cadastrado
	 * @param ementa descrição do que se refere o projeto
	 * @param interesses os interesses do projeto de lei
	 * @param url E o endereco de  um documento que descreve o teor, a lei e a sua justificativa
	 * @param artigo Artigos da constituição sendo complementados ou emendados.
	 * @return o codigo que foi gerado de acordo com a ordem de cadastro.
	 */
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url,String artigo) {
		
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		Validacao.validaString(artigo, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		
		String codigo = geraCodigo("PEC", ano); 
		if (!this.dados.getProjetosLei().containsKey(codigo)) {
			ProjetoDeLei pec = new Pec(dni, ano, codigo, ementa, interesses, url, artigo);
			this.dados.adicionaProjeto(codigo, pec);
		} else {
			throw new NullPointerException("Erro ao cadastrar PEC: codigo de projeto ja cadastrado");
		}	
		return codigo;
	}

	/**
	 * Metodo de gracao de codigo para as leis.
	 * 
	 * @param lei lei em que o codigo sera gerada
	 * @param ano ano de cadastro da lei
	 * @return retorna a lei com codigo e ano.
	 */
	private String geraCodigo(String lei, int ano) {
		ChaveLeiAno chave = new ChaveLeiAno(lei, ano);
		if(this.dados.getCont().containsKey(chave)){
			this.dados.atualizaCont(chave);
			return lei + " " + (this.dados.getCont().get(chave)) + "/" + ano;
		} else {
			this.dados.adicionaCont(chave);
			return lei + " " + (this.dados.getCont().get(chave)) + "/" + ano;
		}		
	}

	/**
	 * Metodo onde se exibe um projeto
	 * 
	 * @param codigo codigo do projeto a ser exibido
	 * @return retorna erro quando o projeto de lei nao existe
	 */
	public String exibirProjeto(String codigo) {
		if(this.dados.getProjetosLei().containsKey(codigo)) {
			return this.dados.getProjetosLei().get(codigo).toString();
		}
		return "Erro ao exibir projeto: projeto de lei nao existe";
	}
	
	/** 
	 * Metodo que realiza a verificacao se a lei existe.
	 * 
	 * @param codigo codigo da lei a ser procurada.
	 * @return retorna True se exister e False se nao existir
	 */
	public boolean containsLei(String codigo) {
		return this.dados.getProjetosLei().containsKey(codigo);
	}
	
	/**
	 * Metodo onde se retorna os projetos de lei
	 * 
	 * @return retorna os valores da colecao de projetos de lei.
	 */
	public Collection<ProjetoDeLei> getProjetosLei(){
		return this.dados.getProjetosLei().values();
	}
	
	/**
	 * Metodo onde se retorna uma lei.
	 * 
	 * @param codigo Codigo da lei a ser retornada.
	 * @return retorna Uma lei pelo seu codigo.
	 */
	public ProjetoDeLei getLei(String codigo) {
		return this.dados.getProjetosLei().get(codigo);
	}

	/**
	 * Metodo onde se exibe a tramitacao
	 * 
	 * @param codigo codigo da lei a ter sua tramitacao exibida
	 * @return retorna a tramitacao de determinada lei.
	 */
	public String exibirTramitacao(String codigo) {
		if(this.dados.getProjetosLei().containsKey(codigo)) {
			return this.getLei(codigo).exibirTramitacao();			
		} else throw new NullPointerException("Erro ao exibir tramitacao: projeto inexistente");
	}
	
}


