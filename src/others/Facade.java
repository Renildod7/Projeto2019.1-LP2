package others;

import controller.PartidoController;
import controller.PessoaController;
import easyaccept.EasyAccept;

public class Facade {
	
	private PessoaController pc;
	private PartidoController pcc;
	
	public Facade() {
		this.pc = new PessoaController();
		this.pcc = new PartidoController();
	}
	
	public static void main(String[] args) {
		args = new String[] {"others.Facade", "easyaccept/use_case_1.txt", "easyaccept/use_case_2.txt", "easyaccept/use_case_3.txt", "easyaccept/use_case_4.txt" };		
		EasyAccept.main(args);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pc.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pc.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void cadastrarPartido(String nome) {
		this.pcc.cadastrarPartido(nome);
	}
	
	public String exibirBase() {
		return pcc.exibirBase();
	}
	
	public void cadastrarDeputado(String dni, String dataDeInicio) {
		this.pc.cadastrarDeputado(dni, dataDeInicio);
	}
	
	public String exibirPessoa(String dni) {
		return this.pc.exibirPessoa(dni);
	}
	
	public void limparSistema() {		
	}
	
	public void salvarSistema() {		
	}
	
	public void carregarSistema() {		
	}

}
