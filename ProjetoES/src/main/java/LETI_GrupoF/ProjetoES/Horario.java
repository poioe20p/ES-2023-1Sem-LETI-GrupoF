package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe Horario representa um horario especifico para uma determinada turma.
 */
public class Horario {

	private static final String csvFilePath = "CaracterizaçãoDasSalas.csv";
	private String horarioFilePath;
	private List<List<String>> horario;
	private List<String> columnTitles;
	private Map<String, Integer> ordemCampos;
	private Salas salas;
	private Map<Metrica, Integer> metricas = new LinkedHashMap<>();

	/**
	 * Construtor da classe Horario.
	 *
	 * @param horarioFilePath O caminho do arquivo CSV contendo os dados do horario.
	 */
	public Horario(String horarioFilePath) {
		this.horarioFilePath = horarioFilePath;
		Reader dataHorarioDoCSV = new Reader(horarioFilePath);
		columnTitles = dataHorarioDoCSV.getColumnTitles();
		horario = dataHorarioDoCSV.getTableData();
		salas = new Salas(csvFilePath);
	}

	/**
	 * Adiciona uma metrica ao horario e salva o estado.
	 *
	 * @param metrica A métrica a ser adicionada.
	 */
	public void adicionarMetrica(Metrica metrica) {
		metricas.put(metrica, calcularQualidade(metrica));
		SaveState.guardaMetricas(metricas);
	}

	private int calcularQualidade(Metrica metrica) {
		List<String> formula = metrica.getComponentesFormula(); // Vai buscar os componetes da formula para o calculo da
																// metrica
		List<String> atributo1 = defineAtributo(formula.get(0));
		List<String> atributo2 = defineAtributo(formula.get(2));
		List<String> contaInicial = new ArrayList<>();
		if (atributo1.get(0).equals("horario") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialHorario(atributo1.subList(1, atributo1.size()),
					atributo2.subList(1, atributo2.size()), formula.get(1));
		} else if (atributo1.get(0).equals("horario") && atributo2.get(0).equals("sala")) {
			contaInicial = calculoParcialHorarioSala(atributo1.subList(1, atributo1.size()),
					atributo2.subList(1, atributo2.size()), formula.get(1));
		} else if (atributo1.get(0).equals("sala") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialSalaHorario(atributo1.subList(1, atributo1.size()),
					atributo2.subList(1, atributo2.size()), formula.get(1));
		}
		if (formula.size() > 3) {
			int count = 0;
			for (int i = 0; i < contaInicial.size(); i++) {
				switch (formula.get(3)) {
				case "<":
					if (Integer.parseInt(contaInicial.get(i)) < Integer.parseInt(formula.get(4))) {
						count++;
						metrica.adicionarAula(getHorario().get(i));
					}
					break;
				case ">":
					if (Integer.parseInt(contaInicial.get(i)) > Integer.parseInt(formula.get(4))) {
						count++;
						metrica.adicionarAula(getHorario().get(i));
					}
					break;
				case "=":
					if (contaInicial.get(i).equals(formula.get(4))) {
						count++;
						metrica.adicionarAula(getHorario().get(i));
					}
					break;
				}
			}
			return count;
		} else if (formula.get(1).equals("=") || formula.get(1).equals("!=")) {
			int count = 0;
			for (int i = 0; i < contaInicial.size(); i++) {
				if (contaInicial.get(i).equals("true")) {
					count++;
					metrica.adicionarAula(getHorario().get(i));
				}
			}
			return count;
		} else {
			int sum = 0;
			for (int i = 0; i < contaInicial.size(); i++) {
				if (Integer.parseInt(contaInicial.get(i)) != 0) {
					sum += Integer.parseInt(contaInicial.get(i));
					metrica.adicionarAula(getHorario().get(i));
				}
			}
			return sum;
		}
	}

