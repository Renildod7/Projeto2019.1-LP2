package util;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import entities.Comissao;
import entities.pessoa.Deputado;
import entities.projetodelei.ProjetoDeLei;
import interfaces.PessoaInterface;

/**
 * Classe responsavel por gerenciar os dados do sistema.
 */
public class Dados {
	
	/**
	 * Mapa de pessoas, onde a chave eh o DNI.
	 */
	private Map<String, PessoaInterface> pessoas;
	
	/**
	 * Set de partidos.
	 */
	private Set<String> partidos;
	
	/**
	 * Mapa de comissoes, onde a chave eh o tema da comissao.
	 */
	private Map<String, Comissao> comissoes;
	
	/**
	 * Mapa de projetos de lei, onde a chave eh o ano da lei.
	 */
	private Map<String, ProjetoDeLei> projetosLei;
	
	/**
	 * Mapa com os contadores de projetos de lei. A chave eh um objeto do tipo ChaveLeiAno que agrupa uma String com o tipo da lei e ano.
	 */
	private Map<ChaveLeiAno, Integer> cont;
	
	/**
	 * Set de deputados.
	 */
	private Set<Deputado> plenario;	
	
	/**
	 * File utilizado para o armazenamento de pessoas cadastradas.
	 */
	private File pessoasFile;
	
	/**
	 * File utilizado para o armazenamento de partidos cadastradas.
	 */
	private File partidosFile;
	
	/**
	 * File utilizado para o armazenamento de comissoes cadastradas.
	 */
	private File comissoesFile;
	
	/**
	 * File utilizado no armazenamento de projetos de lei cadastradas.
	 */
	private File projetosLeiFile;
	
	/**
	 * File utilizado no armazenamento do contador de projetos cadastradas.
	 */
	private File contFile;
	
	/**
	 * File utilizado no armazenamento do plenario.
	 */
	private File plenarioFile;
	
	private FileInputStream fis; 
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	
	/**
	 * Construtor da classe de dados. Inicializa os atributos de guardam dados na memoria e no disco. Os dados gravados em disco sao guardados na pasta dados, presente na pasta raiz deste projeto.
	 */
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
	
	/**
	 * Adiciona uma Pessoa no Map de Pessoas.
	 * 
	 * @param dni indentificador unico de uma Pessoa.
	 * @param pessoa A Pessoa em questao.
	 */
	public void adicionaPessoa(String dni, PessoaInterface pessoa) {
		this.pessoas.put(dni, pessoa);
	}
	
	/**
	 * Retorna o Map de pessoas.
	 * 
	 * @return o Map de Pessoas.
	 */
	public Map<String, PessoaInterface> getPessoas() {
		return this.pessoas;
	}	
	
	/**
	 * Adiciona um partido ao Set de partidos.	
	 * 
	 * @param nome O nome do partido.
	 */
	public void adicionaPartido(String nome) {
		this.partidos.add(nome);
	}
	
	/**
	 * Retorna o Set de partidos.
	 * 
	 * @return O Set de partidos.
	 */
	public Set<String> getPartidos() {
		return this.partidos;
	}
	
	/**
	 * Adiciona uma Comissao ao mapa de comissoes.
	 * 
	 * @param tema		O tema da comissao.
	 * @param comissao	A comissao em questao.
	 */
	public void adicionaComissao(String tema, Comissao comissao) {
		this.comissoes.put(tema, comissao);
	}
	
	/**
	 * Retorna o Map de comissoes.
	 * 
	 * @return O Map de comissoes.
	 */
	public Map<String, Comissao> getComissoes() {
		return this.comissoes;
	}
	
	/**
	 * Adiciona um Deputado no Plenario.
	 * 
	 * @param deputado O deputado em questao.
	 */
	public void adicionaDeputado(Deputado deputado) {
		this.plenario.add(deputado);
	}
	
	/**
	 * Retorna um Set de deputados cadastrados.
	 * 
	 * @return O plenario.
	 */
	public Set<Deputado> getPlenario() {
		return this.plenario;
	}
	

