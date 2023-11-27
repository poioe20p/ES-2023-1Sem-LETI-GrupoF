package LETI_GrupoF.ProjetoES;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A classe Reader e responsavel por ler dados de um arquivo CSV, processar e
 * armazenar as informacoes em estruturas de dados apropriadas.
 */
public class Reader {

	private File ficheiroCSV;
	private List<String> columnTitles;
	private List<List<String>> tableData;

	/**
	 * Construtor da classe Reader. Inicializa o objeto Reader com o caminho do
	 * arquivo CSV e realiza a leitura do arquivo para preencher as estruturas de
	 * dados.
	 *
	 * @param ficheiroLocal O caminho local do arquivo CSV a ser lido.
	 */
	public Reader(String ficheiroLocal) {
		ficheiroCSV = new File(ficheiroLocal);
		tableData = dividirPorColuna(ficheiroCSV);
	}

	/**
	 * Metodo privado que realiza a leitura do arquivo CSV, divide as linhas em
	 * colunas e formata os dados conforme necessario.
	 *
	 * @param csv O arquivo CSV a ser lido.
	 * @return Uma lista de listas de strings representando os dados da tabela CSV.
	 */
	private List<List<String>> dividirPorColuna(File csv) {
		List<List<String>> data = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(csv);
			columnTitles = readColumnTitles(sc);
			while (sc.hasNextLine()) {
				List<String> linha = new ArrayList<>(List.of(sc.nextLine().split(";")));
				data.add(formatDataFromFile(linha));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	/**
	 * Metodo privado que formata os dados lidos do arquivo, preenchendo valores
	 * ausentes.
	 *
	 * @param s Lista de strings representando uma linha de dados do arquivo CSV.
	 * @return Lista de strings formatada.
	 */
	private List<String> formatDataFromFile(List<String> s) {
		while (s.size() < 11) {
			s.add("N/A");
		}
		return s;
	}

	/**
	 * Metodo que le os titulos das colunas do arquivo CSV.
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

	/**
	 * Obtem os titulos das colunas do arquivo CSV.
	 *
	 * @return Lista de strings contendo os titulos das colunas.
	 */
	public List<String> getColumnTitles() {
		return columnTitles;
	}

	/**
	 * Obtem os dados da tabela do arquivo CSV.
	 *
	 * @return Lista de listas de strings representando os dados da tabela.
	 */
	public List<List<String>> getTableData() {
		return tableData;
	}

	/**
	 * Obtem o objeto File associado ao arquivo CSV.
	 *
	 * @return Objeto File representando o arquivo CSV.
	 */
	public File getFicheiroCSV() {
		return ficheiroCSV;
	}

}