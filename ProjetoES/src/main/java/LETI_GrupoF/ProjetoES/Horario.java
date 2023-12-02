package LETI_GrupoF.ProjetoES;

import java.util.List;
import java.util.Map;

/**
 * A classe Horario representa um horario especifico para uma determinada turma.
 */
public class Horario {

	static final private String csvFilePath = "CaracterizaçãoDasSalas.csv";
	private String turma;
	private List<List<String>> horario;
	private final List<String> columnTitles;
	private Salas salas;

	private Map<Metrica, Integer> metricas;

	/**
	 * Construtor da classe Schedule.
	 *
	 * @param turma, turma para a qual o horario esta sendo gerado.
	 * @param table, Reader de tabela que fornece os dados para o horario.
	 */

	public Horario(String turma, int indiceTurma, String horarioFilePath) {
		this.turma = turma;

		Reader dataHorarioDoCSV = new Reader(horarioFilePath);
		columnTitles = dataHorarioDoCSV.getColumnTitles();
		for (List<String> linha : dataHorarioDoCSV.getTableData()) {
			if (existeTurma(linha.get(indiceTurma), turma)) {
				this.horario.add(linha);
			}
		}

		salas = new Salas(csvFilePath);
	}

	/**
	 * Construtor da classe Schedule.
	 *
	 * @param table, Reader de tabela que fornece os dados para o horario.
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

//	public void calcularQualidade(Metrica metrica) {
//		List<String> formulas = metrica.getComponentesFormula();
//		for(int i = 0; i < formulas.size(); i++) {
//			
//		}
//		int resultado =
//		metricas.put(metrica, resultado);
//	}

//int calculoCapacidadeE

	boolean isInColumnTitles(String titulo) {
		if (columnTitles.contains(titulo))
			return true;
		return false;

	}

	int valorMetricaUser(Metrica metrica) {
		List<String> formulas = metrica.getComponentesFormula();
		int index1 = 0;
		int index2 = 0;
		int counter=0;
		String arg1 = formulas.get(0);
		String arg2 = formulas.get(0);

		if (isInColumnTitles(arg1)) {
			index1 = columnTitles.indexOf(arg1);
		} else if (arg1.equals("Capacidade Exame")) {

		} else if (arg1.equals("Capacidade Exame")) {

		}

		switch (formulas.get(1)) {
		case "*":
			break;
		case "=":
		case "+":
		case "-":
		case "/":
		default:
		}
return counter;
	}

	int AulasSobrelotadas(HtmlCreator htmlCreator) {
		int counter = 0;
		int indexinscritos = htmlCreator.tiltesPosition().get(4);
		int indexSala = htmlCreator.tiltesPosition().get(10);
		for (List<String> linha : horario) {
			for (Sala sala : salas.getSalas()) {
				if (sala.getNome().equals(linha.get(indexSala))) {
					if (sala.getCapacidadeNormal() < Integer.parseInt(linha.get(indexinscritos))) {
						counter++;
					}
				}
			}
		}
		return counter;
	}

//		int counter=0;
//		int i=0;
//		List<String> formulas = metrica.getComponentesFormula();
//		for(String title:columnTitles) {
//			
//		if(formulas.get(0).equals(title)) {
//			index=i;
//		}
//			i++;
//		}
//		int value1=
//		int value2=
//		int value3=
//		if(formulas.get(i))
//	    
//		
//	
//		metricas.put(metrica, counter);
//			
//		

	

//		int posicaoSala = 0;
//		int posicaoInscritos = 0;
//		int posicaoCaracteristicas = 0;
//		for (int i = 0; i != horario.size(); i++) {
//			nAulasSemSala(horario.get(i).get(posicaoSala));
//			for (int j = 0; j != salas.getData().size(); j++) {
//
//				if (horario.get(i).get(posicaoSala).equals(salas.getData().get(j).getNome())) {
//					nAulasSobrelotacao(Integer.parseInt(horario.get(i).get(posicaoInscritos)),
//							salas.getData().get(j).getCapacidadeN());
//					nSalasMalAtribuidas(salas.getData().get(j).getTipo(), horario.get(i).get(posicaoCaracteristicas));
//					nCaracteristicasDesperdicadas(salas.getData().get(j).getTipo(),
//							horario.get(i).get(posicaoCaracteristicas), salas.getData().get(j).getNCaracteristicas());
//
//				}
//
//			}
//		}
//	}
//
//	void nAulasSobrelotacao(int a, int b) {
//		if (a - b > 0) {
//			aulasSobrelotacao++;
//			nEstudantesSobrelotacao(a, b);
//		}
//	}
//
//	void nEstudantesSobrelotacao(int a, int b) {
//		estudantesSobrelotacao += a - b;
//	}
//
//	void nSalasMalAtribuidas(List<String> a, String b) {
//		if (!(a.contains(b))) {
//			numSalasMalAtribuidas++;
//		}
//	}
//
//	void nCaracteristicasDesperdicadas(List<String> a, String b, int c) {
//		if (a.contains(b)) {
//			numCaracteristicasDesperdicadas += c - 1;
//		} else {
//			numCaracteristicasDesperdicadas += c;
//		}
//	}
//
//	void nAulasSemSala(String sala) {
//		if (sala.equals("N/A")) {
//			numAulasSemSala++;
//		}
//
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