	/**
	 * Adiciona um projeto de lei no Map projetosLei.
	 * 
	 * @param codigo		O codigo do projeto.
	 * @param projetoDeLei	O projeto em questao.
	 */
	public void adicionaProjeto(String codigo, ProjetoDeLei projetoDeLei) {
		this.projetosLei.put(codigo, projetoDeLei);		
	}
	
	/**
	 * Retorna o Map com os projetos de lei do sistema.
	 * 
	 * @return Os projetos de lei cadastrados.
	 */
	public Map<String,ProjetoDeLei> getProjetosLei() {
		return this.projetosLei;
	}

	/**
	 * Retorna o contador de projetos de lei.
	 * 
	 * @return O contador.
	 */
	public Map<ChaveLeiAno, Integer> getCont() {
		return this.cont;
	}	
	
	/**
	 * Atualizar os valores do contador de projeto de lei.
	 * 
	 * @param chave A chave em questao.
	 */
	public void atualizaCont(ChaveLeiAno chave) {
		this.cont.put(chave, this.cont.get(chave)+1);
	}
	
	/**
	 * Adiciona um novo contador ao mapa de contadores.
	 * 
	 * @param chave A chave em questao.
	 */
	public void adicionaCont(ChaveLeiAno chave) {
		this.cont.put(chave, 1);
	}
	
