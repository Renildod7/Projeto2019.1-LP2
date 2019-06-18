package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entities.PessoaCivil;

class PessoaTest {

	@Test
	void testConstrutorNomeNulo() {
		try {
			PessoaCivil p = new PessoaCivil(null, "000000000-0", "XX", "interesses", "");
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testConstrutorNomeVazio() {
		try {
			PessoaCivil p = new PessoaCivil("", "000000000-0", "XX", "interesses", "");
			fail("Excecao nao lancada");;
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testConstrutorNomeApenasEspacos() {
		try {
			PessoaCivil p = new PessoaCivil("   ", "000000000-0", "XX", "interesses", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testConstrutorDniNulo() {
		try {
			PessoaCivil p = new PessoaCivil("nome", null, "XX", "interesses", "");
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testConstrutorDniVazio() {
		try {
			PessoaCivil p = new PessoaCivil("nome", "", "XX", "interesses", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testConstrutorDniApenasEspacos() {
		try {
			PessoaCivil p = new PessoaCivil("nome", "    ", "XX", "interesses", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testConstrutorDniInvalido() {
		try {
			PessoaCivil p = new PessoaCivil("nome", "A00000000-0", "XX", "interesses", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testConstrutorEstadoNulo() {
		try {
			PessoaCivil p = new PessoaCivil("nome", "000000000-0", null, "interesses", "");
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testConstrutorEstadoVazio() {
		try {
			PessoaCivil p = new PessoaCivil("nome", "000000000-0", "", "interesses", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testConstrutorEstadoApenasEspacos() {
		try {
			PessoaCivil p = new PessoaCivil("nome", "000000000-0", "    ", "interesses", "");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}

	@Test
	void testToStringPessoaSemPartidoSemInteresses() {
		PessoaCivil p = new PessoaCivil("nome", "000000000-0", "XX", "", "");
		assertEquals("nome - 000000000-0 (XX)", p.toString());
	}
	
	@Test
	void testToStringPessoaComPartidoSemInteresses() {
		PessoaCivil p = new PessoaCivil("nome", "000000000-0", "XX", "","YY");
		assertEquals("nome - 000000000-0 (XX) - YY", p.toString());
	}
	
	@Test
	void testToStringPessoaSemPartidoComInteresses() {
		PessoaCivil p = new PessoaCivil("nome", "000000000-0", "XX", "interesses", "");
		assertEquals("nome - 000000000-0 (XX) - Interesses: interesses", p.toString());
	}
	
	@Test
	void testToStringPessoaComPartidoComInteresses() {
		PessoaCivil p = new PessoaCivil("nome", "000000000-0", "XX", "interesses", "YY");
		assertEquals("nome - 000000000-0 (XX) - YY - Interesses: interesses", p.toString());
	}
	
}
