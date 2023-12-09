package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestSala {
	
	static List<String> columnTitles = List.of("Edificio", "Nome sala", "Capacidade Normal", "Capacidade Exame",
			"N caracteristicas", "Anfiteatro aulas", "Apoio tecnico eventos", "Arq 1", "Arq 2", "Arq 3", "Arq 4",
			"Arq 5", "Arq 6", "Arq 9", "BYOD (Bring Your Own Device)", "Focus Group",
			"Horario sala visível portal público", "Laboratorio de Arquitectura de Computadores I",
			"Laboratorio de Arquitectura de Computadores II", "Laboratorio de Bases de Engenharia",
			"Laboratorio de Electronica", "Laboratório de Informática", "Laboratorio de Jornalismo",
			"Laboratorio de Redes de Computadores I", "Laboratorio de Redes de Computadores II",
			"Laboratorio de Telecomunicacoes", "Sala Aulas Mestrado", "Sala Aulas Mestrado Plus", "Sala NEE",
			"Sala Provas", "Sala Reuniao", "Sala de Arquitectura", "Sala de Aulas normal", "videoconferencia", "Atrio");
	static List<String> data = List.of("Ala Autonoma ISCTE-IUL","Auditório Silva Leal","54","27","4","","","","","","","","","","","","x","","","","","","","","","","x","x","","","","","x","","");
	static List<String> informacaoSala = List.of("Ala Autonoma ISCTE-IUL","Auditório Silva Leal","54","27");
	static List<String> caracterisitcasSala = List.of("Horario sala visível portal público", "Sala Aulas Mestrado", "Sala Aulas Mestrado Plus", "Sala de Aulas normal");
	static Sala sala;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sala= new Sala(data,columnTitles);
	}

	@Test
	/**
	 * teste da sala
	 * 
	 */
	void testSala() {
		assertNotNull(sala);
	}
	
	@Test
	void testGetInformacaoSala() {
		assertNotNull(sala.getInformacaoSala());
		assertEquals(informacaoSala,sala.getInformacaoSala());
	}

	@Test
	void testGetNumeroCaracteristicas() {
		assertNotNull(sala.getNumeroCaracteristicas());
		assertEquals(4,sala.getNumeroCaracteristicas());
	}

	@Test
	void testGetCaracteristicasSala() {
		assertNotNull(sala.getCaracteristicasSala());
		assertEquals(caracterisitcasSala,sala.getCaracteristicasSala());
	}
	
	@Test
	void testToString() {
		assertNotNull(sala.toString());
	}
}