	/**
	 * Metodo responsavel por fazer a limpeza dos dados armazenados na memoria do sistema e em arquivos.
	 * @throws FileNotFoundException 
	 */
	public void limparSistema() throws FileNotFoundException {
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
	
	/**
	 * Metodo responsavel por salvar em arquivos os dados do sistema. 
	 */
	public void salvarSistema() throws IOException {
		salvarPessoas();
		salvarPartidos();
		salvarComissoes();
		salvarProjetosLei();
		salvarCont();
		salvarPlenario();
	}
		
	/**
	 * Metodo responsavel por carregar os dados do sistema a partir de arquivos.
	 * 
	 * @throws ClassNotFoundException
	 */
	public void carregarSistema() throws ClassNotFoundException {
		carregarPessoas();
		carregarPartidos();
		carregarComissoes();
		carregarProjetosLei();
		carregarCont();
		carregarPlenario();
	}
	
	/**
	 * Tenta salvar o Map de pessoas em arquivo. 
	 */
	private void salvarPessoas() throws IOException {
		try {
			this.fos = new FileOutputStream(this.pessoasFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.pessoas);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			throw new IOException("Erro ao salvar pessoas em arquivo."); 
		}
	}
	

	/**
	 * Tenta salvar o Set de partidos em arquivo.  
	 */		
	private void salvarPartidos() throws IOException {
		try {
			this.fos = new FileOutputStream(this.partidosFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.partidos);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			throw new IOException("Erro ao tentar salvar partidos em arquivo.");
		}
	}
	
	/**
	 * Tenta salvar o Map de comissoes em arquivo.
	 */
	private void salvarComissoes() throws IOException {
		try {
			this.fos = new FileOutputStream(this.comissoesFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.comissoes);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			throw new IOException("Erro ao tentar salvar comissoes em arquivo");
		}
	}
	
	/**
	 * Tenta salvar o Map de projetos de lei em arquivo.
	 */
	private void salvarProjetosLei() throws IOException {
		try {
			this.fos = new FileOutputStream(this.projetosLeiFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.projetosLei);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			throw new IOException("Erro ao tentar salvar projetos de lei em arquivo");
		}
	}
	
	/**
	 * Tenta salvar o Map do contador de projetos em arquivo.
	 */
	private void salvarCont() throws IOException {
		try {
			this.fos = new FileOutputStream(this.contFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.cont);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			throw new IOException("Erro ao tentar salvar contador em arquivo");
		}
	}
	
	/**
	 * Tenta salvar o plenario em arquivo.
	 */
	private void salvarPlenario() throws IOException {
		try {
			this.fos = new FileOutputStream(plenarioFile);
			this.oos = new ObjectOutputStream(fos);
			oos.writeObject(this.plenario);
			oos.flush();
			oos.close();
		} catch (IOException ioe) {
			throw new IOException("Erro ao tentar salvar plenario em arquivo");
		}
	}
	
	/**
	 * Carrega o Map de pessoas a partir de arquivo. Se o metodo carregar objeto retornar null, um novo Map eh criado. Caso contrário, o Map eh carregado.
	 */	
	@SuppressWarnings("unchecked")
	private void carregarPessoas() throws ClassNotFoundException {
		if (carregarObjeto(this.pessoasFile) == null) {
			this.pessoas = new HashMap<>();
		} else {
			this.pessoas = (HashMap<String, PessoaInterface>) (carregarObjeto(this.pessoasFile));			
		}
	}
	
	/**
	 * Carrega o Set de partidos a partir de arquivo. Se o metodo carregar objeto retornar null, um novo Set eh criado. Caso contrário, o Set eh carregado.
	 */
	@SuppressWarnings("unchecked")
	private void carregarPartidos() throws ClassNotFoundException {
		if (carregarObjeto(this.partidosFile) == null) {
			this.partidos = new TreeSet<>();
		} else {
			this.partidos = (TreeSet<String>) (carregarObjeto(this.partidosFile));			
		}
	}
	
	/**
	 * Carrega o Map de comissoes a partir de arquivo. Se o metodo carregar objeto retornar null, um novo Map eh criado. Caso contrário, o Map eh carregado.
	 */	
	@SuppressWarnings("unchecked")
	private void carregarComissoes() throws ClassNotFoundException {
		if (this.carregarObjeto(this.comissoesFile) == null) {
			this.comissoes = new HashMap<>();
		} else {
			this.comissoes = (HashMap<String, Comissao>) (carregarObjeto(this.comissoesFile));			
		}
	}
	
	/**
	 * Carrega o Map de projetos de lei a partir de arquivo. Se o metodo carregar objeto retornar null, um novo Map eh criado. Caso contrário, o Map eh carregado.
	 */
	@SuppressWarnings("unchecked")
	private void carregarProjetosLei() throws ClassNotFoundException {
		if (carregarObjeto(this.projetosLeiFile) == null) {
			this.projetosLei = new HashMap<>();
		} else {
			this.projetosLei = (HashMap<String, ProjetoDeLei>) (carregarObjeto(this.projetosLeiFile));			
		}
	}
	
	/**
	 * Carrega o Map do contador de projetos de lei a partir de arquivo. Se o metodo carregar objeto retornar null, um novo Map eh criado. Caso contrário, o Map eh carregado.
	 */
	@SuppressWarnings("unchecked")
	private void carregarCont() throws ClassNotFoundException {
		if (carregarObjeto(this.contFile) == null) {
			this.cont = new HashMap<>();
		} else {
			this.cont = (HashMap<ChaveLeiAno, Integer>) (carregarObjeto(this.contFile));
		}
	}
	
	
	/**
	 * Carrega o Set de projetos de deputados a partir de arquivo. Se o metodo carregar objeto retornar null, um novo Set eh criado. Caso contrário, o Set eh carregado.
	 */
	@SuppressWarnings("unchecked")
	private void carregarPlenario() throws ClassNotFoundException {
		if (this.carregarObjeto(plenarioFile) == null) {
			this.plenario = new HashSet<>();
		} else {
			this.plenario = (HashSet<Deputado>) this.carregarObjeto(plenarioFile);
		}
	}
	
	/**
	 * Metodo auxiliar utilizado para carregar um Objeto a partir de um arquivo. 
	 * 
	 * @param file O File que contém os objetos.
	 * 					
	 * @return Se o arquivo estiver vazio, o metodo retorna null. Caso contrario, tenta ler o objeto a partir do arquivo. Se a classe do objeto existir, o objeto eh retornado. Caso contrario lanca excecao.
	 */
	
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
			throw new ClassNotFoundException("Erro ao ler objeto a partir de arquivo");
		}
		return object;		
	}
	
	/**
	 * Metodo auxiliar utilizado para limpeza de arquivos.
	 * 
	 * @param file O arquivo em questao que sera limpo.
	 */
	
	private void limparArquivo(File file) throws FileNotFoundException  {
		try {
			file.delete();
			file.createNewFile();
		} catch (IOException e) {
			throw new FileNotFoundException("Erro ao fazer limpeza de arquivo: arquivo inexistente.");
		}
	}
			
}
