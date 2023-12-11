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


	//TEste para verificar se a lista de salas filtradas é null
	@Test
	void testNomeSalass() {
		assertNotNull(salas.getNomeSalas());
	}

	@Test
	void testColumnTitles() {
		assertNotNull(salas.getColumnTitles());
	}

}


