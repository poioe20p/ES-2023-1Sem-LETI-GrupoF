package LETI_GrupoF.ProjetoES;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sala {

	private String edificio;
	private String nome;
	private int capacidadeN;
	private int capacidadeE;
	private List<String> tipo;
	private List<String> columnTitles;

	Sala(String edificio, String nome, int capacidadeN, int capacidadeE, List<String> tipo) {
		this.edificio = edificio;
		this.nome = nome;
		this.capacidadeN = capacidadeN;
		this.capacidadeE = capacidadeE;
		this.tipo = tipo;
	}

	public List<Sala> lerSalasDoCSV(File csv) {
		List<Sala> data = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(csv);
			columnTitles = readColumnTitles(sc);
			while (sc.hasNextLine()) {
				List<String> linha = new ArrayList<>(List.of(sc.nextLine().split(";")));
				data.add(criarSala(linha));
			}

		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}

		return data;
	}

	public Sala criarSala(List<String> linha) {
		String edificio = linha.get(0);
		String nome = linha.get(1);
		int capacidadeN = Integer.parseInt(linha.get(2));
		int capacidadeE = Integer.parseInt(linha.get(3));
		List<String> tipo = new ArrayList<>();
		for (int i = 4; i != linha.size(); i++) {
			if (linha.get(i).trim() == "X") {
				tipo.add(getColumnTitles().get(i));
			}
		}
		return new Sala(edificio, nome, capacidadeN, capacidadeE, tipo);
	}

	public List<String> readColumnTitles(Scanner sc) throws FileNotFoundException {
		List<String> titles = new ArrayList<>();
		if (sc.hasNextLine()) {
			titles = Arrays.asList(sc.nextLine().split(";"));
		}
		return titles;
	}

	public String getNome() {
		return nome;
	}

	public List<String> getColumnTitles() {
		return columnTitles;
	}

	public int getCapacidadeE() {
		return capacidadeE;
	}

	public int getCapacidadeN() {
		return capacidadeN;
	}

	public String getEdificio() {
		return edificio;
	}

	public List<String> getTipo() {
		return tipo;
	}

}
