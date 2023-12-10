package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Sala representa uma sala de um edificio, com informacoes como o
 * nome, capacidades, tipo e edificio ao qual pertence.
 */
public class Sala {

	private List<String> informacaoSala = new ArrayList<>();
	private List<String> caracteristicasSala = new ArrayList<>();

	/**
	 * Construtor da classe Sala. Inicializa uma sala com as informações fornecidas.
	 *
	 * @param dataSala     A lista de toda a informacao que caracteriza a sala.
	 * @param columnTitles A lista de titulos do ficheiro das Salas
	 */
	public Sala(List<String> dataSala, List<String> columnTitles) {
		informacaoSala = dataSala.subList(0, 4);

		for (int i = 5; i < dataSala.size(); i++) {
			if (dataSala.get(i).trim().equals("x")) {
				caracteristicasSala.add(columnTitles.get(i));
			}
		}
	}

	// Metodos de acesso aos atributos da sala

	/**
	 * Obtem a lista com a informacao principal da sala.
	 *
	 * @return Lista da informacao da sala.
	 */
	public List<String> getInformacaoSala() {
		return informacaoSala;
	}

	/**
	 * Obtem o numero de atriburos associados a sala.
	 *
	 * @return Lista de tipos da sala.
	 */
	public int getNumeroCaracteristicas() {
		return caracteristicasSala.size();
	}

	/**
	 * Obtem a lista de tipos associados a sala.
	 *
	 * @return Lista de caracteristicas da sala.
	 */
	public List<String> getCaracteristicasSala() {
		return caracteristicasSala;
	}

	@Override
	public String toString() {
		return "Sala [edificio=" + getInformacaoSala().get(0) + ", nome=" + getInformacaoSala().get(1)
				+ ", capacidadeNormal=" + getInformacaoSala().get(2) + ", capacidadeExame=" + getInformacaoSala().get(3)
				+ ", nCaracteristicas=" + getNumeroCaracteristicas() + ", caracteristicas="
				+ getCaracteristicasSala().toString() + "]";
	}

}