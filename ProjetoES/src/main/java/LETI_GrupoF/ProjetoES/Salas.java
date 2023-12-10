package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

public class Salas {

	private List<Sala> listasalas;
	private List<String> columnTitles;

	public Salas(String csvFilePath) {
		Reader infoFromCSV = new Reader(csvFilePath);
		columnTitles = infoFromCSV.getColumnTitles();
		listasalas = lerSalasDoCSV(infoFromCSV.getTableData());
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

	public List<Sala> getListaSalas() {
		return listasalas;
	}

	public List<String> getNomeSalas() {
		List<String> nomeSalas = new ArrayList<>();
		for(int i = 0; i < getListaSalas().size(); i++) {
			nomeSalas.add(getListaSalas().get(i).getInformacaoSala().get(1));
		}
		return nomeSalas;
	}
	
	public List<String> getColumnTitles() {
		return columnTitles;
	}
	
}