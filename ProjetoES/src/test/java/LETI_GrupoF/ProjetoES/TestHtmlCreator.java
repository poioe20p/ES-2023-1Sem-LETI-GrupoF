package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHtmlCreator {
	static HtmlCreator horario, horarioNaoExistente;
	static String dataFilePath = "HorarioParaTestes.csv";

	//Teste por fazer
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		horario = new HtmlCreator(dataFilePath, null);
	}

	@Test
	void testHtmlCreator() {
		assertNotNull(horario);
	}

	@Test
	void testGenerateHtmlPage() {
		assertTrue(horario.generateHtmlPage());
	}

	@Test
	void testGetHtmlPath() {
		assertEquals(horario.getHtmlPath(), "Horario.html");
	}
	
}
