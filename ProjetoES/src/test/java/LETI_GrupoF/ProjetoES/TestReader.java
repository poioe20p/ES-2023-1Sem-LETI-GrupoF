package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestReader {
	static File ficheiroCSV = new File("HorarioParaTestes.csv");
	static String nomeFicheiroCSV = "HorarioParaTestes.csv";
	static Reader reader, readerHorarioNExiste, readerSemNextLine;
	static List<String> listaCabecalhos;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		reader = new Reader(nomeFicheiroCSV);
		readerHorarioNExiste = new Reader("HorarioQueNaoExiste.csv");
		readerSemNextLine = new Reader("FicheiroVazio.csv");
		listaCabecalhos = new ArrayList<>(Arrays.asList("Curso", "Unidade Curricular", "Turno", "Turma",
				"Inscritos no turno", "Dia da semana", "Hora inÃ­cio da aula", "Hora fim da aula", "Data da aula",
				"CaracterÃ­sticas da sala pedida para a aula", "Sala atribuÃ­da Ã  aula"));
	}

	@Test
	void testReader() {

		assertNotNull(reader);
		assertEquals(ficheiroCSV, reader.getFicheiroCSV());

		assertEquals(readerHorarioNExiste.getTableData(), new ArrayList<String>());
		
		assertEquals(readerSemNextLine.getTableData(), new ArrayList<String>());

	}

	@Test
	void testGetColumnTitles() {
		
//		boolean resultado = true;
//		
//		for(int i = 0; i != listaCabecalhos.size(); i++) {
//			resultado = listaCabecalhos.get(i).equals(reader.getColumnTitles().get(i));
//			if(!resultado) break;
//		}
//		assertTrue(resultado);
		
		assertNull(readerHorarioNExiste.getColumnTitles());
		
		assertNull(readerSemNextLine.getColumnTitles());
	}
	
}
