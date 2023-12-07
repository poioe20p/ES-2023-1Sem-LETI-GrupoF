package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHorario {
	
	static File ficheiroCSV = new File("HorarioDeExemplo.csv");
	static String nomeFicheiroCSV = "HorarioDeExemplo.csv";
	static Reader reader, readerHorarioNExiste, readerSemNextLine;
	static List<String> listaCabecalhos;
	static Horario horario;
	
	@BeforeAll
	
	static void setUpBeforeClass() throws Exception {
		
		horario= new Horario(nomeFicheiroCSV);
	}

	@Test
	void testHorario() {
		assertNotNull(horario);
		assertNotNull(horario.getSalas());
	}

}
