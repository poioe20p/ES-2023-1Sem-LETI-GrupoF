package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHtmlCreator {
	//static HtmlCreator horario, horarioNaoExistente;
	static String dataFilePath = "HorarioDeExemplo.csv";
	 static Horario horario;
	 static HtmlCreator html;
//	Teste por fazer
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
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
		html= new HtmlCreator(horario, ordemcolunas);
	}

	@Test
	void testHtmlCreator() {
		assertNotNull(horario);
	}

	@Test
	void testGenerateHtmlPage() {
		assertTrue(html.generateHtmlPage());
	}
 
	@Test
	void testGetHtmlPath() {
		assertEquals(html.getHtmlPath(), "Horario.html");
	}
	
}
