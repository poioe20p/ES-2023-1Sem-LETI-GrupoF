package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe Salas representa uma lista de salas e fornece métodos para acessar informações sobre essas salas.
 */
public class Salas {

	private List<Sala> listaSalas;
	private List<String> columnTitles;

	/**
	 * Construtor da classe Salas.
	 *
	 * @param csvFilePath   O caminho do arquivo CSV contendo os dados das salas.
	 */
	public Salas(String csvFilePath) {
		Reader infoFromCSV = new Reader(csvFilePath);
		columnTitles = infoFromCSV.getColumnTitles();
		listaSalas = lerSalasDoCSV(infoFromCSV.getTableData());
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
		return listaSalas;
	}
	
	/**
	 * Obtem a lista de salas ofrmatadas em Strings.
	 *
	 * @return Lista de de salas.
	 */
	public List<List<String>> getListaSalasFiltradas(List<String> caracteristicasSalas) {
		List<List<String>> salas = new ArrayList<>();
		for (int i = 0; i < listaSalas.size(); i++) {
			boolean adicionarLinha = true;
			List<String> sala = new ArrayList<>();
			for (int j = 0; j < listaSalas.get(i).getInformacaoSala().size(); j++) {
				sala.add(listaSalas.get(i).getInformacaoSala().get(j));
			}
			sala.add(listaSalas.get(i).getCaracteristicasSala().toString());
			for (int j = 0; j < caracteristicasSalas.size(); j++) {
				if(!listaSalas.get(i).getCaracteristicasSala().contains(caracteristicasSalas.get(j))) {
					adicionarLinha = false;
				}
			}
			if(adicionarLinha) {
				salas.add(sala);
			}
		}
		return salas;
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
	
	/**
	 * Obtem os cabecalhos relevantes para a pagina de filtragem das salas
	 *
	 * @return Lista de cabecalhos.
	 */
	public List<String> getColumnTitlesFiltered() {
		List<String> getColumnTitlesFiltered = new ArrayList<>();
		getColumnTitlesFiltered.add(columnTitles.get(0));
		getColumnTitlesFiltered.add(columnTitles.get(1));
		getColumnTitlesFiltered.add(columnTitles.get(2));
		getColumnTitlesFiltered.add(columnTitles.get(3));
		getColumnTitlesFiltered.add("Caracteristicas Sala");
		return columnTitles;
	}
	
	/**
	 * Obtem a ordem dos cabecalhos getColumnTitlesFiltered
	 *
	 * @return Mapa da ordem dos cabecalhos.
	 */
	public Map<String, Integer> getColumnsOrder(){
		Map<String, Integer> ordemCampos = new LinkedHashMap<>();
		for(int i = 0; i < getColumnTitlesFiltered().size(); i++) {
			ordemCampos.put(getColumnTitlesFiltered().get(i), i);
		}
		return ordemCampos;
	}
	
}