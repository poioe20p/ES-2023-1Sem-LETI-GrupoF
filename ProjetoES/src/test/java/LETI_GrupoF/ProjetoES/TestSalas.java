/**
 * 
 */
package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class TestSalas {
	static String nomeFicheiroCSV = "CaracterizaçãoDasSalas.csv";
	static Salas salas;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		salas= new Salas(nomeFicheiroCSV);
	}

	@Test
	void testSalas() {
		assertNotNull(salas);
	}

	@Test
	void testListaSalas() {
		assertNotNull(salas.getListaSalas());
	}
	
	@Test
	void testGetListaSalasFiltradas() {
		assertNotNull(salas.getListaSalasFiltradas(List.of("Sala de Aulas normal", "videoconferncia")));
	}

	@Test
	void testNomeSalas() {
		assertNotNull(salas.getNomeSalas());
	}

	@Test
	void testColumnTitles() {
		assertNotNull(salas.getColumnTitles());
	}
	
	@Test
	void testGetColumnsOrder() {
		assertNotNull(salas.getColumnsOrder());
	}
}


