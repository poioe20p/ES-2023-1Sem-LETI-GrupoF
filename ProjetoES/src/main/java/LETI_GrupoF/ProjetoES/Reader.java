package LETI_GrupoF.ProjetoES;

import java.io.File;
import java.io.FileNotFoundException;
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

	public static void main(String[] args) {
		Reader a = new Reader();
		a.dividirPorColuna();
	}

}