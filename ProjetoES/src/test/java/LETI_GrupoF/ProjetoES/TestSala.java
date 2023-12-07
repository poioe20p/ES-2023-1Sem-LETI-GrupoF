package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestSala {

	static String edificio1 = "d";
	static String nome = "d";
	static int capacidadeN = 100;
	static int capacidadeE = 30;
	static List<String> tipo;
	static File ficheiroCSV = new File("CaracterizaçãoDasSalas.csv");
	static String nomeFicheiroCSV = "HorarioParaTestes.csv";
	
	static List<String> columnTitles = List.of("Edifício", "Nome sala", "Capacidade Normal", "Capacidade Exame",
			"N caracter’sticas", "Anfiteatro aulas", "Apoio técnico eventos", "Arq 1", "Arq 2", "Arq 3", "Arq 4",
			"Arq 5", "Arq 6", "Arq 9", "BYOD (Bring Your Own Device)", "Focus Group",
			"Horário sala visível portal público", "Laboratório de Arquitectura de Computadores I",
			"Laboratório de Arquitectura de Computadores II", "Laboratório de Bases de Engenharia",
			"Laboratório de Electrónica", "Laboratório de Informática", "Laboratório de Jornalismo",
			"Laboratório de Redes de Computadores I", "Laboratório de Redes de Computadores II",
			"Laboratório de Telecomunicações", "Sala Aulas Mestrado", "Sala Aulas Mestrado Plus", "Sala NEE",
			"Sala Provas", "Sala Reuni‹o", "Sala de Arquitectura", "Sala de Aulas normal", "Videoconferência", "Atrio");
	static List<String> data = List.of("Ala Autonoma ISCTE-IUL","Auditório Silva Leal","54","27","4"," ","",""," "," ","","","X","","","","","","","X ","X"," "," ","X"," "," "," "," "," "," ");
	static Sala sala;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	 sala= new Sala(data,columnTitles);
		//	Sala sala2= new Sala();
	}

	@Test
	/**
	 * teste da sala
	 * 
	 */
	void testSala() {
		// not nulls
	assertNotNull(sala.getCapacidadeExame());
	assertNotNull(sala.getCapacidadeNormal());
	assertNotNull(sala.getCaracteristicasSala());
	assertNotNull(sala.getEdificio());
	assertNotNull(sala.getNome());
	assertNotNull(sala.getNumeroCaracteristicas());
		//assertsEquals
	
	assertEquals(4,sala.getNumeroCaracteristicas());
	assertEquals("Auditório Silva Leal",sala.getNome());
	assertEquals(54, sala.getCapacidadeNormal());
	assertEquals(27,sala.getCapacidadeExame());
	assertEquals("Ala Autonoma ISCTE-IUL", sala.getEdificio());
	
									
	
	
		}

	}


