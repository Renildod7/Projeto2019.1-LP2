package others;

import controller.SystemController;
import easyaccept.EasyAccept;

public class Facade {
	
	private SystemController systemCntrl;
	
	public Facade() {
		this.systemCntrl = new SystemController();
	}
	
	public static void main(String[] args) {
		args = new String[] {"others.Facade", "easyaccept/use_case_1.txt", 
				 							  "easyaccept/use_case_2.txt", 
				 							  "easyaccept/use_case_3.txt", 
				 							  "easyaccept/use_case_4.txt", 
				 							  "easyaccept/use_case_5.txt",
				 							  "easyaccept/use_case_6.txt"};		
		EasyAccept.main(args);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.systemCntrl.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.systemCntrl.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPartido(String nome) {
		this.systemCntrl.cadastrarPartido(nome);
	}
	
	public String exibirBase() {
		return systemCntrl.exibirBase();
	}
	
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.systemCntrl.cadastrarDeputado(dni, dataDeInicio);
	}
	
	public String exibirPessoa(String dni) {
		return this.systemCntrl.exibirPessoa(dni);
	}
	
	public void cadastrarComissao(String tema, String politicos) {
		this.systemCntrl.cadastrarComissao(tema, politicos);
	}
	
	public void cadastrarPL(String dni, int ano, String ementa, String interesses, String url, boolean conclusivo) {
		this.systemCntrl.cadastrarPL(dni, ano, ementa, interesses, url, conclusivo);
	}
	
	public void cadastrarPLP(String dni, int ano, String ementa, String interesses, String url, String artigo) {
		this.systemCntrl.cadastrarPLP(dni, ano, ementa, interesses, url, artigo);
	}
	
	public void cadastrarPEC(String dni, int ano, String ementa, String interesses, String url, String artigo) {
		this.systemCntrl.cadastrarPEC(dni, ano, ementa, interesses, url, artigo);
	}
	
	public String exibirProjeto(String codigo) {
		return this.systemCntrl.exibirProjeto(codigo);
	}
	
	
	public void limparSistema() {		
	}
	
	public void salvarSistema() {		
	}
	
	public void carregarSistema() {
	}

}
