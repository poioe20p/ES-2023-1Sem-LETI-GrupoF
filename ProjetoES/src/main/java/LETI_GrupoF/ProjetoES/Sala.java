package LETI_GrupoF.ProjetoES;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A classe Sala representa uma sala de um edificio, com informacoes como o
 * nome, capacidades, tipo e edificio ao qual pertence.
 */
public class Sala {

	private String edificio;
	private String nome;
	private int capacidadeN;
	private int capacidadeE;
	private int nCaracteristicas;
	private List<String> tipo;
	private List<String> columnTitles;

	/**
	 * Construtor da classe Sala. Inicializa uma sala com as informações fornecidas.
	 *
	 * @param edificio    O nome do edificio ao qual a sala pertence.
	 * @param nome        O nome da sala.
	 * @param capacidadeN A capacidade normal da sala.
	 * @param capacidadeE A capacidade extra da sala.
	 * @param tipo        A lista de tipos associados a sala.
	 */

	Sala(String edificio, String nome, int capacidadeN, int capacidadeE, int nCaracteristicas, List<String> tipo) {
		this.edificio = edificio;
		this.nome = nome;
		this.capacidadeN = capacidadeN;
		this.capacidadeE = capacidadeE;
		this.nCaracteristicas = nCaracteristicas;
		this.tipo = tipo;
	}

	public int getnCaracteristicas() {
		return nCaracteristicas;
	}

	public void setnCaracteristicas(int nCaracteristicas) {
		this.nCaracteristicas = nCaracteristicas;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCapacidadeN(int capacidadeN) {
		this.capacidadeN = capacidadeN;
	}

	public void setCapacidadeE(int capacidadeE) {
		this.capacidadeE = capacidadeE;
	}

	public void setTipo(List<String> tipo) {
		this.tipo = tipo;
	}

	public void setColumnTitles(List<String> columnTitles) {
		this.columnTitles = columnTitles;
	}

	// Metodos de acesso aos atributos da sala

	/**
	 * Obtem o nome da sala.
	 *
	 * @return Nome da sala.
	 */
	String getNome() {
		return nome;
	}

	/**
	 * Obtem os titulos das colunas do arquivo CSV.
	 *
	 * @return Lista de strings contendo os titulos das colunas.
	 */
	public List<String> getColumnTitles() {
		return columnTitles;
	}

	/**
	 * Obtem a capacidade extra da sala.
	 *
	 * @return Capacidade extra da sala.
	 */
	public int getCapacidadeE() {
		return capacidadeE;
	}

	/**
	 * Obtem a capacidade normal da sala.
	 *
	 * @return Capacidade normal da sala.
	 */
	public int getCapacidadeN() {
		return capacidadeN;
	}

	/**
	 * Obtem o nome do edificio ao qual a sala pertence.
	 *
	 * @return Nome do edificio.
	 */
	public String getEdificio() {
		return edificio;
	}

	/**
	 * Obtem a lista de tipos associados a sala.
	 *
	 * @return Lista de tipos da sala.
	 */
	public List<String> getTipo() {
		return tipo;
	}

	public int getNCaracteristicas() {
		return nCaracteristicas;
	}

	@Override
	public String toString() {
		return "Sala [edificio=" + edificio + ", nome=" + nome + ", capacidadeN=" + capacidadeN + ", capacidadeE="
				+ capacidadeE + ", nCaracteristicas=" + nCaracteristicas + ", tipo=" + tipo + ", columnTitles="
				+ columnTitles + "]";
	}

}