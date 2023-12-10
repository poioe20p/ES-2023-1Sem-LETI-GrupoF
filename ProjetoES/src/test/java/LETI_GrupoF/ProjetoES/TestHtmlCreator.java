package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHtmlCreator {
	//static HtmlCreator horario, horarioNaoExistente;
	static String dataFilePath = "HorarioDeExemplo.csv";
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
	void testTitlesPosition() {
	    assertNotNull(html.tiltesPosition());
	    assertEquals(11, html.tiltesPosition().size());
	    assertNotNull(html2.tiltesPosition());
	    assertEquals(11, html2.tiltesPosition().size());
	    assertEquals(0, htmlFail.tiltesPosition().size());
	}

	@Test
	void testGenerateHtmlPage() {
	    assertTrue(html2.generateHtmlPage());
	    assertTrue(html.generateHtmlPage());
	    assertTrue(htmlFail.generateHtmlPage());
	}
 
	@Test
	void testGetHtmlPath() {
	    assertEquals("Horario.html", html.getHtmlPath());
	    assertEquals("Horario.html", html2.getHtmlPath());
	    assertEquals("Horario.html", htmlFail.getHtmlPath());
	}


}
