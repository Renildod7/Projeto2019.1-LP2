package util;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import entities.Comissao;
import entities.Deputado;
import entities.PessoaInterface;
import entities.Plenario;
import entities.ProjetoDeLei;

public class Dados {
	
	private Map<String, PessoaInterface> pessoas;
	private Set<String> partidos;
	private Map<String, Comissao> comissoes;
	private Map<String, ProjetoDeLei> projetosLei;
	private Map<ChaveLeiAno, Integer> cont;
	private Plenario plenario;
	private File pessoasF;
	
	public Dados() {
		this.pessoas = new HashMap<>();
		this.partidos = new TreeSet<>();
		this.plenario = new Plenario();
		this.comissoes = new HashMap<>();
		this.projetosLei = new HashMap<>();
		this.cont = new HashMap<>();	
		
		this.pessoasF = new File("dados/pessoas.txt");
	}
	
	public void adicionaPessoa(String dni, PessoaInterface pessoa) {
		this.pessoas.put(dni, pessoa);
	}
	
	public Map<String, PessoaInterface> getPessoas() {
		return this.pessoas;
	}	
	
	public void adicionaPartido(String nome) {
		this.partidos.add(nome);
	}
	
	public Set<String> getPartidos() {
		return this.partidos;
	}
	
	public void adicionaComissao(String tema, Comissao comissao) {
		this.comissoes.put(tema, comissao);
	}
	
	public Map<String, Comissao> getComissoes() {
		return this.comissoes;
	}
	
	public void adicionaDeputado(Deputado deputado) {
		this.plenario.cadastrarDeputado(deputado);
	}
	
	public Plenario getPlenario() {
		return this.plenario;
	}
	
	public Map<String,ProjetoDeLei> getProjetosLei() {
		return this.projetosLei;
	}

	public void adicionaProjeto(String codigo, ProjetoDeLei projetoDeLei) {
		this.projetosLei.put(codigo, projetoDeLei);		
	}

	public Map<ChaveLeiAno, Integer> getCont() {
		return this.cont;
	}	
	
	public void atualizaCont(ChaveLeiAno chave) {
		this.cont.put(chave, this.cont.get(chave)+1);
	}
	
	public void adicionaCont(ChaveLeiAno chave) {
		this.cont.put(chave, 1);
	}
	
	public void limparSistema() {
//		this.pessoas = new HashMap<>();
//		this.partidos = new HashSet<>();
//		this.comissoes = new HashMap<>();
//		this.projetosLei = new HashMap<>();
//		this.cont = new HashMap<>();
//		this.plenario = new Plenario();	
//		this.pessoasF = new File("dados/pessoas.txt");
	}
	
	
	public void carregarSistema() throws ClassNotFoundException {
//		carregarPessoas();
	}
	
	public void salvarSistema() {
//		salvarPessoas();		
	}
	
	private void salvarPessoas() {
		try {
			FileOutputStream fos = new FileOutputStream(this.pessoasF);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.pessoas);
			fos.close();
			oos.close();		
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar pessoas");
		}
	}
	
	private Object carregarObjeto(File file) throws ClassNotFoundException {
		Object object = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			fis.close();
			ois.close();
			object = ois.readObject();
		} catch (IOException ioe) {
			System.out.println("Erro na leitura do arquivo");
		}
		return object;		
	}
	
	private void carregarPessoas() throws ClassNotFoundException {
		HashMap<String, PessoaInterface> hashMap = (HashMap<String, PessoaInterface>) (this.carregarObjeto(pessoasF));
		this.pessoas = hashMap;
	}
		
}
