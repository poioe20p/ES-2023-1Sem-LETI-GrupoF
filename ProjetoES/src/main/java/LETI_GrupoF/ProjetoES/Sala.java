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
	 * @param caracterizacaoSala   A lista de toda a informacao que caracteriza a sala.
	 */
	
	Sala(List<String> dataSala, List<String> columnTitles){
		informacaoSala = dataSala.subList(0, 4);
		
		for(int i = 5; i < dataSala.size(); i++) {
			if(dataSala.get(i).equals("X")) {
				caracteristicasSala.add(columnTitles.get(i));
			}
		}
	}
	
	// Metodos de acesso aos atributos da sala

	/**
	 * Obtem o nome do edificio ao qual a sala pertence.
	 *
	 * @return Nome do edificio.
	 */
	public String getEdificio() {
		return informacaoSala.get(0);
	}

	/**
	 * Obtem o nome da sala.
	 *
	 * @return Nome da sala.
	 */
	String getNome() {
		return informacaoSala.get(1);
	}

	/**
	 * Obtem a capacidade normal da sala.
	 *
	 * @return Capacidade normal da sala.
	 */
	public int getCapacidadeNormal() {
		return Integer.parseInt(informacaoSala.get(2));
	}

	/**
	 * Obtem a capacidade extra da sala.
	 *
	 * @return Capacidade extra da sala.
	 */
	public int getCapacidadeExame() {
		return Integer.parseInt(informacaoSala.get(3));
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
		return "Sala [edificio=" + getEdificio() + ", nome=" + getNome() + ", capacidadeNormal=" + getCapacidadeNormal()
				+ ", capacidadeExame=" + getCapacidadeExame() + ", nCaracteristicas=" + getNumeroCaracteristicas()
				+ ", caracteristicas=" + getCaracteristicasSala().toString() + "]";
	}

}