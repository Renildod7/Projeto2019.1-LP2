package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import entities.Pec;
import entities.Pl;
import entities.Plp;
import entities.ProjetoDeLei;

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

	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		String codigo = geraCodigo(ano, "PL"); 
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei pl = new Pl(dni, ano, codigo, ementa, interesses, url, conclusivo);
			this.projetosLei.put(codigo, pl);
		} else {
			throw new NullPointerException("n sei o que escrever");
		}
	
	}
	
	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url,String artigo) {
		String codigo = geraCodigo(ano, "PLP"); 
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei plp = new Plp(dni, ano, codigo, ementa, interesses, url, artigo);
			this.projetosLei.put(codigo, plp);
			
		} else {
			throw new NullPointerException("n sei o que escrever");
		}
	
	}
	
	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url,String artigo) {
		String codigo = geraCodigo(ano, "PEC"); 
		if (!this.projetosLei.containsKey(codigo)) {
			ProjetoDeLei pec = new Pec(dni, ano, codigo, ementa, interesses, url, artigo);
			this.projetosLei.put(codigo, pec);
			
		} else {
			throw new NullPointerException("n sei o que escrever");
		}
	
	}
	
	
	private String geraCodigo(int ano, String tipo) {
		if(tipo.equals("PL")) {
			if(this.codigosPl.containsKey(ano)){
				this.codigosPl.put(ano, this.codigosPl.get(ano)+1);
				return "PL " + (this.codigosPl.get(ano)) + "/" + ano;
			} else {
				this.codigosPl.put(ano,0);
				this.codigosPl.put(ano, this.codigosPl.get(ano)+1);
				return "PL " + (this.codigosPl.get(ano)) + "/" + ano;
			}
		}
		
		if(tipo.equals("PLP")) {
			if(this.codigosPlp.containsKey(ano)){
				this.codigosPlp.put(ano, this.codigosPlp.get(ano)+1);
				return "PLP " + (this.codigosPlp.get(ano)+1) + "/" + ano;
			} else {
				this.codigosPlp.put(ano,0);
				this.codigosPlp.put(ano, this.codigosPlp.get(ano)+1);
				return "PLP " + (this.codigosPlp.get(ano)) + "/" + ano;
			}
		}
		
		if(tipo.equals("PEC")) {
			if(this.codigosPec.containsKey(ano)){
				this.codigosPec.put(ano, this.codigosPec.get(ano)+1);
				return "PEC " + (this.codigosPec.get(ano)+1) + "/" + ano;
			} else {
				this.codigosPec.put(ano,0);
				this.codigosPec.put(ano, this.codigosPec.get(ano)+1);
				return "PEC " + (this.codigosPec.get(ano)) + "/" + ano;
			}
		}
		return "";
	}

	public String exibirProjeto(String codigo) {
		if (this.projetosLei.containsKey(codigo)){
			return this.projetosLei.get(codigo).toString();
		}
		return "deu merda";
	}



}


