package LETI_GrupoF.ProjetoES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Reader {

	File ficheiroCSV = new File("HorarioDeExemplo.csv");
	String[][] tableData = new String[10][10];

	public String[][] dividirPorColuna() {
		String nextLine;
		Scanner sc;
		try {
			sc = new Scanner(ficheiroCSV);
			if (sc.hasNextLine()) // ignora a primeira linha
				sc.nextLine();

			int i = 0;
			while (sc.hasNextLine()) {
				nextLine = sc.nextLine();
				String[] line = nextLine.split(";");
				tableData[i] = line;

				System.out.println(tableData[i]);
				System.out.println("");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return tableData;

	}

	public void dividirPorColunaJP(String siglaCurso) {

		String dataToRead = "HorarioDeExemplo.csv";
		String line;
		String delimiter = ",";
		List<List<String>> data = new ArrayList<>();// cada indice de da lista representa uma linhado ficheiro csv

		try (BufferedReader bf = new BufferedReader(new FileReader(dataToRead))) {

			while ((line = bf.readLine()) != null) {

				String[] values = line.split(delimiter);
				if (values[0].equals(siglaCurso)) {
					data.add(Arrays.asList(values));
				}

			}
			System.out.println(data.get(1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (List<String> row : data) {// escrever todas a linhas

			System.out.println(row);
		
		}
	}

	public static void main(String[] args) {
		Reader a = new Reader();
		a.dividirPorColunaJP("LETI");

	}

}