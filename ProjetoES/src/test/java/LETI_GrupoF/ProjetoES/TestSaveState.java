package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestSaveState {

	static String nomeFicheiroCSV = "HorarioDeExemplo.csv";
	static Horario horario;
	static Map<String, Integer> ordemCampos;
	static Metrica numeroAulasSobrelotacao = new Metrica("Inscritos no turno;-;Capacidade Normal;>;0");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		horario = new Horario(nomeFicheiroCSV);
		ordemCampos = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < horario.getColumnTitles().size(); i++) {
			ordemCampos.put(horario.getColumnTitles().get(i), i);
		}
	}
	
	@Test
	void testGuardarHorario() {
		horario.setOrdemCampos(ordemCampos);
		try {
			Scanner sc = new Scanner(new File(SaveState.getSaveStateFilePath()));
			List<String> linhas = new ArrayList<>();
			while (sc.hasNextLine()) {
                linhas.add(sc.nextLine());
			}
			assertEquals(1 + horario.getColumnTitles().size() + 1, linhas.size());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testGuardarMetricas() {
		horario.setOrdemCampos(ordemCampos);
		horario.adicionarMetrica(numeroAulasSobrelotacao);
		try {
			Scanner sc = new Scanner(new File(SaveState.getSaveStateFilePath()));
			List<String> linhas = new ArrayList<>();
			while (sc.hasNextLine()) {
                linhas.add(sc.nextLine());
			}
			assertEquals(1 + horario.getColumnTitles().size() + 1 + horario.getMetricas().size(), linhas.size());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	void testLimparSaveState() {
		horario.setOrdemCampos(ordemCampos);
		SaveState.limparSaveState();
		try {
			Scanner sc = new Scanner(new File(SaveState.getSaveStateFilePath()));
			List<String> linhas = new ArrayList<>();
			while (sc.hasNextLine()) {
                linhas.add(sc.nextLine());
			}
			assertEquals(0, linhas.size());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testRecuperarHorarioAntigo() {
		horario.setOrdemCampos(ordemCampos);
		horario.adicionarMetrica(numeroAulasSobrelotacao);
		SaveState.recuperarHorarioAntigo();
		assertEquals("HorarioDeExemplo.csv", SaveState.getHorarioFilePath());
		assertEquals(ordemCampos, SaveState.getOrdemCampos());
		assertEquals(horario.getMetricas().size(), SaveState.getMetricas().size());
	}

}
