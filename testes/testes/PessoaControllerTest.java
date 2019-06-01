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

	
	
	@Test
	void testCadastraDeputadoDniNulo() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado(null, "01012001");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDniVazio() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("", "01012001");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDniApenasEspacos() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("    ", "01012001");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDniInvalido() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("A00000000-0", "01012001");
			fail("");
		} catch (IllegalArgumentException npe) {
		}
	}
	
	
	
	@Test
	void testCadastraDeputadoDataDeInicioNula() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("000000000-0", null);
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDataDeInicioVazia() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("000000000-0", "");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDataDeInicioApenasEspacos() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("000000000-0", "   ");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDataDeInicioDataInvalida() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("000000000-0", "31022001");
			fail("");
		} catch (IllegalArgumentException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoDataDeInicioDataFutura() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.cadastrarDeputado("000000000-0", "01013005");
			fail("");
		} catch (IllegalArgumentException npe) {
		}
	}
	
	@Test
	void testCadastraDeputadoPessoaNaoEncontrada() {
		try {
			this.cntrl.cadastrarDeputado("000000000-0", "01012001");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	
	
	@Test
	void testExibePessoaDniNulo() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.exibirPessoa(null);
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testExibePessoaDniVazio() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.exibirPessoa("");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testExibePessoaDniApenasEspacos() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.exibirPessoa("   ");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testExibePessoaDniInvalido() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		try {
			this.cntrl.exibirPessoa("A00000000-0");
			fail("");
		} catch (IllegalArgumentException npe) {
		}
	}
	
	@Test
	void testExibePessoaDniPessoaNaoEncontrada() {
		try {
			this.cntrl.exibirPessoa("000000000-0");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testExibePessoaPessoaCadastrada() {
		this.cntrl.cadastrarPessoa("nome", "000000000-0", "XX", "interesses", "YY");
		assertEquals("nome - 000000000-0 (XX) - YY - Interesses: interesses", this.cntrl.exibirPessoa("000000000-0"));
	}

}
