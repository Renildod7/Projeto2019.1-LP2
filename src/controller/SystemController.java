package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comparator.Aprovacao;
import comparator.Conclusao;
import comparator.Constitucional;
import comparator.EstrategiaOrdenacao;
import comparator.Idade;
import comparator.Interesses;
import entities.Deputado;
import entities.Lei;
import entities.ProjetoDeLei;
import enums.StatusDaLei;
import enums.StatusPlenario;
import enums.TipoDeLei;
import util.Dados;
import util.Validacao;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 *
 */
public class SystemController {

	private PessoaController pessoaCntrl;
	private PartidoController partidoCntrl;
	private CamaraController camaraCntrl;
	private ProjetosDeLeiController projetoCntrl;

	/**
	 * Construtor do controlador Principal
	 * @param dados
	 */
	public SystemController(Dados dados) {
		this.pessoaCntrl = new PessoaController(dados);
		this.partidoCntrl = new PartidoController(dados);
		this.camaraCntrl = new CamaraController(dados);
		this.projetoCntrl = new ProjetosDeLeiController(dados);
	}

	/**
	 * Metodo onde se cadastra uma pessoa
	 * 
	 * @param nome nome da pessoa a ser cadastrada
	 * @param dni indentificacao da pessoa a ser cadastrada
	 * @param estado estado de origem da pessoa
	 * @param interesses interesses em tipos de lei que a pessoa tem, ex educacao, saude etc..
	 * @param partido partido em que a pessoa esta afiliado
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pessoaCntrl.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}

	/**
	 * Metodo onde se cadastra uma pessoa sem partido.
	 * 
	 * @param nome nome da pessoa a ser cadastrada
	 * @param dni indentificacao da pessoa a ser cadastrada
	 * @param estado estado de origem da pessoa
	 * @param interesses interesses em tipos de lei que a pessoa tem, ex educacao, saude etc..
	 */
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pessoaCntrl.cadastrarPessoa(nome, dni, estado, interesses);
	}

	/**
	 * Metodo onde se cadastra um partido
	 * 
	 * @param nome nome do partido a ser cadastrado.
	 */
	public void cadastrarPartido(String nome) {
		this.partidoCntrl.cadastrarPartido(nome);
	}

	/**
	 * Metodo onde se exibe a base Governista
	 * 
	 * @return retorna a base governista
	 */
	public String exibirBase() {
		return partidoCntrl.exibirBase();
	}

	/**
	 * Metodo onde se cadastra um Deputado
	 * 
	 * @param dni indentificacao unica de um deputado.
	 * @param dataDeInicio data de inicio do mandato do Deputado.
	 */
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.camaraCntrl.cadastrarDeputado(this.pessoaCntrl.cadastrarDeputado(dni, dataDeInicio));
	}

	/**
	 * Metodo onde se exibe determinada pessoa pelo indentificador unico.
	 * @param dni indentificador unico de um Pessoa.
	 * @return Retorna a representacao de uma pessoa em formato de String.
	 */
	public String exibirPessoa(String dni) {
		return this.pessoaCntrl.exibirPessoa(dni);
	}

	/**
	 * Metodo onde se Cadastra as Comissoes
	 * @param tema tema de interesse da comissao.
	 * @param politicos Sao os politicos que participao de determinada comissao.
	 */
	public void cadastrarComissao(String tema, String politicos) {
		Validacao.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		Validacao.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		if (camaraCntrl.containsComissao(tema)) {
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		}
		
		Set<Deputado> setPoliticos = new HashSet<>();
		for (String dni : politicos.split(",")) {
			Validacao.validaDni(dni, "Erro ao cadastrar comissao: dni invalido");
			if (pessoaCntrl.containsPessoa(dni)) {
				if (pessoaCntrl.ehDeputado(dni)) {
					setPoliticos.add((Deputado) this.pessoaCntrl.getPessoa(dni));
				} else {
					throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
				}
			} else {
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			}
		}
		this.camaraCntrl.cadastrarComissao(tema, setPoliticos);
	}

	/**
	 * Metodo onde se Cadastra um projeto de lei PL.
	 * 
	 * @param dni indentificador unico de quem criou a pl.
	 * @param ano ano de cadastro da pl
	 * @param ementa descricao ao que se refere o projeto de lei.
	 * @param interesses interesses relacionados a lei, ex: saude, educacao etc..
	 * @param url endereco do documento onde se descreve o teor do projeto de lei.
	 * @param conclusivo informa se o projeto de lei e conclusivo ou nao.
	 * @return retorna Retorna uma String com um numero ano e tipo de lei, de acordo com a posicao que foi criado.
	 */
	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		String codigo;
		if (pessoaCntrl.containsPessoa(dni)) {
			if (pessoaCntrl.ehDeputado(dni)) {
				codigo = this.projetoCntrl.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);				
			} else {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
			}
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}	
		return codigo;
	}

	/**
	 * Metodo onde se cadastra uma PLP
	 * 
	 * @param dni indentificador unico de quem criou a pl.
	 * @param ano ano de cadastro da plp
	 * @param ementa descricao ao que se refere o projeto de lei.
	 * @param interesses interesses relacionados a lei, ex: saude, educacao etc..
	 * @param url endereco do documento onde se descreve o teor do projeto de lei.
	 * @param artigo Artigo da constituicao se esta sendo emendado ou complementado.
	 * @return Retorna Retorna uma String com um numero ano e tipo de lei, de acordo com a posicao que foi criado.
	 */
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		String codigo;
		if (pessoaCntrl.containsPessoa(dni)) {
			if (pessoaCntrl.ehDeputado(dni)) {
				codigo =this.projetoCntrl.cadastrarPLP(dni, ano, ementa, interesses, url, artigo);
			} else {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");				
			}
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}		
		return codigo;
	}

	/**
	 * Metodo onde se cadastra uma PEC.
	 * 
	 * @param dni indentificador unico de quem criou a pl.
	 * @param ano ano de cadastro da PEC
	 * @param ementa descricao ao que se refere o projeto de lei.
	 * @param interesses interesses relacionados a lei, ex: saude, educacao etc..
	 * @param url endereco do documento onde se descreve o teor do projeto de lei.
	 * @param artigo Artigo da constituicao se esta sendo emendado ou complementado.
	 * @return Retorna uma String com um numero ano e tipo de lei, de acordo com a posicao que foi criado.
	 */
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		String codigo;
		if (pessoaCntrl.containsPessoa(dni)) {
			if (pessoaCntrl.ehDeputado(dni)) {
				codigo = this.projetoCntrl.cadastrarPEC(dni, ano, ementa, interesses, url, artigo);
			} else {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");
			}
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}
		return codigo;
	}

	/**
	 * Metodo onde se exibe um projeto de lei
	 * 
	 * @param codigo codigo indentificador para busca do projeto de lei
	 * @return Retorna a Representacao de um projeto de lei em formato de String.
	 */
	public String exibirProjeto(String codigo) {
		return this.projetoCntrl.exibirProjeto(codigo);
	}

	/**
	 * Metodo onde se tem a simulacao de uma votacao de uma comissao.
	 * 
	 * @param codigo codigo de indentificacao da comissao a ser votada.
	 * @param statusGovernista Status onde idica se a comissao e da base governista ou nao.
	 * @param proximoLocal proximo local de votacao a ser votado.
	 * @return retorna um boolean se foi aprovada ou rejeitada a votacao.
	 */
	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		validaVotacaoComissao(codigo, statusGovernista, proximoLocal);	
		
		ProjetoDeLei lei = this.projetoCntrl.getLei(codigo);
		Deputado autorDaLei = (Deputado) this.pessoaCntrl.getPessoa(lei.getAutorDaLei());
		String localDeVotacao = lei.getLocalDeVotacao();
		Set<String> base = this.partidoCntrl.getBase();
	
		return this.camaraCntrl.votarComissao(statusGovernista, proximoLocal, lei, autorDaLei, localDeVotacao, base);	
	}
	
	/**
	 * Metodo onde se tem simulacao de uma votacao no plenario.
	 * @param codigo codigo da comissao a ser votada.
	 * @param statusGovernista Status onde idica se a comissao e da base governista ou nao.
	 * @param presentes deputados presentes na votacao.
	 * @return retorna se a votacao foi aprovada ou rejeitada.
	 */
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
		ProjetoDeLei lei = this.projetoCntrl.getLei(codigo);
		Deputado autorDaLei = (Deputado) this.pessoaCntrl.getPessoa(lei.getAutorDaLei());
		Set<Deputado> todosDeputados = this.pessoaCntrl.getDeputados();
		Set<Deputado> deputadosPresentes = new HashSet<>();
		Set<String> base = this.partidoCntrl.getBase();
		
		for (String dni : presentes.split(",")) {
			deputadosPresentes.add((Deputado) this.pessoaCntrl.getPessoa(dni));
		}		
		validaVotacaoPlenario(lei, statusGovernista, deputadosPresentes.size(), todosDeputados.size());
		return this.camaraCntrl.votarPlenario(statusGovernista, lei, autorDaLei, deputadosPresentes, base);
	}

	/**
	 * Metodo onde se tem a validacao de uma votacao da comissao.
	 * @param codigo codigo da comissao a ser avaliada.
	 * @param statusGovernista Status onde idica se a comissao e da base governista ou nao.
	 * @param proximoLocal proximo local de votacao.
	 */
	private void validaVotacaoComissao(String codigo, String statusGovernista, String proximoLocal) {
		if (!this.camaraCntrl.containsComissao("CCJC")) throw new NullPointerException("Erro ao votar proposta: CCJC nao cadastrada");
		Validacao.validaString(proximoLocal, "Erro ao votar proposta: proximo local vazio");
		Validacao.validaStatusGovernista(statusGovernista);
		if (!this.projetoCntrl.containsLei(codigo)) throw new NullPointerException("Erro ao votar proposta: projeto inexistente");
		ProjetoDeLei lei = this.projetoCntrl.getLei(codigo);
		StatusDaLei satatuDaLei = lei.getStatus();
		String localDeVotacao = lei.getLocalDeVotacao();
		Validacao.validaLocalDeVotacao(localDeVotacao);
		Validacao.validaStatusLei(satatuDaLei);	
	}
	
	/**
	 * Metodo onde se tem a validacao de uma votacao no plenario.
	 * 
	 * @param lei projeto de lei a ser validado.
	 * @param statusGovernista Status onde idica se a comissao e da base governista ou nao.
	 * @param presentes deputados presentes na votacao.
	 * @param todosDeputados todos os deputados que estao cadastrados no sistema.
	 */
	private void validaVotacaoPlenario(ProjetoDeLei lei, String statusGovernista, int presentes, int todosDeputados) {
		if (StatusDaLei.ENCERRADA.equals(lei.getStatus())) throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		if (TipoDeLei.PL.equals(lei.getTipoDeLei()) || TipoDeLei.PLP.equals(lei.getTipoDeLei())) {
			if(presentes < (Math.floor(todosDeputados / 2) + 1)) {
				throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
			}
		} else if (TipoDeLei.PEC.equals(lei.getTipoDeLei())) {
			if (presentes < (3 * Math.floor(todosDeputados / 5) + 1)) {
				throw new IllegalArgumentException("Erro ao votar proposta: quorum invalido");
			}
		}
		if (StatusPlenario.NAO_ESTA.equals(lei.getStatusPlenario())) throw new IllegalArgumentException("Erro ao votar proposta: tramitacao em comissao");
	}

	/**
	 * Metodo onde se exibe uma tramitacao 
	 * 
	 * @param codigo codigo da tramitacao a ser exibida
	 * @return Retorna uma representacao em formato de String de uma tramitacao.
	 */
	public String exibirTramitacao(String codigo) {
		return this.projetoCntrl.exibirTramitacao(codigo);
	}
	
	/**
	 * Metodo onde se retorna uma proposta relacionada.
	 * @param dni dni da proposta a ser retornada.
	 * @return E retornado 
	 */
	public String pegarPropostaRelacionada(String dni) {
		EstrategiaOrdenacao estrategiaOrdenacao = this.pessoaCntrl.getPessoa(dni).getEstrategiaOrdenacao();
		
		List<String> interessesPessoa = Arrays.asList(this.pessoaCntrl.getPessoa(dni).getInteresses().split(",")); // Interesses da Pessoa.
		
		List<ProjetoDeLei> projetosDelei = new ArrayList<>();
		projetosDelei.addAll(this.projetoCntrl.getProjetosLei());
		
		List<Lei> leis = new ArrayList<>();
		
		for(ProjetoDeLei lei : projetosDelei) {
			List<String> interessesLei = Arrays.asList(lei.getInteresses().split(","));
			int cont = 0;
			for(String interesseL : interessesLei) {
				if(interessesPessoa.contains(interesseL)) cont++;
			}
			
			leis.add(new Lei(lei.getCodigo(), cont, lei.getAprovacoes(), lei.getTipoDeLei(), lei.getTramitacao()));
		}
		
		Collections.sort(leis, new Interesses());
		Collections.reverse(leis);
		if(leis.get(0).getQtdInteresses() == 0) return "";
		
		
		List<Lei> maioresInteresses = new ArrayList<>();
		
		for(int i = 0; i < leis.size(); i++) {
			if(leis.get(i).getQtdInteresses() == leis.get(0).getQtdInteresses()) maioresInteresses.add(leis.get(i));
		}
		
		if(maioresInteresses.size() == 1) return maioresInteresses.get(0).getCodigo();
	
		Collections.sort(maioresInteresses, this.pessoaCntrl.getPessoa(dni).getEstrategiaOrdenacao());
		Collections.reverse(maioresInteresses);
		
		
		
		List<Lei> a = new ArrayList<>();
		
		
		//if(estrategiaOrdenacao.getClass() == Constitucional.class) {
			for(int i = 0; i < maioresInteresses.size(); i++) {
				if(maioresInteresses.get(i).geTipoDeLei() == maioresInteresses.get(0).geTipoDeLei()) {
					a.add(maioresInteresses.get(i));
					
				}else break;
			}
		//}
		
		Collections.sort(a, new Idade());
		Collections.reverse(a);
		
		return a.get(0).getCodigo();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//return maioresInteresses.get(0).getCodigo();

		
	}

	public void configurarEstrategiaPropostaRelacionada(String dni, String estrategia) {
		EstrategiaOrdenacao estrategiaOrdenacao = new Constitucional();;
		switch (estrategia) {
		case "CONCLUSAO":
			estrategiaOrdenacao = new Conclusao();
			break;
			
		case "APROVACAO":
			estrategiaOrdenacao = new Aprovacao();
			break;
			
		case "CONSTITUCIONAL":
			estrategiaOrdenacao = new Constitucional();
			break;

		default:
			break;
		}
		this.pessoaCntrl.getPessoa(dni).setEstrategia(estrategiaOrdenacao);
	}
	
	
	
	
}
