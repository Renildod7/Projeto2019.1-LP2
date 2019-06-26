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

public class SystemController {

	private PessoaController pessoaCntrl;
	private PartidoController partidoCntrl;
	private CamaraController camaraCntrl;
	private ProjetosDeLeiController projetoCntrl;

	public SystemController(Dados dados) {
		this.pessoaCntrl = new PessoaController(dados);
		this.partidoCntrl = new PartidoController(dados);
		this.camaraCntrl = new CamaraController(dados);
		this.projetoCntrl = new ProjetosDeLeiController(dados);
	}

	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pessoaCntrl.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}

	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pessoaCntrl.cadastrarPessoa(nome, dni, estado, interesses);
	}

	public void cadastrarPartido(String nome) {
		this.partidoCntrl.cadastrarPartido(nome);
	}

	public String exibirBase() {
		return partidoCntrl.exibirBase();
	}

	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.camaraCntrl.cadastrarDeputado(this.pessoaCntrl.cadastrarDeputado(dni, dataDeInicio));
	}

	public String exibirPessoa(String dni) {
		return this.pessoaCntrl.exibirPessoa(dni);
	}

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

	public String exibirProjeto(String codigo) {
		return this.projetoCntrl.exibirProjeto(codigo);
	}

	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		validaVotacaoComissao(codigo, statusGovernista, proximoLocal);	
		
		ProjetoDeLei lei = this.projetoCntrl.getLei(codigo);
		Deputado autorDaLei = (Deputado) this.pessoaCntrl.getPessoa(lei.getAutorDaLei());
		String localDeVotacao = lei.getLocalDeVotacao();
		Set<String> base = this.partidoCntrl.getBase();
	
		return this.camaraCntrl.votarComissao(statusGovernista, proximoLocal, lei, autorDaLei, localDeVotacao, base);	
	}
	
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

	public String exibirTramitacao(String codigo) {
		return this.projetoCntrl.exibirTramitacao(codigo);
	}
	
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
