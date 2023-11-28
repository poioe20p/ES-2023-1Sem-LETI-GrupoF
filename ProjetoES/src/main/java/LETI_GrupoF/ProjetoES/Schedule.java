package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;

/**
 * A classe Schedule representa um horario especifico para uma determinada
 * turma.
 */
public class Schedule {
	private String turma;
	private Reader table;
	private List<List<String>> horario = new ArrayList<>();
	private List<Sala> salas = new ArrayList<>();
	private int aulasSobrelotacao;
	private int estudantesSobrelotacao;
	private int numSalasMalAtribuidas;
	private int numAulaSemSala;
	private int nCaracteristicasDesperdicadas;

	/**
	 * Construtor da classe Schedule.
	 *
	 * @param turma, turma para a qual o horario esta sendo gerado.
	 * @param table, Reader de tabela que fornece os dados para o horario.
	 */

	public Schedule(String turma, Reader table, UserInteraction uI) {
		this.turma = turma;
		this.table = table;
		for (List<String> linha : table.getTableData()) {
			// System.out.println(linha.get(3));
			if (existeTurma(linha.get(indiceColunaTurma()), turma)) {

				this.horario.add(linha);

			}

		}

	}

	/**
	 * Verifica se uma determinada turma existe em uma linha especifica da tabela.
	 *
	 * @param linhaTurma A string que contem mais que uma turma ou nao de uma dada
	 *                   linha.
	 * @param turma      A turma a ser verificada.
	 * @return true se a turma existir na linha, false caso contrario.
	 */

	private boolean existeTurma(String linhaTurma, String turma) {
		String[] turmas = linhaTurma.split(",");
		for (int i = 0; i != turmas.length; i++) {
			if (turmas[i].trim().equals(turma))
				return true;
		}
		return false;
	}

	/**
	 * Obtem o indice da coluna "Turma" na tabela.
	 *
	 * @return O indice da coluna "Turma".
	 */

	public int indiceColunaTurma() {
		int columnNumber = 0;
		int i = 0;
		for (String title : table.getColumnTitles()) {
			if (title.equals("Turma")) {
				columnNumber = i;
			}
			i++;
		}
		return columnNumber;
	}

//	public double qualidadeHorario() {
//		int linhasCorretas = 0;
//		for (int i = 0; i != horario.size(); i++) {
//			int posicaoSala = 0;
//			int posicaoInscritos = 0;
//			String salaHorario = horario.get(i).get(posicaoSala);
//			Sala sala = 
//		}
//	}
//			for (int j = 0; j != salas.size(); j++) {
//				if (sala.equals(salas.get(j).getNome())) {
//					numAulasSobrelotacao(Integer.parseInt(horario.get(i).get(posicaoInscritos)),
//							salas.get(j).getCapacidadeN());
//					// numCaracteristicasDesperdicadas(salas.get(j).getNCaracteristicas(),
//					// horario.get(i).)
//				}
//			}
//		}
//		return linhasCorretas / horario.size() - 1;
//	}

	Sala getSala(String sala) {
		Sala s = null;
		for (int i = 0; i != salas.size(); i++) {
			if (sala.equals(salas.get(i).getNome()))
				s = salas.get(i);

		}
		return s;
	}

	void numAulasSobrelotacao(int a, int b) {
		if (a - b > 0) {
			aulasSobrelotacao++;
			nEstudantesSobrelotacao(a, b);
		}
	}

	void nEstudantesSobrelotacao(int a, int b) {
		estudantesSobrelotacao += a - b;
	}

	void numAulasCaracteristicasDesperdicadas(int a, int b) {

	}

	void numCaracteristicasDesperdicadas(int a, int b) {
		nCaracteristicasDesperdicadas += a - b;
	}

	/**
	 * Verifica se uma turma especifica existe no horário.
	 *
	 * @param linha A string que contem informações sobre a turma.
	 * @return true se a turma existir no horario, false caso contrario.
	 */

	boolean existsTurma(String linha) {
		if (linha.equals(this.turma)) {

			return true;
		}
		return false;

	}

	/**
	 * Obtem o nome da turma associada a este horario.
	 *
	 * @return O nome da turma.
	 */
	public String getTurma() {
		return turma;
	}

	/**
	 * Obtem o horario associado a esta turma.
	 *
	 * @return O horario como uma lista de listas de strings.
	 */
	public List<List<String>> getHorario() {
		return horario;
	}

	public int getAulasSobrelotacao() {
		return aulasSobrelotacao;
	}

	public int getEstudantesSobrelotacao() {
		return estudantesSobrelotacao;
	}

	public int getNumSalasMalAtribuidas() {
		return numSalasMalAtribuidas;
	}

	public int getNumAulaSemSala() {
		return numAulaSemSala;
	}

	public int getnCaracteristicasDesperdicadas() {
		return nCaracteristicasDesperdicadas;
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
