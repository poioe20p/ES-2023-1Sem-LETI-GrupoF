package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

public class Salas {

	private List<Sala> listasalas;
	private List<String> columnTitles;

	/**
	 * Construtor da classe Salas.
	 *
	 * @param csvFilePath   O caminho do arquivo CSV contendo os dados das salas.
	 */
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

	/**
	 * Obtem a lista de salas.
	 *
	 * @return Lista de objetos sala.
	 */
	public List<Sala> getListaSalas() {
		return listasalas;
	}

	/**
	 * Obtem a lista de tipos nomes das salas.
	 *
	 * @return Lista de nomes das salas.
	 */
	public List<String> getNomeSalas() {
		List<String> nomeSalas = new ArrayList<>();
		for(int i = 0; i < getListaSalas().size(); i++) {
			nomeSalas.add(getListaSalas().get(i).getInformacaoSala().get(1));
		}
		return nomeSalas;
	}
	
	/**
	 * Obtem os cabecalhos associados as salas
	 *
	 * @return Lista de cabecalhos das salas.
	 */
	public List<String> getColumnTitles() {
		return columnTitles;
	}
	
}