package LETI_GrupoF.ProjetoES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Salas {
	private List<String> columnTitles;
	private List<Sala> data = new ArrayList<>();
	static final File csv = new File("CaracterizaçãoDasSalas.csv");

	Salas(File csv) {
		data = lerSalasDoCSV(csv);
	}

	public List<Sala> getData() {
		return data;
	}

	/**
	 * Le as salas de um arquivo CSV e retorna uma lista de objetos Sala.
	 *
	 * @param csv O arquivo CSV contendo as informacoes das salas.
	 * @return Lista de objetos Sala lidos do arquivo CSV.
	 */
	public List<Sala> lerSalasDoCSV(File csv) {
		List<Sala> data = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
			columnTitles = readColumnTitles(br);

			String linha;
			while ((linha = br.readLine()) != null) {
				List<String> valores = new ArrayList<>(List.of(linha.split(";")));
				//System.out.println(linha);
				data.add(criarSala(valores));
			}

		} catch (IOException e) {
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
		int nCaracteristicas = Integer.parseInt(linha.get(4));
		List<String> tipo = new ArrayList<>();
		for (int i = 5; i < linha.size(); i++) {
			if ("X".equals(linha.get(i).trim())) {
				tipo.add(getColumnTitles().get(i));
			}
		}
		return new Sala(edificio, nome, capacidadeN, capacidadeE, nCaracteristicas, tipo);
	}

	/**
	 * Le os titulos das colunas do arquivo CSV.
	 *
	 * @param sc Scanner para leitura do arquivo CSV.
	 * @return Lista de strings contendo os titulos das colunas.
	 * @throws FileNotFoundException Excecao lancada se o arquivo nao for
	 *                               encontrado.
	 */
	public List<String> readColumnTitles(BufferedReader br) throws IOException {
		List<String> titles = new ArrayList<>();
		String line = br.readLine();
		//System.out.println(line);
		if (line != null) {
			titles = Arrays.asList(line.split(";"));
		}
		return titles;
	}

	public List<String> getColumnTitles() {
		return columnTitles;
	}
}
