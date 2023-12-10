package LETI_GrupoF.ProjetoES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SaveState {

	private static final String saveStateFilePath = "SaveState.txt";
	private static String horarioFilePath;
	private static Map<String, Integer> ordemCampos = new LinkedHashMap<>();
	private static Map<Metrica, Integer> metricas = new LinkedHashMap<>();

	/**
	 * Este método tem como finalidade guardas as configuracoes da aplicacao, que possibilita ao
	 * utilizador a sua reutilização caso este queira retomar a sua sessão anterior.
	 *
	 * @param horarioFilePath Indica qual o horario usado pelo utilizador na sessao
	 * @param ordemCampos Um mapa que define a ordem e a posicao dos campos no horario. Cada chave representa um campo e o valor
	 *                    associado indica a posicao do mesmo.
	 */

	public static void guardarHorario(String horarioFilePath, Map<String, Integer> ordemCampos) {
		try {
			FileWriter file = new FileWriter(saveStateFilePath);
			PrintWriter writer = new PrintWriter(file);

			writer.println(horarioFilePath);

			for (Map.Entry<String, Integer> entry : ordemCampos.entrySet()) {
				writer.println(entry.getKey() + ":" + entry.getValue());
			}

			writer.println("FOC"); //Fim Ordem Campos

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este metodo tem como objetivo armazenar as metricas, preservando as formulas
	 * personalizadas introduzidas pelo utilizador, permitindo assim restaurar o
	 * estado anterior das formulas caso o utilizador pretenda recuperar a sessao
	 * anterior
	 *
	 * // * @param formula A formula criada pelo utilizador que sera guardada.
	 * Permite recuperar a formula caso o utilizador retome a sessão.
	 */

	public static void guardaMetricas(Map<Metrica, Integer> metricas) {
		try {
			Scanner sc = new Scanner(new File(saveStateFilePath));
			List<String> linhas = new ArrayList<>();
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				if (!linha.trim().equals("FOC")) {
					linhas.add(linha);
				}else {
					linhas.add(linha);
					break;
				}
			}
			FileWriter file = new FileWriter(saveStateFilePath);
			PrintWriter writer = new PrintWriter(file);
			for(String linha : linhas) {
				writer.println(linha);
			}
			for (Map.Entry<Metrica, Integer> entry : metricas.entrySet()) {
				writer.println(entry.getKey().getFormula() + ":" + entry.getValue());
			}
			sc.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recupera o horario anterior, permitindo que o utilizador retome a sua sessao
	 * anterior.
	 *
	 * @return Um objeto do tipo Horario representando o horario previamente
	 *         armazenado. Retorna null se não houver um horario anteriormente
	 *         armazenado.
	 * @throws FileNotFoundException
	 */

	public static void recuperarHorarioAntigo() {
		boolean isFOC = true;
		try {
			Scanner sc = new Scanner(new File(saveStateFilePath));
			if(sc.hasNextLine()) {
				horarioFilePath = sc.nextLine();
				while (sc.hasNextLine()) {
					String linha = sc.nextLine();
					if (!linha.trim().equals("FOC") && isFOC) {
						String[] partes = linha.split(":");
						ordemCampos.put(partes[0], Integer.parseInt(partes[1]));
					} else if (isFOC) {
						isFOC = false;
					} else {
						String[] partes = linha.split(":");
						metricas.put(new Metrica(partes[0].trim().replace(" ", ";")), Integer.parseInt(partes[1]));
					}
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Limpa o conteudo do arquivo SaveState.txt, removendo todas as informacoes
	 * previamente armazenadas. Esta funcao e utilizada quando o utilizador opta por
	 * nao retomar a sessao anterior.
	 *
	 */
	public static void limparSaveState() {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(saveStateFilePath);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getHorarioFilePath() {
		return horarioFilePath;
	}

	public static Map<String, Integer> getOrdemCampos() {
		return ordemCampos;
	}

	public static Map<Metrica, Integer> getMetricas() {
		return metricas;
	}
	
	public static String getSaveStateFilePath() {
		return saveStateFilePath;
	}

}
