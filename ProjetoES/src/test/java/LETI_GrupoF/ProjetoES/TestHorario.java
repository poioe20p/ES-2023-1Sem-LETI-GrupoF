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
	static Horario horario1;
	static Horario horario2;
	
	@BeforeAll
	
	static void setUpBeforeClass() throws Exception {
		
		horario1= new Horario("ET-A7", 1, nomeFicheiroCSV);
		horario2= new Horario(nomeFicheiroCSV);
	}

	@Test
	void testHorario() {
		assertNotNull(horario1);
		assertNotNull(horario1.getTurma());
		assertNotNull(horario1.getSalas());
		
		assertNotNull(horario1.getHorario());
		assertNotNull(horario1.getTurma());
		assertTrue(horario1.getTurma().equals("ET-A7"));
		;
	}

}
