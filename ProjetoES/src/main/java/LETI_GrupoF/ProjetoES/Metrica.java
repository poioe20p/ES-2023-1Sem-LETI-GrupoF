package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

public class Metrica {

	private final String formula;
	private final List<String> componentesFormula;
	private final List<List<String>> aulasComComtribuicao;

	public Metrica(String formula) {
		this.formula = formula;
		componentesFormula = List.of(formula.split(";"));
		aulasComComtribuicao = new ArrayList<>();
	}
	public String getFormula() {
		return formula.replace(";", " ");
	}

	public List<String> getComponentesFormula() {
		return componentesFormula;
	}

	public void adicionarAula(List<String> aula) {
		aulasComComtribuicao.add(aula);
	}

	public List<List<String>> getAulasComComtribuicao() {
		return aulasComComtribuicao;
	}

	public int getTotal() {
		return aulasComComtribuicao != null ? aulasComComtribuicao.size() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getFormula().equals(((Metrica) obj).getFormula());
	}
}
