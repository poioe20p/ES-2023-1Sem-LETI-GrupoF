package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

public class Salas {

	static final private String csvFilePath = "CaracterizaçãoDasSalas.csv";
	private List<Sala> salas = new ArrayList<>();
	private final Reader dataFromCSV;

	Salas() {
		dataFromCSV = new Reader(csvFilePath);
		salas = lerSalasDoCSV(dataFromCSV.getTableData());
	}

	/**
	 * Le as salas de um arquivo CSV e retorna uma lista de objetos Sala.
	 *
	 * @param tableData A informacao das salas lida do ficheiro CSV.
	 * @return Lista de objetos Sala obtidos da informacao do ficheiro CSV.
	 */
	public List<Sala> lerSalasDoCSV(List<List<String>> tableData) {
		List<Sala> salas = new ArrayList<>();

		for(int i = 0; i < tableData.size(); i++) {
			salas.add(new Sala(tableData.get(i)));
		}
		
		return salas;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public List<String> getColumnTitles() {
		return dataFromCSV.getColumnTitles();
	}
}
