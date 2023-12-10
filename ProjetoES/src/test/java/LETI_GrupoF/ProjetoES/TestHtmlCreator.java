package LETI_GrupoF.ProjetoES;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHtmlCreator {
	//static HtmlCreator horario, horarioNaoExistente;
	static String dataFilePath = "ProjetoES/HorarioDeExemplo.csv";
	 static Horario horario;
	 static HtmlCreator html;
	 static HtmlCreator html2;
	 static HtmlCreator htmlFail;
//	Teste por fazer
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		List<String> lines = Arrays.asList(
				"ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Qua;13:00:00;14:30:00;02/11/2022;Sala Aulas Mestrado;AA2.25",
				"ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Seg;13:00:00;14:30:00;28/11/2022;Sala Aulas Mestrado;AA2.25",
				"ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Seg;13:00:00;14:30:00;21/11/2022;Sala Aulas Mestrado;AA2.25",
				"ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Seg;13:00:00;14:30:00;14/11/2022;Sala Aulas Mestrado;AA2.25",
				"ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Seg;13:00:00;14:30:00;07/11/2022;Sala Aulas Mestrado;AA2.25",
				"ME;Teoria dos Jogos e dos Contratos;01789TP01;MEA1;30;Ter;13:00:00;14:30:00;29/11/2022;Sala Aulas Mestrado;AA2.25"
		);

		List<List<String>> splitLines = new ArrayList<>();

		for (String line : lines) {
			List<String> splitValues = Arrays.asList(line.split(";"));
			splitLines.add(splitValues);
		}
		
		horario = new Horario(dataFilePath);
		List<String> ordemcolunas = List.of(
                "Curso",
                "Unidade Curricular",
                "Turno",
                "Turma",
                "Inscritos no turno",
                "Dia da semana",
                "Hora início da aula",
                "Hora fim da aula",
                "Data da aula",
                "Características da sala pedida para a aula",
                "Sala atribuída à aula"
        );

		List<String> ordemColunasAleatorio = new ArrayList<>(ordemcolunas);
		Collections.shuffle(ordemColunasAleatorio);
		html= new HtmlCreator(horario, ordemcolunas);
		html2 = new HtmlCreator(splitLines, ordemcolunas, ordemColunasAleatorio);
		htmlFail = new HtmlCreator(horario, new ArrayList<>());
	}

	@Test
	void testTitlesPostion() {
		assertNotNull(html.tiltesPosition());
		assertEquals(html.tiltesPosition().size(), 11);
		assertNotNull(html2.tiltesPosition());
		assertEquals(html2.tiltesPosition().size(), 11);
		assertEquals(htmlFail.tiltesPosition().size(), 0);
	}

	@Test
	void testGenerateHtmlPage() {
		assertTrue(html2.generateHtmlPage());
		assertTrue(html.generateHtmlPage());
		assertTrue(htmlFail.generateHtmlPage());
	}
 
	@Test
	void testGetHtmlPath() {
		assertEquals(html.getHtmlPath(), "Horario.html");
		assertEquals(html2.getHtmlPath(), "Horario.html");
		assertEquals(htmlFail.getHtmlPath(), "Horario.html");
	}


}
