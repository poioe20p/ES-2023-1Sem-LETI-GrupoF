package LETI_GrupoF.ProjetoES.user_interface;

import LETI_GrupoF.ProjetoES.Horario;
import LETI_GrupoF.ProjetoES.HtmlCreator;
import LETI_GrupoF.ProjetoES.Metrica;
import LETI_GrupoF.ProjetoES.SaveState;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Esta classe fornece interatividade à página acessada pelo usuário.
 */
public class UserInteraction {

	// Pagina da GUI
	private SubmitFilePage submitFilePage;
	private HtmlCreator htmlCreator;
	private ScheduleQualityCalculationPage scheduleQualityCalculationPage;
	private ColumnsOrderingPage columnsOrderingPage;
	private ScheduleQualityTable scheduleQualityTable;

	/**
	 *
	 * @param args
	 */
	// Para fazer correr o programa
	public static void main(String[] args) {
		SwingUtilities.invokeLater(UserInteraction::new);
	}

	/**
	 * Construtor para inicializar a pagina GUI.
	 */
	public UserInteraction() {
		submitFilePage = new SubmitFilePage();
		submitFilePage.setVisible(true);
		setUpContinueAndSubmitButton();
	}


	/**
	 * Este metodo define o comportamento dos botoes da pagina ColumnsOrderingPage.
	 *
	 * @param columnsOrderingPage
	 * @param horario
	 */
	private void setUpColumnsOrderingPageButtons(ColumnsOrderingPage columnsOrderingPage, Horario horario) {
		// Define o comportamento do boão quando o mesmo é clicado
		columnsOrderingPage.getOpenScheduleButton().addActionListener(e -> {
			horario.setOrdemCampos(getIndicesForUserCSVColumnsMapping(columnsOrderingPage.getUserOrderedColumnTitles(), horario.getColumnTitles()));
			htmlCreator = new HtmlCreator(horario, columnsOrderingPage.getUserOrderedColumnTitles());
			openSchedule();
		});

		columnsOrderingPage.getScheduleQualityButton().addActionListener(e -> {
			horario.setOrdemCampos(getIndicesForUserCSVColumnsMapping(columnsOrderingPage.getUserOrderedColumnTitles(), horario.getColumnTitles()));
			scheduleQualityCalculationPage = new ScheduleQualityCalculationPage(variablesForMetricCalculation(columnsOrderingPage.getUserOrderedColumnTitles()), columnsOrderingPage, horario);
			setUpScheduelQualityPageButton(horario);
			scheduleQualityCalculationPage.setVisible(true);
			columnsOrderingPage.setVisible(false);
		});
	}

	/**
	 * Este metodo define o comportamento dos botoes da pagina ScheduleQualityPage que corresponde à pagina onde as metricas sao calculadas.
	 *
	 * @param horario
	 */

	private void setUpScheduelQualityPageButton(Horario horario) {
		scheduleQualityCalculationPage.getCalculateScheduleQualityButton().addActionListener(e -> {
			for(Metrica metrica: scheduleQualityCalculationPage.getScheduleMetrics()) {
				//Calculo de cada metrica acontece aqui
				horario.adicionarMetrica(metrica);
			}
			scheduleQualityTable = new ScheduleQualityTable
					(horario, scheduleQualityCalculationPage);
			setUpScheduleQualityTableButtons(horario.getColumnTitles());
			scheduleQualityTable.setVisible(true);
			scheduleQualityCalculationPage.setVisible(false);
		});
	}

	/**
	 * Este metodo define o comportamento dos botoes da pagina ScheduleQualityTable que
	 * corresponde à pagina onde as metricas sao apresentadas.
	 */
	private void setUpScheduleQualityTableButtons(List<String> columnTitles) {
		scheduleQualityTable.getOpenMetricScheduleButton().addActionListener(e -> {
			String formulaMetricaLinhaSelecionada = (String) scheduleQualityTable.getTable().
					getValueAt(scheduleQualityTable.getTable().getSelectedRow(), 0);
			for(Metrica metrica: scheduleQualityTable.getData()) {
				if(metrica.getFormula().equals(formulaMetricaLinhaSelecionada)) {
					htmlCreator = new HtmlCreator(metrica.getAulasComComtribuicao(), columnsOrderingPage.getUserOrderedColumnTitles(), columnTitles);
					openSchedule();
				}
			}
		});
	}


	/**
	 * Este metodo define o comportamento do botao de continuar e submeter.
	 */

