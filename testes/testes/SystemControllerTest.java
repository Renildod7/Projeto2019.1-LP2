package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.SystemController;
import entities.pessoa.PessoaCivil;
import util.Dados;

class SystemControllerTest {
	
	Dados dados;
	SystemController sc;
	String plc;
	String plnc;
	String plp;
	String pec;

	
	@BeforeEach
	void setup() {
		this.dados = new Dados();
		this.sc = new SystemController(this.dados);
		sc.cadastrarPessoa("Mateus Matia", "051222222-0", "PE", "", "ABC");
		sc.cadastrarPessoa("Raposo naoPegue", "002325622-0", "PB", "", "DEF");
		sc.cadastrarPessoa("Julio nagaita", "002324987-0", "PB", "", "DEF");
		sc.cadastrarDeputado("051222222-0", "01012019");
		sc.cadastrarDeputado("002325622-0", "01012018");
		sc.cadastrarComissao("CCJCC", "051222222-0,002325622-0");
		
		
		sc.cadastrarPartido("BASE");
		
		sc.cadastrarPessoa("pessoa1", "000000000-1", "XX", "interesse1", "BASE");
		sc.cadastrarPessoa("pessoa2", "000000000-2", "XX", "interesse2", "BASE");
		sc.cadastrarPessoa("pessoa3", "000000000-3", "XX", "interesse3", "BASE");
		sc.cadastrarPessoa("pessoa4", "000000000-4", "XX", "interesse4, interesse1", "BASE");
		sc.cadastrarPessoa("pessoa5", "000000000-5", "XX", "interesse5, interesse2", "BASE");
		sc.cadastrarPessoa("pessoa6", "000000000-6", "XX", "interesse6, interesse3", "OPOSICAO");
		sc.cadastrarPessoa("pessoa7", "000000000-7", "XX", "interesse7, interesse1", "OPOSICAO");
		sc.cadastrarPessoa("pessoa8", "000000000-8", "XX", "interesse8, interesse2", "OPOSICAO");
		sc.cadastrarPessoa("pessoa9", "000000000-9", "XX", "interesse9, interesse3", "OPOSICAO");
		sc.cadastrarPessoa("pessoa0", "000000000-0", "XX", "interesse0, interesse1", "OPOSICAO");
		
		sc.cadastrarDeputado("000000000-1", "01012016");
		sc.cadastrarDeputado("000000000-2", "01012016");
		sc.cadastrarDeputado("000000000-3", "01012016");
		sc.cadastrarDeputado("000000000-4", "01012016");
		sc.cadastrarDeputado("000000000-5", "01012016");
		sc.cadastrarDeputado("000000000-6", "01012016");
		sc.cadastrarDeputado("000000000-7", "01012016");
		sc.cadastrarDeputado("000000000-8", "01012016");
		sc.cadastrarDeputado("000000000-9", "01012016");
		sc.cadastrarDeputado("000000000-0", "01012016");
		
		sc.cadastrarComissao("CCJC", "000000000-1,000000000-7,000000000-8,000000000-4,000000000-5");
		sc.cadastrarComissao("comissao1", "000000000-6,000000000-2,000000000-3,000000000-9,000000000-0");
		sc.cadastrarComissao("comissao2", "000000000-7,000000000-2,000000000-3,000000000-7,000000000-0");
		sc.cadastrarComissao("comissao3", "000000000-1,000000000-5,000000000-8,000000000-9,000000000-0");
		
		this.plc = sc.cadastrarPL("000000000-1", 2016, "pl coclusiva", "interesses1", "url", true);
		this.plnc = sc.cadastrarPL("000000000-2", 2016, "pl nao coclusiva", "interesses2", "url", false);
		this.plp = sc.cadastrarPLP("000000000-2", 2016, "plp", "interesses2", "url", "artigo");
		this.pec = sc.cadastrarPEC("000000000-3", 2016, "pec", "interesses3", "url", "artigo");
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
			sc.cadastrarComissao("CCJCCC", "051222222-0,100000000-0");
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
	void testCadastraComissaoTemaCadastrado() {
		try {
			sc.cadastrarComissao("CCJCC", "051222222-0,051444444-0");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	
	
	@Test
	void testVotacaoPLcNormal() {
		assertTrue(sc.votarComissao(plc, "GOVERNISTA", "comissao1"));
		assertFalse(sc.votarComissao(plc, "GOVERNISTA", "-"));
		try {
			sc.votarPlenario(plnc, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLcStatusGovernistaInvalido() {
		try {
			sc.votarComissao(plnc, "INVALIDO", "comissao1");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLcDiretoNoPlenario() {
		try {
			sc.votarPlenario(plnc, "GOVERNISTA", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLcPlenarioQuorumInvalido() {
		assertTrue(sc.votarComissao(plc, "GOVERNISTA", "plenario"));
		try {
			sc.votarPlenario(plnc, "GOVERNISTA", "000000000-1,000000000-2,000000000-3,000000000-4");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	
	
	@Test
	void testVotacaoPLncNormal() {
		assertTrue(sc.votarComissao(plnc, "GOVERNISTA", "comissao1"));
		assertTrue(sc.votarComissao(plnc, "OPOSICAO", "plenario"));
		assertFalse(sc.votarPlenario(plnc, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7"));
		try {
			sc.votarPlenario(plnc, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLncStatusGovernistaInvalido() {
		try {
			sc.votarComissao(plnc, "INVALIDO", "plenario");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLncDiretoNoPlenario() {
		try {
			sc.votarPlenario(plnc, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLncPlenarioQuorumInvalido() {
		assertTrue(sc.votarComissao(plnc, "GOVERNISTA", "plenario"));
		try {
			sc.votarPlenario(plnc, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	
	@Test
	void testVotacaoPLPNormal() {
		assertFalse(sc.votarComissao(plp, "OPOSICAO", "plenario"));
		assertFalse(sc.votarPlenario(plp, "GOVERNISTA", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7"));
		try {
			sc.votarPlenario(plp, "GOVERNISTA", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLPStatusGovernistaInvalido() {
		try {
			sc.votarComissao(plp, "INVALIDO", "plenario");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLPDiretoNoPlenario() {
		try {
			sc.votarComissao(plp, "INVALIDO", "plenaio");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPLPQuorumInvalido() {
		assertTrue(sc.votarComissao(plp, "GOVERNISTA", "plenario"));
		try {
			sc.votarComissao(plp, "INVALIDO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	
	@Test
	void testVotacaoPECNormal() {
		assertTrue(sc.votarComissao(pec, "GOVERNISTA", "plenario"));
		assertFalse(sc.votarPlenario(pec, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7"));
		try {
			sc.votarPlenario(pec, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPECStatusGovernistaInvalido() {
		try {
			assertTrue(sc.votarComissao(pec, "INVALIDO", "plenario"));
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPECDiretoNoPlenario() {
		try {
			sc.votarPlenario(pec, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testVotacaoPECQuorumInvalido() {
		assertTrue(sc.votarComissao(pec, "GOVERNISTA", "plenario"));
		assertFalse(sc.votarPlenario(pec, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6,000000000-7"));
		try {
			sc.votarPlenario(pec, "OPOSICAO", "000000000-1,000000000-2,000000000-3,000000000-4,000000000-5,000000000-6");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	
	
	

	
}
