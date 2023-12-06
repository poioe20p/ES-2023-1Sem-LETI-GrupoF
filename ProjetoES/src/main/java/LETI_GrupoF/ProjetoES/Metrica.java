package LETI_GrupoF.ProjetoES;

import java.util.List;

public class Metrica {

	private String formula;
	private List<String> componentesFormula;
	private List<List<String>> aulasComComtribuicao;

	public Metrica(String formula) {
		this.formula = formula;
		componentesFormula = List.of(formula.split(";"));
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
	
}