	private void setUpContinueAndSubmitButton () {
		submitFilePage.getContinueButton().addActionListener(e -> {
			// Vai buscar o input do utilizador
			String input = submitFilePage.getCsvFileLocationTextField().getText();

			if (input != null && !input.isEmpty()) {

				// Verifica se o input é um URL ou um caminho para um ficheiro
				if (input.matches("^https?://.*")) {
					submitFilePage.setRemoteFile(true);
					try {
						URL remoteFile = new URL(input);
						if (saveToLocalFile(remoteFile.openStream(), "ProjetoES/HorarioRemoto.csv")) {
							Horario horario = new Horario("ProjetoES/HorarioRemoto.csv");
							columnsOrderingPage = new ColumnsOrderingPage(horario.getColumnTitles(), submitFilePage);
							setUpColumnsOrderingPageButtons(columnsOrderingPage, horario);
							columnsOrderingPage.setVisible(true);
							submitFilePage.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(submitFilePage, "Error processing remote file, please try again",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
				// Neste caso o input é um caminho para um ficheiro local
				else {
					File file = new File(input);
					if (file.exists()) {
						// Se existir usa o csv para gerar os dados para a pagina HTML e depois abre a
						// pagina
						Horario horario = new Horario(input);
						columnsOrderingPage = new ColumnsOrderingPage(horario.getColumnTitles(), submitFilePage);
						setUpColumnsOrderingPageButtons(columnsOrderingPage, horario);
						columnsOrderingPage.setVisible(true);
						submitFilePage.setVisible(false);
					} else {
						// No caso de não existir o ficheiro aparece uma mensagem de erro
						JOptionPane.showMessageDialog(submitFilePage, "File does not exist: " + file, "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				// No caso de não existir o ficheiro aparece uma mensagem de erro
				JOptionPane.showMessageDialog(submitFilePage, "Invalid file path.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		submitFilePage.getSavedScheduleQualityButton().addActionListener(sSQB -> {
			SaveState.recuperarHorarioAntigo();
			Horario horario = new Horario(SaveState.getHorarioFilePath());
			horario.setOrdemCampos(SaveState.getOrdemCampos());
			horario.setMetricas(SaveState.getMetricas());
			scheduleQualityCalculationPage = new ScheduleQualityCalculationPage(
					variablesForMetricCalculation(new ArrayList<>(horario.getOrdemCampos().keySet())),
					submitFilePage, horario);
		});

		submitFilePage.getOpenSavedScheduleButton().addActionListener(oSSB -> {
			SaveState.recuperarHorarioAntigo();
			Horario horario = new Horario(SaveState.getHorarioFilePath());
			horario.setOrdemCampos(SaveState.getOrdemCampos());
			horario.setMetricas(SaveState.getMetricas());
			htmlCreator = new HtmlCreator(horario, new ArrayList<>(horario.getOrdemCampos().keySet()));
			openSchedule();
		});
	}

	/**
	 * Este metodo salva o arquivo remoto em um arquivo local.
	 *
	 * @param inputStream O arquivo remoto.
	 *
	 * @param localFilePath O caminho para o arquivo local.
	 *
	 * @return true se o arquivo foi salvo com sucesso, false caso contrario.
	 */
	private boolean saveToLocalFile(InputStream inputStream, String localFilePath) {
		try {
			File localFile = new File(localFilePath);
			localFile.createNewFile();

			FileOutputStream outputStream = new FileOutputStream(localFile);
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outputStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Este metodo abre a pagina HTML com a tabela no navegador e verifica se a pagina HTML foi gerada com sucesso.
	 * Caso a pagina HTML nao tenha sido gerada com sucesso, aparece uma mensagem de erro.
	 * Caso a pagina HTML tenha sido gerada com sucesso, abre a pagina HTML no navegador.
	 *
	 */
	private void openSchedule() {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			File file = new File(htmlCreator.getHtmlPath());
			if (file.exists()) {
				try {
					if (htmlCreator.generateHtmlPage()) {
						System.out.println("HTML page generated successfully");
						desktop.browse(file.toURI());
					} else {
						System.out.println("Error generating HTML page");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// No caso de não existir o ficheiro aparece uma mensagem de erro
				JOptionPane.showMessageDialog(submitFilePage, "File does not exist: " + file, "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(submitFilePage, "Desktop is not supported on this platform", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Este metodo retorna um mapa onde para cada chave String (nome da coluna do CSV) temos associado um único inteiro correspondente
	 * ao indice dessa coluna na lista de cabeçalho do CSV do utilizador.
	 * Ou seja na primeira posicao da lista de cabeçalho tens o indice da posicao dos cursos na lista de cabeçalho do CSV do utilizador
	 * porque na lista de cabeçalho default a primeira posicao corresponde ao curso. E assim sucessivamente.
	 *
	 * @param userOrderedColumnTitles Os titulos das colunas ordenados pelo utilizador.
	 * @param columnTitles Os titulos das colunas do CSV do Utilizador.
	 *
	 * @return Um mapa onde para cada chave String (nome da coluna do CSV) temos associado um único inteiro correspondente
	 */

	public Map<String, Integer> getIndicesForUserCSVColumnsMapping (List<String> userOrderedColumnTitles, List<String> columnTitles) {
		Map<String, Integer> indicesForUserCSVColumnsMap = new LinkedHashMap<>();
		for (String userOrderedColumnTitle : userOrderedColumnTitles) {
			indicesForUserCSVColumnsMap.put(userOrderedColumnTitle, columnTitles.indexOf(userOrderedColumnTitle));
		}
		return indicesForUserCSVColumnsMap;
	}

	/**
	 * Este metodo retorna as variaveis fornecidas ao utilizador para o calculo das metricas.
	 * Portanto estas sao as varivaveis que aparecem no menu dropdown da pagina GUI.
	 *
	 * @param userOrderedColumnTitles Os titulos das colunas ordenados pelo utilizador.
	 * @return Uma lista de strings com as variaveis para o calculo das metricas.
	 */

	public List<String> variablesForMetricCalculation(List<String> userOrderedColumnTitles) {
		List<String> variablesForMetricCalculation = new ArrayList<>();
		if(userOrderedColumnTitles.size() >= 11) {
			variablesForMetricCalculation.add(userOrderedColumnTitles.get(4));
			variablesForMetricCalculation.add(userOrderedColumnTitles.get(9));
			variablesForMetricCalculation.add(userOrderedColumnTitles.get(10));
		} else if (userOrderedColumnTitles.size() == 10) {
			variablesForMetricCalculation.add(userOrderedColumnTitles.get(4));
			variablesForMetricCalculation.add(userOrderedColumnTitles.get(9));
		}
		else if (userOrderedColumnTitles.size() >= 5) {
			variablesForMetricCalculation.add(userOrderedColumnTitles.get(4));
		}

		variablesForMetricCalculation.add("Capacidade Normal");
		variablesForMetricCalculation.add("Capacidade Exame");
		return variablesForMetricCalculation;
	}

}
