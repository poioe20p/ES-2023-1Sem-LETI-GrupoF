package LETI_GrupoF.ProjetoES;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe Horario representa um horario especifico para uma determinada turma.
 */
public class Horario {

	static final private String csvFilePath = "CaracterizaçãoDasSalas.csv";
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
		List<String> formula = metrica.getComponentesFormula(); // Vai buscar os componetes da formula para o calculo da metrica
		List<String> atributo1 = defineAtributo(formula.get(0));
		List<String> atributo2 = defineAtributo(formula.get(2));
		List<String> contaInicial = new ArrayList<>();
		if (atributo1.get(0).equals("horario") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialHorario(atributo1, atributo2, formula.get(1));
		} else if (atributo1.get(0).equals("sala") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialHorarioSala(atributo2, atributo1, formula.get(1));
		} else if (atributo1.get(0).equals("sala") && atributo2.get(0).equals("horario")) {
			contaInicial = calculoParcialSalaHorario(atributo1, atributo2, formula.get(1));
		}
		if (formula.size() > 2) {
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
				if(Integer.parseInt(contaInicial.get(i)) != 0){
					sum += Integer.parseInt(contaInicial.get(i));
					metrica.adicionarAula(getHorario().get(i));
				}
			}
			return sum;
		}
	}

	/**
	 * Devolve uma lista da informacao de um certo atributo/coluna, independentemente do ficheiro em que se localiza
	 *
	 * @param coluna String da coluna que se quer obter informacao.
	 * @return Lista de strings representando os dados da coluna.
	 */
	private List<String> defineAtributo(String nomeAtributo) {
		List<String> atributo = new ArrayList<>();
		if (getColumnTitles().contains(nomeAtributo)) { // Verifica se o atributo (que é um campo/coluna de um dos ficheiros(horario ou salas)) pertence ao horario
			atributo.add("horario");
			for (int i = 0; i < getHorario().size(); i++) {
				atributo.add(getHorario().get(i).get(ordemCampos.get(nomeAtributo))); // Vai busacar uma lista com todo o conteudo do atributo
			}
		} else if (getSalas().getColumnTitles().contains(nomeAtributo)) { // Verifica se o atributo pertence as salas
			atributo.add("sala");
			for (int i = 0; i < getSalas().getListaSalas().size(); i++) {
				atributo.add(getSalas().getListaSalas().get(i).getCampo(getSalas().getColumnTitles().indexOf(nomeAtributo))); // Vai busacar uma lista com todo o conteudo do atributo
			}
		}
		return atributo;
	}

	private List<String> calculoParcialHorario(List<String> atributoHoraio1, List<String> atributoHoraio2, String operador) {
		List<String> contaInicial = new ArrayList<>();
		if(isListaDeSalas(atributoHoraio1)) {
			contaInicial = comparaAtributos(atributoHoraio2, atributoHoraio1, operador);
		}else {
			contaInicial = comparaAtributos(atributoHoraio1, atributoHoraio2, operador);
		}
		return contaInicial;
	}

	private boolean isListaDeSalas(List<String> atributo) {
		if(getSalas().getNomeSalas().contains(atributo.get(1))) {
			return true;
		}
		return false;
	}

	private List<String> comparaAtributos(List<String> listaRequesitosSalas, List<String> salaAtribuida, String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 1; i < listaRequesitosSalas.size(); i++) {
			int linhaSalaFicheiroCSV = getSalas().getNomeSalas().indexOf(salaAtribuida.get(i));
			if(!getHorario().get(i).get(posicaoColunaSalaHorario()).equals("N/A")) {
				List<String> caracteristicasSala = getSalas().getListaSalas().get(linhaSalaFicheiroCSV).getCaracteristicasSala();
				switch (operador) {
				case "-":
					caracteristicasSala.removeAll(listaRequesitosSalas);
					contaInicial.add(String.valueOf(caracteristicasSala.size()));
					break;
				case "=":
					if(caracteristicasSala.contains(listaRequesitosSalas.get(i))) {
						contaInicial.add("true");
					}else {
						contaInicial.add("false");
					}
					break;
				case "!=":
					if(caracteristicasSala.contains(listaRequesitosSalas.get(i))) {
						contaInicial.add("false");
					}else {
						contaInicial.add("true");
					}
					break;
				}
			}
		}
		return contaInicial;
	}

	private List<String> calculoParcialHorarioSala(List<String> atributoHoraio, List<String> atributoSala, String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 1; i < atributoHoraio.size(); i++) {
			if(!getHorario().get(i).get(posicaoColunaSalaHorario()).equals("N/A")) {
				switch (operador) {
				case "*":
					contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i))	* Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
					break;
				case "/":
					contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i))	/ Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
					break;
				case "+":
					contaInicial.add(String.valueOf(Integer.parseInt(atributoHoraio.get(i)) + Integer.parseInt(atributoSala.get(indexSalaAula(i)))));
					break;
				case "-":
					int resultado = Integer.parseInt(atributoHoraio.get(i))	- Integer.parseInt(atributoSala.get(indexSalaAula(i)));
					if(resultado > 0) {
						contaInicial.add(String.valueOf(resultado)); //Ha sobrelotacao
					}else {
						contaInicial.add("0"); //Nao ha sobrelotacao
					}
					break;
				}
			}
		}
		return contaInicial;
	}

	private List<String> calculoParcialSalaHorario(List<String> atributoSala, List<String> atributoHoraio, String operador) {
		List<String> contaInicial = new ArrayList<>();
		for (int i = 1; i < atributoHoraio.size(); i++) {
			if(!getHorario().get(i).get(posicaoColunaSalaHorario()).equals("N/A")) {
				switch (operador) {
				case "*":
					contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i))) * Integer.parseInt(atributoHoraio.get(i))));
					break;
				case "/":
					contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i))) / Integer.parseInt(atributoHoraio.get(i))));
					break;
				case "+":
					contaInicial.add(String.valueOf(Integer.parseInt(atributoSala.get(indexSalaAula(i))) + Integer.parseInt(atributoHoraio.get(i))));
					break;
				case "-":
					int resultado = Integer.parseInt(atributoSala.get(indexSalaAula(i))) - Integer.parseInt(atributoHoraio.get(i));
					if(resultado > 0) {
						contaInicial.add(String.valueOf(resultado)); //Ha sobrelotacao
					}else {
						contaInicial.add("0"); //Nao ha sobrelotacao
					}
					break;
				}
			}
		}
		return contaInicial;
	}

	private int indexSalaAula(int indexAula) {
		int posicaoColunaSalaHorario = posicaoColunaSalaHorario();
		if(getSalas().getNomeSalas().indexOf(getHorario().get(indexAula).get(posicaoColunaSalaHorario)) == -1) {
			System.out.println(getSalas().getNomeSalas().get(24));
			System.out.println(getHorario().get(indexAula).get(posicaoColunaSalaHorario));
		}
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

}
