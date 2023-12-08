package LETI_GrupoF.ProjetoES;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * A classe HtmlCreator e responsavel criar o ficheiro html.
 */
public class HtmlCreator {

	static final private String pageFilePath = "Horario.html";
	private final List<List<String>> dataForHtml;
	private final List<String> columnFields;
	private Horario horario;
	private List<String> userOrderTitles = new ArrayList<>();
	private List<String> columnTitlesForQualitySchedule;

	/**
	 * Construtor da classe HtmlCreator. Inicializa um objeto HtmlCreator com o
	 * caminho do arquivo CSV contendo os dados para a pagina HTML.
	 *
	 * @param userOrderTitles Lista com os titulos das colunas na ordem escolhida pelo utilizador.
	 */

	public HtmlCreator(Horario horario, List<String> userOrderTitles) {
		this.horario = horario;
		dataForHtml = horario.getHorario();
		this.userOrderTitles = userOrderTitles;
		this.columnFields = new ArrayList<>();
		for(String titles: userOrderTitles) {
			String titlesTrimmed = titles.replace(" ", "");
			columnFields.add(titlesTrimmed + "_field");
		}
	}

	public HtmlCreator(List<List<String>> dataForHtml, List<String> userOrderTitles, List<String> columTitles) {
		this.dataForHtml = dataForHtml;
		this.userOrderTitles = userOrderTitles;
		this.columnFields = new ArrayList<>();
		this.columnTitlesForQualitySchedule = columTitles;
		for(String titles: userOrderTitles) {
			String titlesTrimmed = titles.replace(" ", "");
			columnFields.add(titlesTrimmed + "_field");
		}
		this.horario = null;
	}


//	public List<String> getUserOrderTitles() {
//		return userOrderTitles;
//	}

	/**
	 * Devolve uma lista com a posicao dos valores associados a cada titulo na ordem
	 * do arquivo CSV fornecido pelo usuario.
	 *
	 * @return Lista de strings contendo a posicao dos titulos.
	 */
	// Devolve uma lista com a posição dos valores assoicados a cada titulo na ordem
	// do ficheiro CSV fornecido pelo utilizador
	public List<String> tiltesPosition() {
		List<String> titlesPosition = new ArrayList<>();
		for (String title : userOrderTitles) {
			if(horario != null) {
				titlesPosition.add(String.valueOf(horario.getColumnTitles().indexOf(title)));
			} else {
				//Aqui necessito do cabeçalho do ficheiro CSV submetido pelo utilizador.
				titlesPosition.add(String.valueOf(columnTitlesForQualitySchedule.indexOf(title)));
			}
		}
		return titlesPosition;
	}

	/**
	 * Formata os dados para a representacao em HTML.
	 *
	 * @return String contendo o codigo JavaScript com os dados formatados.
	 */
	private String formatDataForHtml() {
		StringBuilder jsCode = new StringBuilder();
		jsCode.append("var tableData = [");
		List<String> titlesPosition = tiltesPosition();

		for (List<String> row : dataForHtml) {

			jsCode.append("{ ");
			for (int i = 0; i < columnFields.size(); i++) {
				jsCode.append(columnFields.get(i) + ": ");
				String columnValue = row.get(Integer.parseInt(titlesPosition.get(i))).replace("'", "");
				String javaScriptFormatValue = "'" + columnValue + "', ";
				jsCode.append(javaScriptFormatValue);
			}

			jsCode.delete(jsCode.length() - 2, jsCode.length());
			jsCode.append(" }, ");

		}
		return jsCode.substring(0, jsCode.length() - 2) + "];";
	}

	/**
	 * Constroi a string com as colunas da tabela.
	 *
	 * @return String contendo o codigo JavaScript com as colunas da tabela.
	 */
	private String buildColumnsForTable() {
		StringBuilder jsCode = new StringBuilder();
		jsCode.append("\t" + "\t").append("columns: [");

		for (int i = 0; i < columnFields.size(); i++) {
			jsCode.append("\t" + "\t").append("{title: '").append(userOrderTitles.get(i)).append("', field: '").append(columnFields.get(i)).append("', headerFilter:'input'},").append("\n");
		}
		jsCode.delete(jsCode.length() - 1, jsCode.length());
		jsCode.append("],");
		return jsCode.toString();
	}

	/**
	 * Gera a pagina HTML com base nos dados fornecidos.
	 *
	 * @return True se a geracao foi bem-sucedida, False caso contrario.
	 */

	public boolean generateHtmlPage() {
		boolean success = true;

		// Cria um documento HTML
		Document doc = Document.createShell("");

		// Põe o titulo da pagina HTML
		doc.title("Horário");

		// Cria e adiciona o elemento head
		Element head = doc.appendElement("head");

		// Adiciona o link para o ficheiro CSS do tabulator
		head.appendElement("link").attr("rel", "stylesheet").attr("href",
				"https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css");

		// Cria e adiciona o elemento body
		Element body = doc.appendElement("body");

		// Cria e adiciona o elemento div com o id "horario"
		Element tableDiv = body.appendElement("div").attr("id", "horario");

		// Cria e adiciona o elemento script com o link para o ficheiro JavaScript do
		// tabulator
		body.appendElement("script").attr("src", "https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js");

		// Código JavaScript para criar a tabela e as suas propriedades
		String javaScriptTable1 =  """
				var table = new Tabulator('#horario', {
				    	data: tableData,
				    	pagination:"local",
				    	layout: 'fitDatafill',
				    	paginationSize:10,
				    	movableColumns:true,
				        paginationCounter:"rows",
				        paginationSizeSelector:[5, 10, 20, 40],
				        initialSort:[{column:"building",dir:"asc"},],
				        """;

		String javaScriptTable2 = javaScriptTable1 + buildColumnsForTable() + "});;";

		// Completa o codigo JavaScript com os dados da tabela
		String javascriptCode = formatDataForHtml() + "\n" + javaScriptTable2;

		// Adiciona o codigo JavaScript à página HTML
		body.appendElement("script").attr("type", "text/javascript").text(javascriptCode);

		try {
			// Gera a string que representa o HTML
			String htmlContent = doc.html();

			// Escreve a string no ficheiro HTML
			FileWriter writer = new FileWriter(pageFilePath);
			writer.write(htmlContent);
			writer.close();

			System.out.println("HTML file written to: " + pageFilePath);
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
		}

		return success;
	}

	/**
	 * Obtem o caminho do arquivo HTML gerado.
	 *
	 * @return O caminho do arquivo HTML.
	 */
	public String getHtmlPath() {
		return pageFilePath;
	}

	public List<List<String>> getSchedule() {
		return dataForHtml;
	}

}
