package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SystemController;

class SystemControllerTest {
	
	private SystemController sc;
	
	@BeforeEach
	void setup() {
		this.sc = new SystemController();
		sc.cadastrarPessoa("Mateus Matia", "051222222-0", "PE", "", "ABC");
		sc.cadastrarPessoa("Raposo naoPegue", "002325622-0", "PB", "", "DEF");
		sc.cadastrarPessoa("Julio nagaita", "002324987-0", "PB", "", "DEF");
		sc.cadastrarDeputado("051222222-0", "01012019");
		sc.cadastrarDeputado("002325622-0", "01012018");
		sc.cadastrarComissao("CCJCC", "051222222-0,002325622-0");
	}
	
	@Test
	void testCadastraComissaoTemaVazio() {
		try {
			sc.cadastrarComissao("", "051222222-0,051444444-0");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testCadastraComissaoTemaEspacos() {
		try {
			sc.cadastrarComissao("  ", "051222222-0,051444444-0");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testCadastraComissaoTemaNulo() {
		try {
			sc.cadastrarComissao(null, "051222222-0,051444444-0");
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testCadastraComissaoPessoaDniInvalido() {
		try {
			sc.cadastrarComissao("CCJC", "051222222-A,051444444-0");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testCadastraComissaoPessoaInexistente() {
		try {
			sc.cadastrarComissao("CCJC", "051222222-0,000000000-0");
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testCadastraComissaoPessoaNaoDeputado() {
		try {
			sc.cadastrarComissao("CCJC", "051222222-A,051444444-0,002324987-0");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testCadastraComissaoListaPoliticosVazia() {
		try {
			sc.cadastrarComissao("CCJC", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void tesCadastraComissaoTemaCadastrado() {
		try {
			sc.cadastrarComissao("CCJCC", "051222222-0,051444444-0");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	
	
}
