package others;

import controller.PartidoController;
import controller.PessoaController;
import easyaccept.EasyAccept;

public class Facade {
	
	private PessoaController pessoaCntrl;
	private PartidoController partidoCntrl;
	
	public Facade() {
		this.pessoaCntrl = new PessoaController();
		this.partidoCntrl = new PartidoController();
	}
	
	public static void main(String[] args) {
		args = new String[] {"others.Facade", "easyaccept/use_case_1.txt", "easyaccept/use_case_2.txt", "easyaccept/use_case_3.txt", "easyaccept/use_case_4.txt" };		
		EasyAccept.main(args);
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
	
	public void limparSistema() {		
	}
	
	public void salvarSistema() {		
	}
	
	public void carregarSistema() {		
	}

}
