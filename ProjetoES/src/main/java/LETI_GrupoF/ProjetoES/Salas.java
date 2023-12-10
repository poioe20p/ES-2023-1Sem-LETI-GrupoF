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
	public List<List<String>> getListaSalasFiltradas(List<String> caracteristicasSalas, List<Sala> salasComCapacidadeFiltrada) {
		List<List<String>> salas = new ArrayList<>();
		for (int i = 0; i < salasComCapacidadeFiltrada.size(); i++) {
			boolean adicionarLinha = true;
			List<String> sala = new ArrayList<>();
			for (int j = 0; j < salasComCapacidadeFiltrada.get(i).getInformacaoSala().size(); j++) {
				sala.add(salasComCapacidadeFiltrada.get(i).getInformacaoSala().get(j));
			}
			sala.add(salasComCapacidadeFiltrada.get(i).getCaracteristicasSala().toString());
			for (int j = 0; j < caracteristicasSalas.size(); j++) {
				if(!salasComCapacidadeFiltrada.get(i).getCaracteristicasSala().contains(caracteristicasSalas.get(j))) {
					adicionarLinha = false;
				}
			}
			if(adicionarLinha) {
				salas.add(sala);
			}
		}
		System.out.println(salas);
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


	public List<Sala> getFirstCapacityFilteredClassRooms(List<String> filteringElements, boolean isNormalCapacityFiltering) {
		List<Sala> salasFiltradas = new ArrayList<>();
		for(Sala sala: listaSalas) {
			if(isNormalCapacityFiltering) {
				if (isClassRoomValid(filteringElements.get(1), filteringElements.get(0), isNormalCapacityFiltering, sala)) {
					salasFiltradas.add(sala);
				}
			} else {
				if (isClassRoomValid(filteringElements.get(5), filteringElements.get(4), isNormalCapacityFiltering, sala)) {
					salasFiltradas.add(sala);
				}
			}
		}
		return salasFiltradas;
	}

	public List<Sala> getFirstAndSecondCapacityFilteredClassRooms(List<String> filteringElements, boolean isNormalCapacityFiltering, List<Sala> filteredCapacityClasses) {
		List<Sala> salasFiltradas = getFirstCapacityFilteredClassRooms(filteringElements, isNormalCapacityFiltering);
		if(filteringElements.size() < 4) {
			return salasFiltradas;
		} else {
			if (isNormalCapacityFiltering) {
				if (filteringElements.get(2).equals("<")) {
					for (Sala sala : filteredCapacityClasses) {
						if (Integer.parseInt(filteringElements.get(3)) < Integer.parseInt(sala.getInformacaoSala().get(2))) {
							salasFiltradas.add(sala);
						}
					}
				} else {
					for (Sala sala : filteredCapacityClasses) {
						if (Integer.parseInt(sala.getInformacaoSala().get(2)) > Integer.parseInt(filteringElements.get(3))) {
							salasFiltradas.add(sala);
						}
					}
				}
			} else {
				if (filteringElements.get(6).equals("<")) {
					for (Sala sala : filteredCapacityClasses) {
						if (Integer.parseInt(filteringElements.get(7)) < Integer.parseInt(sala.getInformacaoSala().get(3))) {
							salasFiltradas.add(sala);
						}
					}
				} else {
					for (Sala sala : filteredCapacityClasses) {
						if (Integer.parseInt(filteringElements.get(7)) > Integer.parseInt(sala.getInformacaoSala().get(3))) {
							salasFiltradas.add(sala);
						}
					}
				}
			}
		}
		return salasFiltradas;
	}


	public boolean isClassRoomValid(String filter, String Value, boolean isNormalCapacityFiltering, Sala sala) {
		switch (filter) {
			case "=" -> {
				if(isNormalCapacityFiltering) {
					if(sala.getInformacaoSala().get(2).equals(Value)) {
						return true;
					}
				} else {
					if(sala.getInformacaoSala().get(3).equals(Value)) {
						return true;
					}
				}
			}

			case "<" -> {
				if(isNormalCapacityFiltering) {
					if( Integer.parseInt(Value) < Integer.parseInt(sala.getInformacaoSala().get(2))) {
						return true;
					}
				} else {
					if(Integer.parseInt(Value) < Integer.parseInt(sala.getInformacaoSala().get(3))) {
						return true;
					}
				}
			}

			case ">" -> {
				if(isNormalCapacityFiltering) {
					if(Integer.parseInt(Value) > Integer.parseInt(sala.getInformacaoSala().get(2))) {
						return true;
					}
				} else {
					if(Integer.parseInt(Value) > Integer.parseInt(sala.getInformacaoSala().get(3))) {
						return true;
					}
				}
			}
		}
		return false;
	}


}