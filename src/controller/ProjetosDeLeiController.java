package controller;

import entities.Pec;
import entities.Pl;
import entities.Plp;
import entities.ProjetoDeLei;
import util.ChaveLeiAno;
import util.Dados;
import util.Validacao;

public class ProjetosDeLeiController {

	private Dados dados;

	public ProjetosDeLeiController(Dados dados) {
		this.dados = dados;
	}

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

	public String exibirProjeto(String codigo) {
		if(this.dados.getProjetosLei().containsKey(codigo)) {
			return this.dados.getProjetosLei().get(codigo).toString();
		}
		return "Erro ao exibir projeto: projeto de lei nao existe";
	}
	
	public boolean containsLei(String codigo) {
		return this.dados.getProjetosLei().containsKey(codigo);
	}
	
	public ProjetoDeLei getLei(String codigo) {
		return this.dados.getProjetosLei().get(codigo);
	}

	public String exibirTramitacao(String codigo) {
		return this.getLei(codigo).exibirTramitacao();
	}
	
}


