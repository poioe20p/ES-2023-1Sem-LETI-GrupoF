package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHorario {
	
	static String nomeFicheiroCSV = "HorarioDeExemplo.csv";
	static Horario horario;
	static Metrica numeroAulasSobrelotacao = new Metrica("Inscritos no turno;-;Capacidade Normal;>;0");
	static Metrica numeroAulasSobrelotacao2 = new Metrica("Capacidade Normal;-;Inscritos no turno;>;0");
	static Metrica numeroAulasSalaMalAtribuida = new Metrica("Características da sala pedida para a aula;-;Sala atribuída à aula;>;0");
	
	@BeforeAll
	
	static void setUpBeforeClass() throws Exception {
		horario= new Horario(nomeFicheiroCSV);
		
		Map<String, Integer> ordemCampos = new LinkedHashMap<String, Integer>();
		for(int i = 0; i < horario.getColumnTitles().size(); i++) {
			ordemCampos.put(horario.getColumnTitles().get(i), i);
		}
		horario.setOrdemCampos(ordemCampos);
	}

	@Test
	void testHorario() {
		assertNotNull(horario);
		assertNotNull(horario.getSalas());
	}
	
	@Test
	void testAdicionarMetrica() {
		horario.adicionarMetrica(numeroAulasSobrelotacao);
		horario.adicionarMetrica(numeroAulasSobrelotacao2);
		horario.adicionarMetrica(numeroAulasSalaMalAtribuida);
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
