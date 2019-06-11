package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.PartidoController;

class PartidoControllerTest {

	PartidoController cntrl;
	@BeforeEach
	void preparaTest() {
		this.cntrl = new PartidoController();
	}
	
	@Test
	void testCadastraPartidoNomeNulo() {
		try {
			this.cntrl.cadastrarPartido(null);
			fail("");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testCadastraPartidoNomeVazio() {
		try {
			this.cntrl.cadastrarPartido("");
			fail("");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testCadastraPartidoNomeApernasEspacos() {
		try {
			this.cntrl.cadastrarPartido("  ");
			fail("");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testCadastraPartidoPartidoJaCadastrado() {
		this.cntrl.cadastrarPartido("NOME");
		try {
			this.cntrl.cadastrarPartido("NOME");
			fail("");
		} catch (IllegalArgumentException npe) { }
	}
	
	@Test
	void testExibiBaseNenhumPartidoCadastrado() {
		assertEquals("", this.cntrl.exibirBase());
	}
	
	@Test
	void testExibiBasePartidoCadastrado() {
		this.cntrl.cadastrarPartido("NOME");
		assertEquals("NOME", this.cntrl.exibirBase());
	}
}
