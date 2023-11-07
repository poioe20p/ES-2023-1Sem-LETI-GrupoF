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

	private List<List<String>> dividirPorColuna(File csv, String turma) {
		List<List<String>> data = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(csv);
			columnTitles = readColumnTitles(sc);
			while (sc.hasNextLine()) {
				List<String> linha = Arrays.asList(sc.nextLine().split(";"));
				if (existsTurma(linha.get(3),turma)) {
					data.add(linha);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	public List<String> readColumnTitles(Scanner sc) throws FileNotFoundException {
		List<String> titles = new ArrayList<>();
		if (sc.hasNextLine()) {
			titles = Arrays.asList(sc.nextLine().split(";"));
		}
		return titles;
	}
	private boolean existsTurma(String linha, String turma) {
		String[] turmas = linha.split(",");
		boolean resultado = false;
		for (int i = 0; i != turmas.length; i++) {
			if (turmas[i].trim().equals(turma))
				resultado = true;
		}
		return resultado;
	}

	public List<String> getColumnTitles() {
		return columnTitles;
	}

	public List<List<String>> getTableData() {
		return tableData;
	}

// 	 Descomentar e correr a classe para ver o resultado
//
//	public static void main(String[] args) {
//		Reader horario = new Reader("HorarioDeExemplo.csv", "ET-A1");
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