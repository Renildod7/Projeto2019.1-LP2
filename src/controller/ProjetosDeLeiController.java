package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import entities.Pec;
import entities.Pl;
import entities.Plp;
import entities.ProjetoDeLei;
import others.Validacao;

public class ProjetosDeLeiController {

	private Map<String, ProjetoDeLei> projetosLei;
	private Map<Integer, Integer> codigosPl;
	private Map<Integer, Integer> codigosPlp;
	private Map<Integer, Integer> codigosPec;


	public ProjetosDeLeiController() {
		this.projetosLei = new HashMap<>();
		this.codigosPl = new HashMap<>();
		this.codigosPlp = new HashMap<>();
		this.codigosPec = new HashMap<>();
	}

	public String cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		String codigo = geraCodigo(this.codigosPl, ano, "PL"); 	
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei pl = new Pl(dni, ano, codigo, ementa, interesses, url, conclusivo);
			this.projetosLei.put(codigo, pl);
			return codigo;
		} else {
			throw new NullPointerException("n sei o que escrever");
		} 
	
	}
	
	public String cadastrarPLP(String dni, int ano, String ementa, String interesses, String url,String artigo) {
		
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		Validacao.validaString(artigo, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		
		String codigo = geraCodigo(this.codigosPlp, ano, "PLP"); 
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei plp = new Plp(dni, ano, codigo, ementa, interesses, url, artigo);
			this.projetosLei.put(codigo, plp);
			return codigo;
		} else {
			throw new NullPointerException("n sei o que escrever");
		}
	
	}
	
	public String cadastrarPEC(String dni, int ano, String ementa, String interesses, String url,String artigo) {
		
		Validacao.validaString(dni, "Erro ao cadastrar projeto: autor nao pode ser vazio ou nulo");
		Validacao.validaDni(dni, "Erro ao cadastrar projeto: dni invalido");
		Validacao.validaString(ementa, "Erro ao cadastrar projeto: ementa nao pode ser vazia ou nula");
		Validacao.validaString(interesses, "Erro ao cadastrar projeto: interesse nao pode ser vazio ou nulo");
		Validacao.validaString(url, "Erro ao cadastrar projeto: url nao pode ser vazio ou nulo");
		Validacao.validaString(artigo, "Erro ao cadastrar projeto: artigo nao pode ser vazio ou nulo");
		Validacao.validaAno(ano, "Erro ao cadastrar projeto: " );
		
		String codigo = geraCodigo(this.codigosPec, ano, "PEC"); 
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei pec = new Pec(dni, ano, codigo, ementa, interesses, url, artigo);
			this.projetosLei.put(codigo, pec);
			return codigo;
		} else {
			throw new NullPointerException("n sei o que escrever");
		}
	
	}

	
	private String geraCodigo(Map<Integer, Integer> contador, int ano, String lei) {
		if(contador.containsKey(ano)){
			contador.put(ano, contador.get(ano)+1);
			return lei+" " + (contador.get(ano)) + "/" + ano;
		} else {
			contador.put(ano,0);
			contador.put(ano, contador.get(ano)+1);
			return lei+" " + (contador.get(ano)) + "/" + ano;
		}
		
	}

	public String exibirProjeto(String codigo) {
		if (this.projetosLei.containsKey(codigo)){
			return this.projetosLei.get(codigo).toString();
		}
		return "deu merda";
	}



}


