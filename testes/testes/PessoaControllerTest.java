package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.PessoaController;

class PessoaControllerTest {
	
	PessoaController cntrl;
	
	@BeforeEach
	void preparaTestes() {
		this.cntrl = new PessoaController();
	}
	
	@Test
	void testCadastraPessoaNomeNulo() {
		try {
			this.cntrl.cadastrarPessoa(null, "000000000-0", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraPessoaNomeVazio() {
		try {
			this.cntrl.cadastrarPessoa("", "000000000-0", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraPessoaNomeApenasEspacos() {
		try {
			this.cntrl.cadastrarPessoa("   ", "000000000-0", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	
	@Test
	void testCadastraPessoaDniNulo() {
		try {
			this.cntrl.cadastrarPessoa("nome", null, "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraPessoaDniVazio() {
		try {
			this.cntrl.cadastrarPessoa("nome", "", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraPessoaDniApenasEspacos() {
		try {
			this.cntrl.cadastrarPessoa("nome", "    ", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	
	@Test
	void testCadastraPessoaEstadoNulo() {
		try {
			this.cntrl.cadastrarPessoa("nome", "000000000-0", null, "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraPessoaEstadoVazio() {
		try {
			this.cntrl.cadastrarPessoa("nome", "000000000-0", "", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraPessoaEstadoApenasEspacos() {
		try {
			this.cntrl.cadastrarPessoa("nome", "000000000-0", "    ", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	
	@Test
	void testCadastraPessoaPessoaJaCadastrada() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses");
		try {
			this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses");
			fail("");
		} catch (IllegalArgumentException npe) {
		}
	}

	
	
	

}
