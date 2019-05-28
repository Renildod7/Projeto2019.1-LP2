package others;

import controller.PessoaController;
import easyaccept.EasyAccept;

public class Facade {
	
	private PessoaController pc;
	
	public Facade() {
		this.pc = new PessoaController();
	}
	
	public static void main(String[] args) {
		args = new String[] {"others.Facade", "easyaccept/use_case_1.txt"};		
		EasyAccept.main(args);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses, String partido) {
		this.pc.cadastrarPessoa(nome, dni, estado, interesses, partido);
	}
	
	public void cadastrarPessoa(String nome, String dni, String estado, String interesses) {
		this.pc.cadastrarPessoa(nome, dni, estado, interesses);
	}
	
	public void limparSistema() {		
	}
	
	public void salvarSistema() {
		
	}
	
	

}
