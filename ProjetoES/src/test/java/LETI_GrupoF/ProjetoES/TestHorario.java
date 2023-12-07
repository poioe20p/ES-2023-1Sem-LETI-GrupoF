package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHorario {
	
	static String nomeFicheiroCSV = "HorarioDeExemplo.csv";
	static Horario horario;
	static Metrica numeroAulasSobrelotacao = new Metrica("Inscritos no turno;-;Capacidade Normal;>;0");
	
	@BeforeAll
	
	static void setUpBeforeClass() throws Exception {
		horario= new Horario(nomeFicheiroCSV);
		horario.setOrdemCampos();
	}

	@Test
	void testHorario() {
		assertNotNull(horario);
		assertNotNull(horario.getSalas());
	}
	
	@Test
	void testAdicionarMetrica() {
		horario.adicionarMetrica(numeroAulasSobrelotacao);
	}
	
	@Test
	void testGetColumnTiltes() {
		assertNotNull(horario.getColumnTitles());
	}
	
	@Test
	void testGetHorario() {
		assertNotNull(horario.getHorario());
	}
	
	@Test
	void testGetSalas() {
		assertNotNull(horario.getSalas());
	}
	
	@Test
	void testGetMetricas() {
		assertNotNull(horario.getMetricas());
	}

}
