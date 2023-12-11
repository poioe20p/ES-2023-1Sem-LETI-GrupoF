package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestHorario {
	
	static String nomeFicheiroCSV = "HorarioDeExemplo.csv";
	static Horario horario;
	static Map<String, Integer> ordemCampos;
	static Metrica numeroAulasSobrelotacao = new Metrica("Inscritos no turno;-;Capacidade Normal;>;0");
	static Metrica numeroAulasSobrelotacao2 = new Metrica("Capacidade Normal;-;Inscritos no turno;<;0");
	static Metrica numeroAulasSalaDesperdicada = new Metrica("Características da sala pedida para a aula;-;Sala atribuída à aula");
	static Metrica metricaNumericaMultiplicacao = new Metrica("Inscritos no turno;*;Capacidade Exame;>;0");
	static Metrica metricaNumericaMultiplicacao2 = new Metrica("Capacidade Exame;*;Inscritos no turno;>;0");
	static Metrica metricaNumericaDivisao = new Metrica("Inscritos no turno;/;Capacidade Exame;=;10");
	static Metrica metricaNumericaDivisao2 = new Metrica("Capacidade Exame;/;Inscritos no turno;=;10");
	static Metrica metricaNumericaAdicao = new Metrica("Inscritos no turno;+;Capacidade Normal");
	static Metrica metricaNumericaAdicao2 = new Metrica("Capacidade Normal;+;Inscritos no turno");
	static Metrica numeroAulasSalaBemAtribuida = new Metrica("Características da sala pedida para a aula;=;Sala atribuída à aula");
	static Metrica numeroAulasSalaBemAtribuida2 = new Metrica("Sala atribuída à aula;=;Características da sala pedida para a aula");
	static Metrica numeroAulasSalaMalAtribuida = new Metrica("Características da sala pedida para a aula;!=;Sala atribuída à aula");
	static Metrica numeroAulasSalaMalAtribuida2 = new Metrica("Sala atribuída à aula;!=;Características da sala pedida para a aula");
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		horario = new Horario(nomeFicheiroCSV);
		
		ordemCampos = new LinkedHashMap<>();
		for(int i = 0; i < horario.getColumnTitles().size(); i++) {
			ordemCampos.put(horario.getColumnTitles().get(i), i);
		}
		horario.setOrdemCampos(ordemCampos);
	}

	@Test
	void testHorario() {
		assertNotNull(horario);
	}
	
	@Test
	void testAdicionarMetrica() {
		horario.adicionarMetrica(numeroAulasSobrelotacao);
		horario.adicionarMetrica(numeroAulasSobrelotacao2);
		horario.adicionarMetrica(numeroAulasSalaDesperdicada);
		horario.adicionarMetrica(metricaNumericaMultiplicacao);
		horario.adicionarMetrica(metricaNumericaMultiplicacao2);
		horario.adicionarMetrica(metricaNumericaDivisao);
		horario.adicionarMetrica(metricaNumericaDivisao2);
		horario.adicionarMetrica(metricaNumericaAdicao);
		horario.adicionarMetrica(metricaNumericaAdicao2);
		horario.adicionarMetrica(numeroAulasSalaBemAtribuida);
		horario.adicionarMetrica(numeroAulasSalaBemAtribuida2);
		horario.adicionarMetrica(numeroAulasSalaMalAtribuida);
		horario.adicionarMetrica(numeroAulasSalaMalAtribuida2);
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
	void testGetHorarioFilePath() {
		assertEquals(nomeFicheiroCSV, horario.getHorarioFilePath());
	}
	
	@Test
	void testGetSalas() {
		assertNotNull(horario.getSalas());
	}
	
	@Test
	void testGetMetricas() {
		assertNotNull(horario.getMetricas());
	}

	@Test
	void testGetOrdemCampos() {
		assertEquals(ordemCampos, horario.getOrdemCampos());
	}
	
}
