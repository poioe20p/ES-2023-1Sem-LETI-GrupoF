package LETI_GrupoF.ProjetoES;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reader {

	private File ficheiroCSV;
	private List<String> columnTitles;
	private List<List<String>> tableData;

	public Reader(String ficheiroLocal, String turma) {
		ficheiroCSV = new File(ficheiroLocal);
		tableData = dividirPorColuna(ficheiroCSV, turma);
	}

	public List<List<String>> dividirPorColuna(File csv, String turma) {
		List<List<String>> data = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(csv);
			if (sc.hasNextLine()) { // ignora a primeira linha
				columnTitles = Arrays.asList(sc.nextLine().split(";"));
			}
			while (sc.hasNextLine()) {
				List<String> linha = Arrays.asList(sc.nextLine().split(";"));
				if(linha.get(3).contains(turma)) {
					data.add(Arrays.asList(sc.nextLine().split(";")));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	public List<String> getColumnTitles() {
		return columnTitles;
	}

	public List<List<String>> getTableData() {
		return tableData;
	}

//	Descomentar e correr a classe para ver o resultado
//	
//	public static void main(String[] args) {
//		Reader horario = new Reader("HorarioDeExemplo.csv", "ET-A7");
//		List<String> cabecalho = horario.getColumnTitles();
//		System.out.println(cabecalho);
//		List<List<String>> data = horario.getTableData();
//		for (List<String> row : data) {
//
//			System.out.println(row);
//		
//		}
//	}

}