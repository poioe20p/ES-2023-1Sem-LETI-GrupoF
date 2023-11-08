package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.Reader;

class TestReader {
	static File ficheiroCSV = new File("HorarioParaTestes.csv");
	static String nomeFicheiroCSV = "HorarioParaTestes.csv";
	static Reader readerCICT02, readerA7, readerGC2, readerHorarioNExiste, readerSemNextLine;
	static List<String> listaCabecalhos;

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		readerCICT02 = new Reader(nomeFicheiroCSV, "CI-CT-02");
//		readerA7 = new Reader(nomeFicheiroCSV, "ET-A7");
//		readerGC2 = new Reader(nomeFicheiroCSV, "GC2");
//		readerHorarioNExiste = new Reader("HorarioQueNaoExiste.csv", "ET-A8");
//		readerSemNextLine = new Reader("FicheiroVazio.csv", "ET-A7");
//		listaCabecalhos = new ArrayList<String>(Arrays.asList("Curso", "Unidade Curricular", "Turno", "Turma",
//				"Inscritos no turno", "Dia da semana", "Hora inÃ­cio da aula", "Hora fim da aula", "Data da aula",
//				"CaracterÃ­sticas da sala pedida para a aula", "Sala atribuÃ­da Ã  aula"));
//	}


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		readerCICT02 = new Reader(nomeFicheiroCSV);
		readerA7 = new Reader(nomeFicheiroCSV);
		readerGC2 = new Reader(nomeFicheiroCSV);
		readerHorarioNExiste = new Reader("HorarioQueNaoExiste.csv");
		readerSemNextLine = new Reader("FicheiroVazio.csv");
		listaCabecalhos = new ArrayList<String>(Arrays.asList("Curso", "Unidade Curricular", "Turno", "Turma",
				"Inscritos no turno", "Dia da semana", "Hora inÃ­cio da aula", "Hora fim da aula", "Data da aula",
				"CaracterÃ­sticas da sala pedida para a aula", "Sala atribuÃ­da Ã  aula"));
	}

	@Test
	void testReader() {

		assertNotNull(readerCICT02);
		assertEquals(ficheiroCSV, readerCICT02.getFicheiroCSV());
		assertEquals("CI-CT-02", readerCICT02.getTurma());
//		assertIterableEquals(listaCabecalhos, readerCICT02.getColumnTitles());
		;

		assertNotNull(readerA7);
		assertEquals(ficheiroCSV, readerA7.getFicheiroCSV());
		assertEquals("ET-A7", readerA7.getTurma());

		assertNotNull(readerGC2);
		assertEquals(ficheiroCSV, readerGC2.getFicheiroCSV());
		assertEquals("GC2", readerGC2.getTurma());

		assertNotNull(readerHorarioNExiste);
		assertNotNull(readerSemNextLine);
// 		assertEquals(ficheiroCSV, readerGC2.getFicheiroCSV());

	}

}
