package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe Horario representa um horario especifico para uma determinada turma.
 */
public class Horario {

	static final private String csvFilePath = "ProjetoES/CaracterizaçãoDasSalas.csv";
	private List<List<String>> horario;
	private List<String> columnTitles;
	private Map<String, Integer> ordemCampos;
	private Salas salas;
	private SaveState ss = new SaveState();
	private Map<Metrica, Integer> metricas = new LinkedHashMap<>();

	/**
	 * Construtor da classe Schedule.
	 *
	 * // * @param table, Reader de tabela que fornece os dados para o horario.
	 */

	public Horario(String horarioFilePath) {
		Reader dataHorarioDoCSV = new Reader(horarioFilePath);
		columnTitles = dataHorarioDoCSV.getColumnTitles();
		horario = dataHorarioDoCSV.getTableData();
		//ss.guardarHorario(csvFilePath, ordemCampos); //Ta comentado porque se neste momento ordemCampos ainda nao foi inicializado
		//Faz mais sentido tar no metodo setOrdemCampos
		salas = new Salas(csvFilePath);
	}

	public void adicionarMetrica(Metrica metrica) {
		ss.guardarMetricas(metrica);
		metricas.put(metrica, calcularQualidade(metrica));
	}

	private int calcularQualidade(Metrica metrica) {
		List<String> formula = metrica.getComponentesFormula(); // Vai buscar os componetes da formula para o calculo da
																// metrica
		List<String> atributo1 = defineAtributo(formula.get(0));
		List<String> atributo2 = defineAtributo(formula.get(2));
		List<String> contaInicial = new ArrayList<>();
		if (atributo1.get(0).equals("horario") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialHorario(atributo1, atributo2, formula.get(1));
		} else if (atributo1.get(0).equals("sala") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialHorarioSala(atributo2, atributo1, formula.get(1));
		} else if (atributo1.get(0).equals("horario") && atributo2.get(0).equals("sala")) {
			contaInicial = calculoParcialSalaHorario(atributo1, atributo2, formula.get(1));
		}
		if (formula.get(3) != null) {
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
//				case "=":
//					break;
				}
			}
			return count;
		} else if (formula.get(1).equals("=") || formula.get(1).equals("!=")) {
			int count = 0;
			for (int i = 0; i < contaInicial.size(); i++) {
				if (contaInicial.get(i).equals("true")) {
					count++;
				}
			}
			return count;
		} else {
			int sum = 0;
			for (int i = 0; i < contaInicial.size(); i++) {
				sum += Integer.parseInt(contaInicial.get(i));
			}
			return sum;
		}
	}

	/**
	 * Devolve uma lista da informacao de um certo atributo/coluna,
	 * independentemente do ficheiro em que se localiza
	 *
	 * // * @param coluna String da coluna que se quer obter informacao. //
	 * * @return Lista de strings representando os dados da coluna.
	 */
	private List<String> defineAtributo(String nomeAtributo) {
		List<String> atributo = new ArrayList<>();
		if (getColumnTitles().contains(nomeAtributo)) { // Verifica se o atributo (que é um campo/coluna de um dos
														// ficheiros(horario ou salas)) pertence ao horario
			atributo.add("horario");
			for (int i = 0; i < getHorario().size(); i++) {
				atributo.add(getHorario().get(i).get(ordemCampos.get(nomeAtributo))); // Vai busacar uma lista com todo
																						// o conteudo do atributo
			}
		} else if (getSalas().getColumnTitles().contains(nomeAtributo)) { // Verifica se o atributo pertence as salas
			atributo.add("sala");
			for (int i = 0; i < getHorario().size(); i++) {
				atributo.add(getSalas().getSalas().get(i).getCampo(getSalas().getColumnTitles().indexOf(nomeAtributo))); // Vai
																															// busacar
																															// uma
																															// lista
																															// com
																															// todo
																															// o
																															// conteudo
																															// do
																															// atributo
			}
		}
		return atributo;
	}

	private List<String> calculoParcialHorario(List<String> atributoHoraio1, List<String> atributoHoraio2,
			String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 1; i < atributoHoraio1.size(); i++) {
			switch (operador) {
//			case "-":
//				contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio1.get(i)) - Integer.parseInt(atributoHoraio2.get(i))));
//				break;
//			case "=":
//				break;
//			case "!=":
//				break;
			}
		}
		return contaInicial;
	}

	private List<String> calculoParcialHorarioSala(List<String> atributoHoraio, List<String> atributoSala,
			String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 1; i < atributoHoraio.size(); i++) {
			switch (operador) {
			case "*":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i))
						* Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
				break;
			case "/":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i))
						/ Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
				break;
			case "+":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i))
						+ Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
				break;
			case "-":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i))
						- Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
				break;
			}
		}
		return contaInicial;
	}

	private List<String> calculoParcialSalaHorario(List<String> atributoSala, List<String> atributoHoraio,
			String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 1; i < atributoHoraio.size(); i++) {
			switch (operador) {
			case "*":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i)))
						* Integer.parseInt(atributoHoraio.get(i))));
				break;
			case "/":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i)))
						/ Integer.parseInt(atributoHoraio.get(i))));
				break;
			case "+":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i)))
						+ Integer.parseInt(atributoHoraio.get(i))));
				break;
			case "-":
				contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i)))
						- Integer.parseInt(atributoHoraio.get(i))));
				break;
			}
		}
		return contaInicial;
	}

	private int indexSalaAula(int index) {
		int indexSalaHorario = 0;
		int i = 1;
		for (Map.Entry<String, Integer> entry : ordemCampos.entrySet()) {
			if (ordemCampos.entrySet().size() == i) {
				indexSalaHorario = entry.getValue();
			}
			i++;
		}
		return getSalas().getNomeSalas().indexOf(getHorario().get(index).get(indexSalaHorario));
	}

	/**
	 * Obtem os cabecalhos associados a este horario.
	 *
	 * @return O cabecalho.
	 */
	public List<String> getColumnTitles() {
		return columnTitles;
	}

	/**
	 * Obtem o horario associado a esta turma.
	 *
	 * @return O horario como uma lista de listas de strings.
	 */
	public List<List<String>> getHorario() {
		return horario;
	}

	/**
	 * Obtem o horario associado a esta turma.
	 *
	 * @return O horario como uma lista de listas de strings.
	 */
	public Salas getSalas() {
		return salas;
	}

	public Map<Metrica, Integer> getMetricas() {
		return metricas;
	}

	public void setOrdemCampos(Map<String, Integer> ordemCampos) {
		this.ordemCampos = ordemCampos;
		ss.guardarHorario(csvFilePath, ordemCampos);
	}

	/**
	 * Metodo main para testar a classe Schedule.
	 *
	 * @param args Os argumentos da linha de comando (não sao usados neste caso).
	 */
//	public static void main(String[] args) {
//		Reader reader = new Reader("HorarioParaTestes.csv");
//
//		Schedule horario = new Schedule("ET-A10", reader);
//
//		for (List<String> linha : horario.getHorario()) {
//			System.out.println(linha);
//
//		}
//
//	}

}
