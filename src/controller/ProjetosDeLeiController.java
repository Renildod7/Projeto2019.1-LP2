package controller;

import java.util.HashMap;
import java.util.Map;

import entities.Pec;
import entities.Pl;
import entities.Plp;
import entities.ProjetoDeLei;
import others.ChaveLeiAno;
import others.TipoDeLei;
import others.Validacao;

public class ProjetosDeLeiController {

	private Map<String, ProjetoDeLei> projetosLei;
	private Map<ChaveLeiAno, Integer> cont;



	public ProjetosDeLeiController() {
		this.projetosLei = new HashMap<>();
		this.cont = new HashMap<>();
	}

	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		
		String codigo = geraCodigo("PL", ano); 	
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei pl = new Pl(dni, ano, codigo, ementa, interesses, url, conclusivo);
			this.projetosLei.put(codigo, pl);
		} else {
			throw new NullPointerException("n sei o que escrever");
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
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei plp = new Plp(dni, ano, codigo, ementa, interesses, url, artigo);
			this.projetosLei.put(codigo, plp);
		} else {
			throw new NullPointerException("n sei o que escrever");
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
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei pec = new Pec(dni, ano, codigo, ementa, interesses, url, artigo);
			this.projetosLei.put(codigo, pec);
		} else {
			throw new NullPointerException("n sei o que escrever");
		}
	
		return codigo;
	}

	
	private String geraCodigo(String lei, int ano) {
		ChaveLeiAno chave = new ChaveLeiAno(lei, ano);
		if(this.cont.containsKey(chave)){
			this.cont.put(chave, this.cont.get(chave)+1);
			return lei + " " + (this.cont.get(chave)) + "/" + ano;
		} else {
			this.cont.put(chave,0);
			this.cont.put(chave, this.cont.get(chave)+1);
			return lei + " " + (this.cont.get(chave)) + "/" + ano;
		}
		
		
	}

	public String exibirProjeto(String codigo) {
		if(this.projetosLei.containsKey(codigo)) {
			return this.projetosLei.get(codigo).toString();
		}
		return "deu merda";
	}
	
	public ProjetoDeLei getLei(String codigo) {
		return this.projetosLei.get(codigo);
	}
	
}