	private List<String> defineAtributo(String nomeAtributo) {
		List<String> atributo = new ArrayList<>();
		if (getColumnTitles().contains(nomeAtributo)) { // Verifica se o atributo (que é um campo/coluna de um dos
														// ficheiros(horario ou salas)) pertence ao horario
			atributo.add("horario");
			for (int i = 0; i < getHorario().size(); i++) {
				atributo.add(getHorario().get(i).get(ordemCampos.get(nomeAtributo))); // Vai buscar uma lista com todo o conteudo do atributo
			}
		} else if (getSalas().getColumnTitles().contains(nomeAtributo)) { // Verifica se o atributo pertence as salas
			atributo.add("sala");
			for (int i = 0; i < getSalas().getListaSalas().size(); i++) {
				atributo.add(getSalas().getListaSalas().get(i).getInformacaoSala()
						.get(getSalas().getColumnTitles().indexOf(nomeAtributo))); // Vai buscar uma lista com todo o conteudo do atributo
			}
		}
		return atributo;
	}

	private List<String> calculoParcialHorario(List<String> atributoHoraio1, List<String> atributoHoraio2, String operador) {
		List<String> contaInicial = new ArrayList<>();
		if (isListaDeSalas(atributoHoraio1)) {
			contaInicial = comparaAtributos(atributoHoraio2, atributoHoraio1, operador);
		} else {
			contaInicial = comparaAtributos(atributoHoraio1, atributoHoraio2, operador);
		}
		return contaInicial;
	}

	private boolean isListaDeSalas(List<String> atributo) {
		return getSalas().getNomeSalas().contains(atributo.get(1));
	}

