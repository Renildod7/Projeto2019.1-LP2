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
import entities.ProjetoDeLei;

public class Dados {
	
	private Map<String, PessoaInterface> pessoas;
	private Set<String> partidos;
	private Map<String, Comissao> comissoes;
	private Map<String, ProjetoDeLei> projetosLei;
	private Map<ChaveLeiAno, Integer> cont;
	private Set<Deputado> plenario;
	
	private File pessoasFile;
	private File partidosFile;
	private File comissoesFile;
	private File projetosLeiFile;
	private File contFile;
	private File plenarioFile;
	
	private FileInputStream fis; 
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	
	public Dados() {
		this.pessoas = new HashMap<>();
		this.partidos = new TreeSet<>();
		this.comissoes = new HashMap<>();
		this.projetosLei = new HashMap<>();
		this.cont = new HashMap<>();	
		this.plenario = new HashSet<>();

		this.pessoasFile = new File("dados/pessoas.txt");
		this.partidosFile = new File("dados/partidos.txt");
		this.comissoesFile = new File("dados/comissoes.txt");
		this.projetosLeiFile = new File("dados/projetosLei.txt");
		this.contFile = new File("dados/cont.txt");
		this.plenarioFile = new File("dados/plenario.txt");
		
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
		this.plenario.add(deputado);
	}
	
	public Set<Deputado> getPlenario() {
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
		this.pessoas = new HashMap<>();
		this.partidos = new TreeSet<>();
		this.comissoes = new HashMap<>();
		this.projetosLei = new HashMap<>();
		this.cont = new HashMap<>();
		this.plenario = new HashSet<>();
		
		limparArquivo(this.pessoasFile);
		limparArquivo(this.partidosFile);
		limparArquivo(this.comissoesFile);
		limparArquivo(this.projetosLeiFile);
		limparArquivo(this.contFile);
		limparArquivo(this.plenarioFile);
	}
	
	public void salvarSistema() {
		salvarPessoas();
		salvarPartidos();
		salvarComissoes();
		salvarProjetosLei();
		salvarCont();
		salvarPlenario();
	}
		
	public void carregarSistema() throws ClassNotFoundException {
		carregarPessoas();
		carregarPartidos();
		carregarComissoes();
		carregarProjetosLei();
		carregarCont();
		carregarPlenario();
	}
	
	private void salvarPessoas() {
		try {
			this.fos = new FileOutputStream(this.pessoasFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.pessoas);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar pessoas.");
		}
	}
	
	private void salvarPartidos() {
		try {
			this.fos = new FileOutputStream(this.partidosFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.partidos);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar partidos.");
		}
	}
	
	private void salvarComissoes() {
		try {
			this.fos = new FileOutputStream(this.comissoesFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.comissoes);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar comissoes.");
		}
	}
	
	private void salvarProjetosLei() {
		try {
			this.fos = new FileOutputStream(this.projetosLeiFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.projetosLei);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar pessoas.");
		}
	}
	
	private void salvarCont() {
		try {
			this.fos = new FileOutputStream(this.contFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.cont);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar contador.");
		}
	}
	
	private void salvarPlenario() {
		try {
			this.fos = new FileOutputStream(plenarioFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.plenario);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao salvar plenario.");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregarPessoas() throws ClassNotFoundException {
		if (carregarObjeto(this.pessoasFile) == null) {
			this.pessoas = new HashMap<>();
		} else {
			this.pessoas = (HashMap<String, PessoaInterface>) (carregarObjeto(this.pessoasFile));			
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregarPartidos() throws ClassNotFoundException {
		if (carregarObjeto(this.partidosFile) == null) {
			this.partidos = new TreeSet<>();
		} else {
			this.partidos = (TreeSet<String>) (carregarObjeto(this.partidosFile));			
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregarComissoes() throws ClassNotFoundException {
		if (this.carregarObjeto(this.comissoesFile) == null) {
			this.comissoes = new HashMap<>();
		} else {
			this.comissoes = (HashMap<String, Comissao>) (carregarObjeto(this.comissoesFile));			
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregarProjetosLei() throws ClassNotFoundException {
		if (carregarObjeto(this.projetosLeiFile) == null) {
			this.projetosLei = new HashMap<>();
		} else {
			this.projetosLei = (HashMap<String, ProjetoDeLei>) (carregarObjeto(this.projetosLeiFile));			
		}
	}
	
	@SuppressWarnings("unchecked")
	private void carregarCont() throws ClassNotFoundException {
		if (carregarObjeto(this.contFile) == null) {
			this.cont = new HashMap<>();
		} else {
			this.cont = (HashMap<ChaveLeiAno, Integer>) (carregarObjeto(this.contFile));
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void carregarPlenario() throws ClassNotFoundException {
		if (this.carregarObjeto(plenarioFile) == null) {
			this.plenario = new HashSet<>();
		} else {
			this.plenario = (HashSet<Deputado>) this.carregarObjeto(plenarioFile);
		}
	}
	
	private Object carregarObjeto(File file) throws ClassNotFoundException {
		Object object = null;
		if (file.length() == 0) {
			return object;
		}
		try {
			this.fis = new FileInputStream(file);
			this.ois = new ObjectInputStream(fis);
			object = ois.readObject(); 
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			System.out.println("Erro na leitura do arquivo");
		}
		return object;		
	}
	
	private void limparArquivo(File file)  {
		try {
			file.delete();
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Erro ao limpar arquivo.");
		}
	}
			
}
