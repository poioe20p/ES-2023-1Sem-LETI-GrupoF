package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A classe Horario representa um horario especifico para uma determinada turma.
 */
public class Horario {

	static final private String csvFilePath = "ProjetoES/CaracterizaçãoDasSalas.csv";
	private String turma;
	private List<List<String>> horario;
	private final List<String> columnTitles;
	private Salas salas;

	private Map<Metrica, Integer> metricas;

	/**
	 * Construtor da classe Schedule.
	 *
	 * @param turma, turma para a qual o horario esta sendo gerado.
//	 * @param table, Reader de tabela que fornece os dados para o horario.
	 */

	public Horario(String turma, int indiceTurma, String horarioFilePath) {
		this.turma = turma;

		Reader infoHorarioDoCSV = new Reader(horarioFilePath);
		columnTitles = infoHorarioDoCSV.getColumnTitles();
		for (List<String> linha : infoHorarioDoCSV.getTableData()) {
			if (existeTurma(linha.get(indiceTurma), turma)) {
				this.horario.add(linha);
			}
		}

		salas = new Salas(csvFilePath);
	}

	/**
	 * Construtor da classe Schedule.
	 *
//	 * @param table, Reader de tabela que fornece os dados para o horario.
	 */

	public Horario(String horarioFilePath) {
		Reader dataHorarioDoCSV = new Reader(horarioFilePath);
		columnTitles = dataHorarioDoCSV.getColumnTitles();
		horario = dataHorarioDoCSV.getTableData();

		salas = new Salas(csvFilePath);
	}

	/**
	 * Verifica se uma determinada turma existe em uma linha especifica da tabela.
	 *
	 * @param linhaTurma A string que contem mais que uma turma ou nao de uma dada
	 *                   linha.
	 * @param turma      A turma a ser verificada.
	 * @return TRUE Se a turma existir na linha, FALSE caso contrario.
	 */

	private boolean existeTurma(String linhaTurma, String turma) {
		String[] turmas = linhaTurma.split(",");
		for (int i = 0; i != turmas.length; i++) {
			if (turmas[i].trim().equals(turma))
				return true;
		}
		return false;
	}

	public void adicionarMetrica(Metrica metrica) {
		metricas.put(metrica, calcularQualidade(metrica));
	}
	
	private int calcularQualidade(Metrica metrica) {
		List<String> formula = metrica.getComponentesFormula();   //Vai buscar os componetes da formula para o calculo da metrica
		List<String> nomeSalas = salas.getNomeSalas();   //Vai busacar uma lista com todos os nomes das salas
		List<String> atributo1 = defineAtributo(formula.get(0));
		List<String> atributo2 = defineAtributo(formula.get(2));
		List<Integer> contaInicial = new ArrayList<>();
		for(int i = 0; i < atributo1.size(); i++) {
			switch(formula.get(1)) {
			case "*":
				contaInicial.add(Integer.parseInt(atributo1.get(i)) * Integer.parseInt(atributo2.get(i)));
				break;
			case "/":
				contaInicial.add(Integer.parseInt(atributo1.get(i)) / Integer.parseInt(atributo2.get(i)));
				break;
			case "+":
				contaInicial.add(Integer.parseInt(atributo1.get(i)) + Integer.parseInt(atributo2.get(i)));
				break;
			case "-":
				contaInicial.add(Integer.parseInt(atributo1.get(i)) - Integer.parseInt(atributo2.get(i)));
				break;
	//		case "=":
	//			break;
			}
		}
		int count = 0;
		for(int i = 0; i < contaInicial.size(); i++) {
			switch(formula.get(3)) {
			case "<":
				if(contaInicial.get(i) < Integer.parseInt(formula.get(4))) {
					count++;
				}
				break;
			case ">":
				if(contaInicial.get(i) > Integer.parseInt(formula.get(4))) {
					count++;
				}
				break;
			}
		}
		return count;
	}

	/**
	 * Devolve uma lista da informacao de um certo atributo/coluna, independentemeste do ficheiro em que se localiza
	 *
//	 * @param coluna String da coluna que se quer obter informacao.
//	 * @return Lista de strings representando os dados da coluna.
	 */
	private List<String> defineAtributo(String nomeAtributo) {
		List<String> atributo = new ArrayList<>();
		if(getColumnTitles().contains(nomeAtributo)) {   //Verifica se o atributo (que é um campo/coluna de um dos ficheiros(horario ou salas)) pertence ao horario
			atributo = getHorario().get(getColumnTitles().indexOf(nomeAtributo));   //Vai busacar uma lista com todo o conteudo do atributo
		}else if(getSalas().getColumnTitles().contains(nomeAtributo)){   //Verifica se o atributo pertence as salas
			atributo = getHorario().get(getSalas().getColumnTitles().indexOf(nomeAtributo));   //Vai busacar uma lista com todo o conteudo do atributo
		}
		return atributo;
	}
	
//	int valorMetricaUser(Metrica metrica) {
//		List<String> formula = metrica.getComponentesFormula();
//		int index1 = 0;
//		int index2 = 0;
//		int counter=0;
//		String arg1 = formula.get(0);
//		String arg2 = formula.get(0);
//
//		if (isInColumnTitles(arg1)) {
//			index1 = columnTitles.indexOf(arg1);
//		} else if (arg1.equals("Capacidade Exame")) {
//
//		} else if (arg1.equals("Capacidade Exame")) {
//
//		}
//
//		switch (formula.get(1)) {
//		case "*":
//			break;
//		case "=":
//		case "+":
//		case "-":
//		case "/":
//		default:
//		}
//return counter;
//	}
//
//	int AulasSobrelotadas(HtmlCreator htmlCreator) {
//		int counter = 0;
//		int indexinscritos = htmlCreator.tiltesPosition().get(4);
//		int indexSala = htmlCreator.tiltesPosition().get(10);
//		for (List<String> linha : horario) {
//			for (Sala sala : salas.getSalas()) {
//				if (sala.getNome().equals(linha.get(indexSala))) {
//					if (sala.getCapacidadeNormal() < Integer.parseInt(linha.get(indexinscritos))) {
//						counter++;
//					}
//				}
//			}
//		}
//		return counter;
//	}

	/**
	 * Obtem o nome da turma associada a este horario.
	 *
	 * @return O nome da turma.
	 */
	public String getTurma() {
		return turma;
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

//	public Reader getInfoHorarioDoCSV() {
//		return infoHorarioDoCSV;
//	}

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
