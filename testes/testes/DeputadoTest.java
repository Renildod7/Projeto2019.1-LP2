package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Deputado;
import entities.PessoaCivil;

class DeputadoTest {
	
	PessoaCivil p = new PessoaCivil("nome", "000000000-0", "XX", "interesses", "partido");

	@Test
	void testConstrutorDataDeInicioNula() {
		try {
			Deputado d = new Deputado(null, p);
			fail("Excecao nao lancada");
		} catch (NullPointerException iae) {
		}
	}
	
	@Test
	void testConstrutorDataDeInicioVazia() {
		try {
			Deputado d = new Deputado("", p);
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	void testConstrutorDataDeInicioApenasEspacos() {
		try {
			Deputado d = new Deputado("    ", p);
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testConstrutorDataDeInicioInvalida() {
		try {
			Deputado d = new Deputado("31022001", p);
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testConstrutorDataDeInicioFutura() {
		try {
			Deputado d = new Deputado("01013001", p);
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) {
		}
	}
		
	@Test
	void testGetDataDeInicio() {
		Deputado d = new Deputado("01012001", p);
		assertEquals("01/01/2001", d.getDataDeInicio());
	}

}
