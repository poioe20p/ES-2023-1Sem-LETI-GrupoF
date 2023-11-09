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

	public Reader(String ficheiroLocal) {
		ficheiroCSV = new File(ficheiroLocal);
		tableData = dividirPorColuna(ficheiroCSV);
	}

	private List<List<String>> dividirPorColuna(File csv) {
		List<List<String>> data = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(csv);
			columnTitles = readColumnTitles(sc);
			while (sc.hasNextLine()) {
				List<String> linha = new ArrayList<>(List.of(sc.nextLine().split(";")));
				data.add(formatDataFromFile(linha));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	private List<String> formatDataFromFile(List<String> s) {
		while(s.size() < 11) {
			s.add("N/A");
		}
		return s;
	}


	public List<String> readColumnTitles(Scanner sc) throws FileNotFoundException {
		List<String> titles = new ArrayList<>();
		if (sc.hasNextLine()) {
			titles = Arrays.asList(sc.nextLine().split(";"));
		}
		return titles;
	}

	public List<String> getColumnTitles() {
		return columnTitles;
	}

	public List<List<String>> getTableData() {
		return tableData;
	}

	public File getFicheiroCSV() {
		return ficheiroCSV;
	}

}