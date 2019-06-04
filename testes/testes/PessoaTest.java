package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.Pessoa;

class PessoaTest {

	@Test
	void testConstrutorNomeNulo() {
		try {
			Pessoa p = new Pessoa(null, "000000000-0", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorNomeVazio() {
		try {
			Pessoa p = new Pessoa("", "000000000-0", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorNomeApenasEspacos() {
		try {
			Pessoa p = new Pessoa("   ", "000000000-0", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	
	
	@Test
	void testConstrutorDniNulo() {
		try {
			Pessoa p = new Pessoa("nome", null, "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorDniVazio() {
		try {
			Pessoa p = new Pessoa("nome", "", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorDniApenasEspacos() {
		try {
			Pessoa p = new Pessoa("nome", "    ", "XX", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorDniInvalido() {
		try {
			Pessoa p = new Pessoa("nome", "A00000000-0", "XX", "interesses");
			fail("");
		} catch (IllegalArgumentException npe) {
		}
	}
	
	
	
	@Test
	void testConstrutorEstadoNulo() {
		try {
			Pessoa p = new Pessoa("nome", "000000000-0", null, "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorEstadoVazio() {
		try {
			Pessoa p = new Pessoa("nome", "000000000-0", "", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testConstrutorEstadoApenasEspacos() {
		try {
			Pessoa p = new Pessoa("nome", "000000000-0", "    ", "interesses");
			fail("");
		} catch (NullPointerException npe) {
		}
	}
	
		

	
	
	@Test
	void testToStringPessoaSemPartidoSemInteresses() {
		Pessoa p = new Pessoa("nome", "000000000-0", "XX", "");
		assertEquals("nome - 000000000-0 (XX)", p.toString());
	}
	
	@Test
	void testToStringPessoaComPartidoSemInteresses() {
		Pessoa p = new Pessoa("nome", "000000000-0", "XX", "","YY");
		assertEquals("nome - 000000000-0 (XX) - YY", p.toString());
	}
	
	@Test
	void testToStringPessoaSemPartidoComInteresses() {
		Pessoa p = new Pessoa("nome", "000000000-0", "XX", "interesses");
		assertEquals("nome - 000000000-0 (XX) - Interesses: interesses", p.toString());
	}
	
	@Test
	void testToStringPessoaComPartidoComInteresses() {
		Pessoa p = new Pessoa("nome", "000000000-0", "XX", "interesses", "YY");
		assertEquals("nome - 000000000-0 (XX) - YY - Interesses: interesses", p.toString());
	}
	
	
	
	
}
