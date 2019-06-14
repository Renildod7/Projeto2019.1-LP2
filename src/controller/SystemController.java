package controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Pessoa;
import entities.Pl;
import entities.ProjetoDeLei;
import others.StatusDaLei;
import others.Validacao;

public class SystemController {

	private PessoaController pessoaCntrl;
	private PartidoController partidoCntrl;
	private ComissaoController comissaoCntrl;
	private ProjetosDeLeiController projetoCntrl;

	public SystemController() {
		this.pessoaCntrl = new PessoaController();
		this.partidoCntrl = new PartidoController();
		this.comissaoCntrl = new ComissaoController();
		this.projetoCntrl = new ProjetosDeLeiController();
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
		this.pessoaCntrl.cadastrarDeputado(dni, dataDeInicio);
	}

	public String exibirPessoa(String dni) {
		return this.pessoaCntrl.exibirPessoa(dni);
	}

	public void cadastrarComissao(String tema, String politicos) {
		Validacao.validaString(tema, "Erro ao cadastrar comissao: tema nao pode ser vazio ou nulo");
		Validacao.validaString(politicos, "Erro ao cadastrar comissao: lista de politicos nao pode ser vazio ou nulo");
		if (comissaoCntrl.containsComissao(tema)) {
			throw new IllegalArgumentException("Erro ao cadastrar comissao: tema existente");
		}

		Set<String> setPoliticos = new HashSet<>();
		for (String dni : politicos.split(",")) {
			Validacao.validaDni(dni, "Erro ao cadastrar comissao: dni invalido");
			if (pessoaCntrl.containsPessoa(dni)) {
				if (pessoaCntrl.ehDeputado(dni)) {
					setPoliticos.add(dni);
				} else {
					throw new IllegalArgumentException("Erro ao cadastrar comissao: pessoa nao eh deputado");
				}
			} else {
				throw new NullPointerException("Erro ao cadastrar comissao: pessoa inexistente");
			}
		}
		this.comissaoCntrl.cadastrarComissao(tema, setPoliticos);
	}

	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		String codigo;
		if (pessoaCntrl.containsPessoa(dni)) {
			if (!pessoaCntrl.ehDeputado(dni)) {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");}}
		if (pessoaCntrl.containsPessoa(dni)) {
			codigo = this.projetoCntrl.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
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
			if (!pessoaCntrl.ehDeputado(dni)) {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");}}
		if (pessoaCntrl.containsPessoa(dni)) {
			codigo =this.projetoCntrl.cadastrarPLP(dni, ano, ementa, interesses, url, artigo);
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
			if (!pessoaCntrl.ehDeputado(dni)) {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");}}
		if (pessoaCntrl.containsPessoa(dni)) {
			codigo = this.projetoCntrl.cadastrarPEC(dni, ano, ementa, interesses, url, artigo);
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}
		
		return codigo;

	}

	public String exibirProjeto(String codigo) {
		return this.projetoCntrl.exibirProjeto(codigo);

	}

	public boolean votarComissao(String codigo, String statusGovernista, String proximoLocal) {
		if(!this.comissaoCntrl.containsComissao("CCJC")) throw new NullPointerException("Erro ao votar proposta: CCJC nao cadastrada");
		ProjetoDeLei lei = this.projetoCntrl.getLei(codigo);
		Pessoa criadorDaLei = this.pessoaCntrl.getPessoa(lei.getCriadorDaLei());
		if(lei.getProximoLocalDeVotacao().equals("plenario")) throw new IllegalArgumentException("Erro ao votar proposta: proposta encaminhada ao plenario");
		Set<String> comissao = this.comissaoCntrl.getComissao(lei.getProximoLocalDeVotacao());
		
		if(lei.getStatus().equals(StatusDaLei.ENCERRADA)) throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		int votosAFavor = votar(comissao, lei, statusGovernista);
		
		switch(lei.getTipoDeLei()) {
		
		case PL:
			if(((Pl)lei).getConclusivo()) {
				if(votosAFavor >= (Math.floor(comissao.size() / 2) + 1)) {
					lei.setProximoLocalDeVotacao(proximoLocal);
					
					if(proximoLocal.equals("-")) {
						lei.aprovarLei();
						criadorDaLei.adicionaLeiAprovada();
					}
					return true;
				}else {
					lei.regeitarLei();
				}
				return false;
			} else {
				lei.setProximoLocalDeVotacao(proximoLocal);
				if(votosAFavor >= (Math.floor(comissao.size() / 2) + 1)) {
					return true;
				} else return false;
			}
			
		case PLP:
			lei.setProximoLocalDeVotacao(proximoLocal);
			if(proximoLocal.equals("plenario")) lei.plenario1oTurno();
			if(votosAFavor >= (Math.floor(comissao.size() / 2) + 1)) {
				return true;
			} else return false;

		case PEC:
			lei.setProximoLocalDeVotacao(proximoLocal);
			if(proximoLocal.equals("plenario")) lei.plenario1oTurno();
			if(votosAFavor >= (Math.floor(comissao.size() / 2) + 1)) {
				return true;
			} else return false;

		default:
			break;
		
		}
		return true;
	}
	
	private int votar(Set<String> deputados, ProjetoDeLei lei, String statusGovernista) {
		Set<String> base = this.partidoCntrl.getBase();
		int votosAFavor = 0;
		for(String dni : deputados) {
			String partidoDeputado = this.pessoaCntrl.getPessoa(dni).getPartido();
			
			switch (statusGovernista) {
			case "GOVERNISTA":
				if(base.contains(partidoDeputado)) votosAFavor ++;
				break;
			
			case "OPOSICAO":
				if(!base.contains(partidoDeputado)) votosAFavor ++;
				break;
				
			case "LIVRE":
				List<String> leiInteresses  = Arrays.asList(lei.getInteresses().split(","));
				List<String> deputadoInteresses = Arrays.asList(this.pessoaCntrl.getPessoa(dni).getInteresses().split(","));
				
				for(String interesse : deputadoInteresses) {
					if(leiInteresses.contains(interesse)) {
						votosAFavor ++;
						break;
					}
				}
				break;

			default:
				break;
			}
		}
		
		return votosAFavor;
	}

	
	public boolean votarPlenario(String codigo, String statusGovernista, String presentes) {
		ProjetoDeLei lei = this.projetoCntrl.getLei(codigo);
		Pessoa criadorDaLei = this.pessoaCntrl.getPessoa(lei.getCriadorDaLei());
		if(lei.getStatus().equals(StatusDaLei.ENCERRADA)) throw new IllegalArgumentException("Erro ao votar proposta: tramitacao encerrada");
		Set<String> todosDeputados = this.pessoaCntrl.getDeputados();
		Set<String> setPresentes = new HashSet<>();
		setPresentes.addAll(Arrays.asList(presentes.split(",")));
		int votosAFavor = votar(setPresentes, lei, statusGovernista);
		
		switch (lei.getTipoDeLei()) {
		case PL:
			if(votosAFavor >= (Math.floor(setPresentes.size() / 2) + 1)) {
				lei.aprovarLei();
				criadorDaLei.adicionaLeiAprovada();
				return true;
			}else {
				lei.regeitarLei();
				return false;
			}
			
		case PLP:
			switch (lei.getStatusPlenario()) {
			case PRIMEIRO_TURNO:
				if(votosAFavor >= (Math.floor(todosDeputados.size() / 2) + 1)) {
					lei.plenario2oTurno();
					return true;
				}else return false;
				
			case SEGUNDO_TURNO:
				if(votosAFavor >= (Math.floor(todosDeputados.size() / 2) + 1)) {
					lei.aprovarLei();
					criadorDaLei.adicionaLeiAprovada();
					return true;
				}else {
					lei.regeitarLei();
					return false;
				}

			default:
				break;
			}
			
		case PEC:
			switch (lei.getStatusPlenario()) {
			case PRIMEIRO_TURNO:
				if(votosAFavor >= (Math.floor(3 * setPresentes.size() / 5) + 1)) {
					lei.plenario2oTurno();
					return true;
				}else return false;
				
			case SEGUNDO_TURNO:
				if(votosAFavor >= (Math.floor(3 * setPresentes.size() / 5) + 1)) {
					lei.aprovarLei();
					criadorDaLei.adicionaLeiAprovada();
					return true;
				}else {
					lei.regeitarLei();
					return false;
				}
			default:
				break;
			}

		default:
			break;
		}
		
		return false;
	}
}
