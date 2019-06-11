package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Partido;

class PartidoTest {

	@Test
	void testConstrutorNomeNulo() {
		try {
			Partido p = new Partido(null);
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorNomeVazio() {
		try {
			Partido p = new Partido("");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	@Test
	void testConstrutorNomeApenasEspacos() {
		try {
			Partido p = new Partido("   ");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) {
		}
	}
	
	

}
