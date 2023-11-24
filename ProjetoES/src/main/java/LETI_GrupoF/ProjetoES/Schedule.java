package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private String turma;
	private Reader table;
	private List<List<String>> horario = new ArrayList<>();;

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

	private boolean existeTurma(String linhaTurma, String turma) {
		String[] turmas = linhaTurma.split(",");
		for (int i = 0; i != turmas.length; i++) {
			if (turmas[i].trim().equals(turma))
				return true;
		}
		return false;
	}

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

	boolean existsTurma(String linha) {
		if (linha.equals(this.turma)) {

			return true;
		}
		return false;

	}

	public String getTurma() {
		return turma;
	}

	public List<List<String>> getHorario() {
		return horario;
	}

	public static void main(String[] args) {
		Reader reader = new Reader("HorarioParaTestes.csv");

		Schedule horario = new Schedule("ET-A10", reader);

		for (List<String> linha : horario.getHorario()) {
			System.out.println(linha);

		}

	}

}
