package controller;

import java.util.HashSet;
import java.util.Set;

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

	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		if (pessoaCntrl.containsPessoa(dni)) {
			if (!pessoaCntrl.ehDeputado(dni)) {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");}}
		if (pessoaCntrl.containsPessoa(dni)) {
			this.projetoCntrl.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}

	}

	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		if (pessoaCntrl.containsPessoa(dni)) {
			if (!pessoaCntrl.ehDeputado(dni)) {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");}}
		if (pessoaCntrl.containsPessoa(dni)) {
			this.projetoCntrl.cadastrarPLP(dni, ano, ementa, interesses, url, artigo);
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}

	}

	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigo) {
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		if (pessoaCntrl.containsPessoa(dni)) {
			if (!pessoaCntrl.ehDeputado(dni)) {
				throw new NullPointerException("Erro ao cadastrar projeto: pessoa nao eh deputado");}}
		if (pessoaCntrl.containsPessoa(dni)) {
			this.projetoCntrl.cadastrarPEC(dni, ano, ementa, interesses, url, artigo);
		} else {
			throw new NullPointerException("Erro ao cadastrar projeto: pessoa inexistente");
		}

	}

	public String exibirProjeto(String codigo) {
		return this.projetoCntrl.exibirProjeto(codigo);

	}

}
