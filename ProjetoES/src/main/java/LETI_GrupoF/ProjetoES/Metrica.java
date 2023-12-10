package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Metrica representa uma metrica usada para avaliar horarios.
 */
public class Metrica {

	private final String formula;
	private final List<String> componentesFormula;
	private final List<List<String>> aulasComContribuicao;

	/**
	 * Constroi um objeto Metrica com a formula fornecida.
	 *
	 * @param formula A formula usada para a metrica.
	 */
	public Metrica(String formula) {
		this.formula = formula;
		componentesFormula = List.of(formula.split(";"));
		aulasComContribuicao = new ArrayList<>();
	}

	/**
	 * Obtem a formula da metrica.
	 *
	 * @return A formula como uma string com espacos em vez de ponto e virgula.
	 */
	public String getFormula() {
		return formula.replace(";", " ");
	}

	/**
	 * Obtem os componentes da formula.
	 *
	 * @return Os componentes da formula como uma lista de strings.
	 */
	public List<String> getComponentesFormula() {
		return componentesFormula;
	}

	/**
	 * Adiciona uma aula a lista de aulas com contribuicao para a metrica.
	 *
	 * @param aula A aula a ser adicionada.
	 */
	public void adicionarAula(List<String> aula) {
		aulasComContribuicao.add(aula);
	}

	/**
	 * Obtem a lista de aulas com contribuicao para a metrica.
	 *
	 * @return A lista de aulas com contribuicao como uma lista de listas de
	 *         strings.
	 */
	public List<List<String>> getAulasComComtribuicao() {
		return aulasComContribuicao;
	}

    /**
     * Obtem o total de aulas com contribuicao para a metrica.
     *
     * @return O total de aulas com contribuicao.
     */
	public int getTotal() {
		return aulasComContribuicao != null ? aulasComContribuicao.size() : 0;
	}
	/**
     * Verifica se dois objetos Metrica sao iguais com base nas suas formulas.
     *
     * @param obj O objeto a ser comparado.
     * @return Verdadeiro se os objetos forem iguais, falso caso contrario.
     */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
		return this.getFormula().equals(((Metrica) obj).getFormula());
	}
}
