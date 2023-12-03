package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

public class Salas {

	private List<Sala> salas;
	private List<String> columnTitles;

	public Salas(String csvFilePath) {
		Reader infoFromCSV = new Reader(csvFilePath);
		salas = lerSalasDoCSV(infoFromCSV.getTableData());
		columnTitles = infoFromCSV.getColumnTitles();
	}

	/**
	 * Le as salas de um arquivo CSV e retorna uma lista de objetos Sala.
	 *
	 * @param tableData A informacao das salas lida do ficheiro CSV.
	 * @return Lista de objetos Sala obtidos da informacao do ficheiro CSV.
	 */
	private List<Sala> lerSalasDoCSV(List<List<String>> tableData) {
		List<Sala> salas = new ArrayList<>();

		for(int i = 0; i < tableData.size(); i++) {
			salas.add(new Sala(tableData.get(i), getColumnTitles()));
		}
		
		return salas;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public List<String> getNomeSalas() {
		List<String> nomeSalas = new ArrayList<>();
		for(int i = 0; i < getSalas().size(); i++) {
			nomeSalas.add(getSalas().get(i).getNome());
		}
		return nomeSalas;
	}
	
	public List<String> getColumnTitles() {
		return columnTitles;
	}
	
}