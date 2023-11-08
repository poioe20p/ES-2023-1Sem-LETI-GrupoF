package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHtmlCreator {
	static HtmlCreator horarioMEA1, horarioNaoExistente, paginaHorarioNaoExistente;
	static String pageFilePath = "Horario.html";
    static String dataFilePath = "HorarioDeExemplo.csv";
    static String turma = "MEA1";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		horarioMEA1 = new HtmlCreator(pageFilePath, dataFilePath, turma);
		horarioNaoExistente = new HtmlCreator(pageFilePath, "ProjetoEs/HorarioNaoExistente.csv", turma);
		paginaHorarioNaoExistente = new HtmlCreator("ProjetoES/HorarioNaoExistente.html", dataFilePath, turma);
	}

	@Test
	void testHtmlCreator() {
		assertNotNull(horarioMEA1);
		assertNotNull(horarioNaoExistente);
		assertNotNull(paginaHorarioNaoExistente);
	}

	@Test
	void testGenerateHtmlPage() {
		assertTrue(horarioMEA1.generateHtmlPage());
		assertFalse(horarioNaoExistente.generateHtmlPage());
		assertFalse(horarioNaoExistente.generateHtmlPage());
	}

}
