package testes;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import util.Validacao;

class ValidacaoTest {

	@Test
	void testValidaStringNula() {
		try {
			Validacao.validaString(null, "mensagem");
			fail("Excecao nao lancada");
		} catch (NullPointerException npe) { }
	}
	
	@Test
	void testValidaStringVazia() {
		try {
			Validacao.validaString("", "mensagem");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaStringApenasEspacos() {
		try {
			Validacao.validaString("   ", "mensagem");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDni1() {
		try {
			Validacao.validaDni("A0000000-0", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDni2() {
		try {
			Validacao.validaDni("00000000-A", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDni3() {
		try {
			Validacao.validaDni("0000-000-0", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDni4() {
		try {
			Validacao.validaDni("0000000000-0", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDni5() {
		try {
			Validacao.validaDni("0000000-00", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	
	@Test
	void testValidaDataDataInvalida1() {
		try {
			Validacao.validaData("31022001", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDataDataInvalida2() {
		try {
			Validacao.validaData("122001", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDataDataInvalida3() {
		try {
			Validacao.validaData("05132001", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDataDataInvalida4() {
		try {
			Validacao.validaData("32122001", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDataDataInvalida5() {
		try {
			Validacao.validaData("0112001", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}
	
	@Test
	void testValidaDataDataFutura() {
		try {
			Validacao.validaData("01013001", "msgErr");
			fail("Excecao nao lancada");
		} catch (IllegalArgumentException iae) { }
	}

}
