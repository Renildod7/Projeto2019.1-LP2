package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Deputado;

class DeputadoTest {

	@Test
	void testConstrutorDataDeInicioNula() {
		try {
			Deputado d = new Deputado("nome", "000000000-0", "XX", "interesses", "YY", null);
			fail("");
		} catch (NullPointerException iae) {
		}
	}
	
	@Test
	void testConstrutorDataDeInicioVazia() {
		try {
			Deputado d = new Deputado("nome", "000000000-0", "XX", "interesses", "YY", "");
			fail("");
		} catch (NullPointerException iae) {
		}
	}

	@Test
	void testConstrutorDataDeInicioApenasEspacos() {
		try {
			Deputado d = new Deputado("nome", "000000000-0", "XX", "interesses", "YY", "    ");
			fail("");
		} catch (NullPointerException iae) {
		}
	}
	
	@Test
	void testConstrutorDataDeInicioInvalida() {
		try {
			Deputado d = new Deputado("nome", "000000000-0", "XX", "interesses", "YY", "31022001");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testConstrutorDataDeInicioFutura() {
		try {
			Deputado d = new Deputado("nome", "000000000-0", "XX", "interesses", "YY", "01013001");
			fail("");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	
	
	@Test
	void testGetDataDeInicio() {
		Deputado d = new Deputado("nome", "000000000-0", "XX", "interesses", "YY", "01012001");
		assertEquals("01/01/2001", d.getDataDeInicio());
	}

}