	private List<String> comparaAtributos(List<String> listaRequesitosSalas, List<String> salaAtribuida, String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 0; i < listaRequesitosSalas.size(); i++) {
			int linhaSalaFicheiroCSVSalas = getSalas().getNomeSalas().indexOf(salaAtribuida.get(i));
			if (!getHorario().get(i).get(posicaoColunaSalaHorario()).equals("N/A") && linhaSalaFicheiroCSVSalas >= 0) {
				List<String> caracteristicasSala = new ArrayList<>(
						getSalas().getListaSalas().get(linhaSalaFicheiroCSVSalas).getCaracteristicasSala());
				switch (operador) {
				case "-":
					caracteristicasSala.remove(listaRequesitosSalas.get(i));
					contaInicial.add(String.valueOf(caracteristicasSala.size()));
					break;
				case "=":
					if (caracteristicasSala.contains(listaRequesitosSalas.get(i))) {
						contaInicial.add("true");
					} else {
						contaInicial.add("false");
					}
					break;
				case "!=":
					if (caracteristicasSala.contains(listaRequesitosSalas.get(i))) {
						contaInicial.add("false");
					} else {
						contaInicial.add("true");
					}
					break;
				}
			} else {
				contaInicial.add("0"); // Nao ha sala
			}
		}
		return contaInicial;
	}

	private List<String> calculoParcialHorarioSala(List<String> atributoHoraio, List<String> atributoSala, String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 0; i < atributoHoraio.size(); i++) {
			int indexSalaAula = indexSalaAula(i);
			if (!getHorario().get(i).get(posicaoColunaSalaHorario()).equals("N/A") && indexSalaAula >= 0) {
				int valorHorario = Integer.parseInt(atributoHoraio.get(i));
				int valorSala = Integer.parseInt(atributoSala.get(indexSalaAula));
				switch (operador) {
				case "*":
					contaInicial.add(String.valueOf(valorHorario * valorSala));
					break;
				case "/":
					if (valorHorario > 0 && valorSala > 0) {
						contaInicial.add(String.valueOf(valorHorario / valorSala));
					} else {
						contaInicial.add("0");
					}
					break;
				case "+":
					contaInicial.add(String.valueOf(valorHorario + valorSala));
					break;
				case "-":
					int resultado = valorHorario - valorSala;
					if (resultado > 0) {
						contaInicial.add(String.valueOf(resultado)); // Ha sobrelotacao
					} else {
						contaInicial.add("0"); // Nao ha sobrelotacao
					}
					break;
				}
			} else {
				contaInicial.add("0"); // Nao ha sala
			}
		}
		return contaInicial;
	}

	private List<String> calculoParcialSalaHorario(List<String> atributoSala, List<String> atributoHoraio, String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 0; i < atributoHoraio.size(); i++) {
			int indexSalaAula = indexSalaAula(i);
			if (!getHorario().get(i).get(posicaoColunaSalaHorario()).equals("N/A") && indexSalaAula >= 0) {
				int valorHorario = Integer.parseInt(atributoHoraio.get(i));
				int valorSala = Integer.parseInt(atributoSala.get(indexSalaAula));
				switch (operador) {
				case "*":
					contaInicial.add(String.valueOf(valorSala * valorHorario));
					break;
				case "/":
					if (valorHorario > 0 && valorSala > 0) {
						contaInicial.add(String.valueOf(valorSala / valorHorario));
					} else {
						contaInicial.add("0");
					}
					break;
				case "+":
					contaInicial.add(String.valueOf(valorSala + valorHorario));
					break;
				case "-":
					int resultado = valorSala - valorHorario;
					if (resultado < 0) {
						contaInicial.add(String.valueOf(resultado)); // Ha sobrelotacao
					} else {
						contaInicial.add("0"); // Nao ha sobrelotacao
					}
					break;
				}
			} else {
				contaInicial.add("0"); // Nao ha sala
			}
		}
		return contaInicial;
	}

	private int indexSalaAula(int indexAula) {
		int posicaoColunaSalaHorario = posicaoColunaSalaHorario();
		return getSalas().getNomeSalas().indexOf(getHorario().get(indexAula).get(posicaoColunaSalaHorario));
	}

	private int posicaoColunaSalaHorario() {
		int posicaoColunaSalaHorario = 0;
		int i = 1;
		for (Map.Entry<String, Integer> entry : ordemCampos.entrySet()) {
			if (ordemCampos.entrySet().size() == i) {
				posicaoColunaSalaHorario = entry.getValue();
			}
			i++;
		}
		return posicaoColunaSalaHorario;
	}

	/**
	 * Obtem os cabecalhos associados a este horario.
	 *
	 * @return Uma lisata com os cabecalhos.
	 */
	public List<String> getColumnTitles() {
		return columnTitles;
	}

	/**
	 * Obtem o horario.
	 *
	 * @return O horario como uma lista de listas de strings.
	 */
	public List<List<String>> getHorario() {
		return horario;
	}

	/**
	 * Obtem o endereco do ficheiro csv do horario
	 *
	 * @return O horario como uma lista de listas de strings.
	 */
	public String getHorarioFilePath() {
		return horarioFilePath;
	}

	/**
	 * Obtem as salas.
	 *
	 * @return As salas.
	 */
	public Salas getSalas() {
		return salas;
	}

	/**
	 * Define as metricas e salva o estado.
	 *
	 * @param metricas As metricas a serem definidas.
	 */
	public void setMetricas(Map<Metrica, Integer> metricas) {
		this.metricas = metricas;
		SaveState.guardaMetricas(metricas);
	}

	/**
	 * Obtem as metricas associadas a este horario.
	 *
	 * @return As metricas.
	 */
	public Map<Metrica, Integer> getMetricas() {
		return metricas;
	}

	/**
	 * Define a ordem dos campos e salva o estado.
	 *
	 * @param ordemCampos A ordem dos campos a ser definida.
	 */
	public void setOrdemCampos(Map<String, Integer> ordemCampos) {
		this.ordemCampos = ordemCampos;
		SaveState.guardarHorario(getHorarioFilePath(), ordemCampos);
	}

	/**
	 * Obtem a ordem dos campos associados a este horario.
	 *
	 * @return A ordem dos campos.
	 */
	public Map<String, Integer> getOrdemCampos() {
		return ordemCampos;
	}

}
