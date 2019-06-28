package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import comparator.Idade;
import comparator.Interesses;
import entities.LeiComparator;
import entities.projetodelei.Pec;
import entities.projetodelei.Pl;
import entities.projetodelei.Plp;
import entities.projetodelei.ProjetoDeLei;
import enums.StatusDaLei;
import interfaces.EstrategiaOrdenacao;
import util.ChaveLeiAno;
import util.Dados;
import util.Validacao;

/**
 * Controler dos Projetos de lei.
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
	 * @param ano ano em que o proejto de lei e cadastrado
	 * @param ementa descricao do que se refere o projeto
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
	 * @param ano ano em que o proejto de lei e cadastrado
	 * @param ementa descricao do que se refere o projeto
	 * @param interesses os interesses do projeto de lei
	 * @param url E o endereco de  um documento que descreve o teor, a lei e a sua justificativa
	 * @param artigo Artigos da constituicao sendo complementados ou emendados.
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
	 * @param ano  ano em que o proejto de lei e cadastrado
	 * @param ementa descricao do que se refere o projeto
	 * @param interesses os interesses do projeto de lei
	 * @param url E o endereco de  um documento que descreve o teor, a lei e a sua justificativa
	 * @param artigo Artigos da constituicao sendo complementados ou emendados.
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

	
	/**
	 * Retorna o codigo da proposta mais relacionada com a pessoa cujo interesses e estrategia de ordenacao sao passados como parametro.
	 * A estrategia de ordenacao da pessoa é usada quando existe empate no maior numero de interesse em comum entre os projetos de lei e a pessoa.
	 * 
	 * @param interessesPessoa Interesses da pessoa.
	 * @param estrategiaOrdenacaoPessoa Estrategia de ordenacao da pessoa utilizada em caso de empate.
	 * 
	 * @return Codigo do projeto de lei mas relacionado a pessoa.
	 */
	public String pegarPropostaRelacionada(List<String> interessesPessoa, EstrategiaOrdenacao estrategiaOrdenacaoPessoa) {
		List<ProjetoDeLei> projetosDeLei = new ArrayList<>(); projetosDeLei.addAll(this.getProjetosLei());
		List<LeiComparator> pojetosComInteressesEmComum = pojetosComInteressesEmComum(interessesPessoa, projetosDeLei);
		
		Collections.sort(pojetosComInteressesEmComum, new Interesses());
		if(pojetosComInteressesEmComum.size()== 0) return "";
		
		List<LeiComparator> projetosMaioresInteressesEmpatados = projetosMaioresInteressesEmpatados(pojetosComInteressesEmComum);
		if(projetosMaioresInteressesEmpatados.size() == 1) return projetosMaioresInteressesEmpatados.get(0).getCodigo();
	
		Collections.sort(projetosMaioresInteressesEmpatados, estrategiaOrdenacaoPessoa);
		
		List<LeiComparator> projetosEmpatadosEstrategiaOrdenacaoPessoa = estrategiaOrdenacaoPessoa.empatados(projetosMaioresInteressesEmpatados);
		if(projetosEmpatadosEstrategiaOrdenacaoPessoa.size() == 1) return projetosEmpatadosEstrategiaOrdenacaoPessoa.get(0).getCodigo();
		
		Collections.sort(projetosEmpatadosEstrategiaOrdenacaoPessoa, new Idade());
		return projetosEmpatadosEstrategiaOrdenacaoPessoa.get(0).getCodigo();
	}
	
	/**
	 * Retorna uma lista de objetos do tipo LeiComparator onde cada LeiComparator e referente a um projeto de lei 
	 * que possue algum interesse em commun com os interesses de uma determinada pessoa.
	 * 
	 * @param interessesPessoa Interesses da pessoa.
	 * @param projetosDeLei Projetos de lei.
	 * 
	 * @return lista de LeiComparator referente aos projetos de lei que possuem algum interesses em comum com os de uma determinada pessoa.
	 */
	private List<LeiComparator> pojetosComInteressesEmComum(List<String> interessesPessoa, List<ProjetoDeLei> projetosDeLei){
		List<LeiComparator> list = new ArrayList<>();
		for(ProjetoDeLei lei : projetosDeLei) {
			if(lei.getStatus().equals(StatusDaLei.ENCERRADA)) continue;
			List<String> interessesLei = Arrays.asList(lei.getInteresses().split(","));
			int contInteresses = 0;
			int contTramitacao = 0;
			for(String interesseL : interessesLei) {
				if(interessesPessoa.contains(interesseL)) contInteresses++;
			}
			for(String s : lei.getTramitacao()) {
				if(s.equals("EM VOTACAO (Plenario - 2o turno)")) {
					contTramitacao += 10000;
				}else if(s.equals("EM VOTACAO (Plenario)")) {
					contTramitacao += 10000;
				}else if(s.equals("EM VOTACAO (Plenario - 1o turno)")) {
					contTramitacao += 500;
				}else contTramitacao++;
			}
			
			if(contInteresses != 0) list.add(new LeiComparator(lei.getCodigo(), contInteresses, lei.getAprovacoes(), lei.getTipoDeLei(), contTramitacao, lei.getDataDeCadastro()));
		}
		return list;
	}
	
	/**
	 * Recebe uma lista de objetos do tipo LeiComparator ordenada apartir do atributo qtdInteresses do maior para o menor, 
	 * e retorna uma lista de LeiComparator com os que estao empatados com o maior valor no atributo qtdInteresses.
	 * 
	 * @param pojetosComInteressesEmComum Lista de LeiComparator.
	 * 
	 * @return Lista de LeiComparator que estao empatados com o maior valor no atributo qtdInteresses.
	 */
	private List<LeiComparator> projetosMaioresInteressesEmpatados(List<LeiComparator> pojetosComInteressesEmComum){
		List<LeiComparator> list = new ArrayList<>();
		
		for(int i = 0; i < pojetosComInteressesEmComum.size(); i++) {
			if(pojetosComInteressesEmComum.get(i).getQtdInteresses() == pojetosComInteressesEmComum.get(0).getQtdInteresses()) {
				list.add(pojetosComInteressesEmComum.get(i));
			}
		}
		return list;
	}
	
}


