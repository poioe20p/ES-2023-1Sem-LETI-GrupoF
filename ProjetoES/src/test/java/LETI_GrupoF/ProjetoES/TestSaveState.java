package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestSaveState {

	static String nomeFicheiroCSV = "HorarioDeExemplo.csv";
	static Horario horario;
	static Metrica numeroAulasSobrelotacao = new Metrica("Inscritos no turno;-;Capacidade Normal;>;0");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		horario = new Horario(nomeFicheiroCSV);
		Map<String, Integer> ordemCampos = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < horario.getColumnTitles().size(); i++) {
			ordemCampos.put(horario.getColumnTitles().get(i), i);

		}
		horario.setOrdemCampos(ordemCampos);
	}
	
	@Test
	void testGuardarHorario() throws FileNotFoundException {
		sS.guardarHorario(horario.getHorarioFilePath(), horario.getOrdemCampos(),
				new ArrayList<>(horario.getMetricas().keySet()));
		assertNotNull(sS.RecuperarHorarioAntigo());
	}

	@Test
	void testGuardarMetricas() throws FileNotFoundException {
		Horario horarioRecuperado = sS.RecuperarHorarioAntigo();
		assertNotNull(horarioRecuperado, "O horário não foi recuperado corretamente.");
	}


	@Test
	void testLimparSaveState() throws FileNotFoundException {
		sS.LimparSaveState();
		assertNull(sS.RecuperarHorarioAntigo(), "O arquivo não foi limpo corretamente.");
	}

//	@Test
//	void testRecuperarHorarioAntigos() throws FileNotFoundException {
//		Horario horarioRecuperado = sS.RecuperarHorarioAntigo();
//		assertNotNull(horarioRecuperado, "O horário não foi recuperado corretamente.");
//	}

	@Test
	void testGetHorarioFilePath() throws FileNotFoundException {
		Horario horarioRecuperado = sS.RecuperarHorarioAntigo();
		assertNotNull(horarioRecuperado, "O horário não foi recuperado corretamente.");
	}

	@Test
	void testGetOrdemCampos() throws FileNotFoundException {
		Horario horarioRecuperado = sS.RecuperarHorarioAntigo();
		assertNotNull(horarioRecuperado, "O horário não foi recuperado corretamente.");
	}

	@Test
	void testGetMetricas() throws FileNotFoundException {
		Horario horarioRecuperado = sS.RecuperarHorarioAntigo();
		assertNotNull(horarioRecuperado, "O horário não foi recuperado corretamente.");
	}

}
