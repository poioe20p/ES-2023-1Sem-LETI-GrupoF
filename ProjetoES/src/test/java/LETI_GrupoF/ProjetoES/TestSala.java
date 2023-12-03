package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

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
	static List<String> columnTitles;
	static File ficheiroCSV = new File("CaracterizaçãoDasSalas.csv");
	static String nomeFicheiroCSV = "HorarioParaTestes.csv";
	static Salas salas = new Salas(ficheiroCSV);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		salas.getData();
	}

	@Test
	void testSala() {
	//	sala1.lerSalasDoCSV(ficheiroCSV);
		for(Sala s:salas.getData()){
	System.out.println(s);}
	
	}

}
