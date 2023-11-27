package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestSchedule {
	
	static File ficheiroCSV = new File("HorarioParaTestes.csv");
	static String nomeFicheiroCSV = "HorarioParaTestes.csv";
	static Reader reader, readerHorarioNExiste, readerSemNextLine;
	static List<String> listaCabecalhos;
	static Schedule horario;
	
	@BeforeAll
	
	static void setUpBeforeClass() throws Exception {
		Reader reader= new Reader(nomeFicheiroCSV);
		horario= new Schedule("ET-A7", reader);
	}

	@Test
	void testSchedule() {
		assertNotNull(horario);
		assertTrue(horario.existsTurma("ET-A7"));
		assertFalse(horario.existsTurma("ET-B7"));
		assertNotNull(horario.getHorario());
		assertNotNull(horario.getTurma());
		assertTrue(horario.getTurma().equals("ET-A7"));
		;
	}

}
