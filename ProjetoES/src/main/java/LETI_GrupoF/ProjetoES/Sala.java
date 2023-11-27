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

	Sala(String edificio, String nome, int capacidadeN, int capacidadeE, List<String> tipo) {
		this.edificio = edificio;
		this.nome = nome;
		this.capacidadeN = capacidadeN;
		this.capacidadeE = capacidadeE;
		this.tipo = tipo;
	}

	/**
	 * Le as salas de um arquivo CSV e retorna uma lista de objetos Sala.
	 *
	 * @param csv O arquivo CSV contendo as informacoes das salas.
	 * @return Lista de objetos Sala lidos do arquivo CSV.
	 */
	public List<Sala> lerSalasDoCSV(File csv) {
		List<Sala> data = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(csv);
			columnTitles = readColumnTitles(sc);
			while (sc.hasNextLine()) {
				List<String> linha = new ArrayList<>(List.of(sc.nextLine().split(";")));
				data.add(criarSala(linha));
			}

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * Cria e retorna um objeto Sala a partir de uma linha de dados do arquivo CSV.
	 *
	 * @param linha Lista de strings representando uma linha de dados do arquivo
	 *              CSV.
	 * @return Objeto Sala criado a partir da linha de dados.
	 */
	public Sala criarSala(List<String> linha) {
		String edificio = linha.get(0);
		String nome = linha.get(1);
		int capacidadeN = Integer.parseInt(linha.get(2));
		int capacidadeE = Integer.parseInt(linha.get(3));
		List<String> tipo = new ArrayList<>();
		for (int i = 4; i != linha.size(); i++) {
			if (linha.get(i).trim() == "X") {
				tipo.add(getColumnTitles().get(i));
			}
		}
		return new Sala(edificio, nome, capacidadeN, capacidadeE, tipo);
	}

	/**
	 * Le os titulos das colunas do arquivo CSV.
	 *
	 * @param sc Scanner para leitura do arquivo CSV.
	 * @return Lista de strings contendo os titulos das colunas.
	 * @throws FileNotFoundException Excecao lancada se o arquivo nao for
	 *                               encontrado.
	 */
	public List<String> readColumnTitles(Scanner sc) throws FileNotFoundException {
		List<String> titles = new ArrayList<>();
		if (sc.hasNextLine()) {
			titles = Arrays.asList(sc.nextLine().split(";"));
		}
		return titles;
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

}