/**
 * 
 */
package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class TestSalas {
	static String nomeFicheiroCSV = "C:\\Users\\João Oliveira\\OneDrive - ISCTE-IUL\\Documentos\\ISCTE\\3º ano 1º Semestre\\Projeto-ES\\ES-2023-1Sem-LETI-GrupoF\\ProjetoES\\CaracterizaçãoDasSalas.csv";
	static Salas salas;
	static Salas salas2;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	 salas= new Salas(nomeFicheiroCSV);
	// salas2= new Salas("nao existe");
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
	    void testNomeSalas() {
	        assertNotNull(salas.getNomeSalas());
	    }

	    @Test
	    void testColumnTitles() {
	        assertNotNull(salas.getColumnTitles());
	    }

	    
	    /*
	    @Test
	    void testSalas2() {
	        assertNotNull(salas2);
	    }
	    */
	}


