package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Schedule representa um horario especifico para uma determinada
 * turma.
 */
public class Schedule {
	private String turma;
	private Reader table;
	private List<List<String>> horario = new ArrayList<>();;

	/**
	 * Construtor da classe Schedule.
	 *
	 * @param turma, turma para a qual o horario esta sendo gerado.
	 * @param table, Reader de tabela que fornece os dados para o horario.
	 */

	public Schedule(String turma, Reader table) {
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
	 * Obtém o índice da coluna "Turma" na tabela.
	 *
	 * @return O índice da coluna "Turma".
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

	/**
	 * Verifica se uma turma específica existe no horário.
	 *
	 * @param linha A string que contem informações sobre a turma.
	 * @return true se a turma existir no horário, false caso contrário.
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

	/**
	 * Metodo main para testar a classe Schedule.
	 *
	 * @param args Os argumentos da linha de comando (não sao usados neste caso).
	 */
	public static void main(String[] args) {
		Reader reader = new Reader("HorarioParaTestes.csv");

		Schedule horario = new Schedule("ET-A10", reader);

		for (List<String> linha : horario.getHorario()) {
			System.out.println(linha);

		}

	}

}
