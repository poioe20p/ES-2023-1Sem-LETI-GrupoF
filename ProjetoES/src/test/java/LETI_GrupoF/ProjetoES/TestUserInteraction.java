package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;

import java.util.*;

class TestUserInteraction {
	static UserInteraction ui;




	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ui = new UserInteraction();
	}

	@Test
	void testUserInteraction() {
		assertNotNull(ui);
	}

	@Test
	void testGetIndicesForUserCSVColumnsMapping() {
		List<String> columnTitles2 = new ArrayList<>(List.of(
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
		));
		columnTitles2.set(0, "Dia da semana");
		columnTitles2.set(1, "Hora início da aula");
		columnTitles2.set(2, "Hora fim da aula");
		columnTitles2.set(3, "Sala atribuída à aula");
		columnTitles2.set(4, "Data da aula");
		columnTitles2.set(5, "Curso");
		columnTitles2.set(6, "Unidade Curricular");
		columnTitles2.set(7, "Turno");
		columnTitles2.set(8, "Turma");
		columnTitles2.set(9, "Inscritos no turno");
		columnTitles2.set(10, "Características da sala pedida para a aula");
		List<String> columnTitles = new ArrayList<>(List.of(
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
		));
		Map<String, Integer> indices = ui.getIndicesForUserCSVColumnsMapping(columnTitles ,columnTitles2);
		assertEquals(indices.get("Curso"), 5);
		assertEquals(indices.get("Unidade Curricular"), 6);
		assertEquals(indices.get("Turno"), 7);
		assertEquals(indices.get("Turma"), 8);
		assertEquals(indices.get("Inscritos no turno"), 9);
		assertEquals(indices.get("Dia da semana"), 0);
		assertEquals(indices.get("Hora início da aula"), 1);
		assertEquals(indices.get("Hora fim da aula"), 2);
		assertEquals(indices.get("Data da aula"), 4);
		assertEquals(indices.get("Características da sala pedida para a aula"), 10);
		assertEquals(indices.get("Sala atribuída à aula"), 3);
	}

	@Test
	void testVariablesForMetricCalculation() {
		List<String> fullList = new ArrayList<>(List.of(
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
		));

		List<String> tenElementList = fullList.subList(0, 10);
		List<String> nineElementList = fullList.subList(0, 9);
		List<String> toSmallList = fullList.subList(0, 4);

		List<String> testFullList = ui.variablesForMetricCalculation(fullList);
		List<String> testTenElementList = ui.variablesForMetricCalculation(tenElementList);
		List<String> testNineElementList = ui.variablesForMetricCalculation(nineElementList);
		List<String> testToSmallList = ui.variablesForMetricCalculation(toSmallList);

		assertEquals(testFullList.size(), 5);
		assertTrue(testFullList.contains("Inscritos no turno"));
		assertTrue(testFullList.contains("Características da sala pedida para a aula"));
		assertTrue(testFullList.contains("Sala atribuída à aula"));
		assertTrue(testFullList.contains("Capacidade Normal"));
		assertTrue(testFullList.contains("Capacidade Exame"));

		assertEquals(testTenElementList.size(), 4);
		assertTrue(testTenElementList.contains("Inscritos no turno"));
		assertTrue(testTenElementList.contains("Características da sala pedida para a aula"));
		assertTrue(testTenElementList.contains("Capacidade Normal"));
		assertTrue(testTenElementList.contains("Capacidade Exame"));

		assertEquals(testNineElementList.size(), 3);
		assertTrue(testNineElementList.contains("Inscritos no turno"));
		assertTrue(testNineElementList.contains("Capacidade Normal"));
		assertTrue(testNineElementList.contains("Capacidade Exame"));

		assertEquals(testToSmallList.size(), 2);
		assertTrue(testToSmallList.contains("Capacidade Normal"));
		assertTrue(testToSmallList.contains("Capacidade Exame"));
	}


}
