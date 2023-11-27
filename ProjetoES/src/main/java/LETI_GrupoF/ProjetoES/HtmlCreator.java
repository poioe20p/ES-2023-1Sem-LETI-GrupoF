package LETI_GrupoF.ProjetoES;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Esta class cria a pagina HTML
public class HtmlCreator {

    static final private String pageFilePath = "ProjetoES/Horario.html";
    private final List<List<String>> dataForHtml;
    private final List<String> columnFields;


    public HtmlCreator(String dataFilePath) {
        Reader dataFromCSV = new Reader(dataFilePath);
        this.dataForHtml = dataFromCSV.getTableData();

        //Gera a lista com os fields usados no tabulator para cada coluna
        this.columnFields = new ArrayList<>(List.of(
                "cursoL: ", "ucL: ", "turnoL: ", "turmaL: ", "inscritosL: ",
                "diaL: ", "horaInicioL: ", "horaFimL: ", "dataL: ", "caracteristicasL: ", "salaL: "));
    }

    //Devolve uma lista com a posição dos valores assoicados a cada titulo na ordem do ficheiro CSV fornecido pelo utilizador
    private List<String> tiltesPosition(List<String> csvHeaderl, List<String> userOrderTitles) {
        List<String> titlesPosition = new ArrayList<>();
        for( String title : userOrderTitles) {
            titlesPosition.add(String.valueOf(csvHeaderl.indexOf(title)));
        }
        return titlesPosition;
    }

    private String formatDataForHtml() {
        StringBuilder jsCode = new StringBuilder();
        jsCode.append("var tableData = [");

        for (List<String> row : dataForHtml) {

            jsCode.append("{ ");
            for (int i = 0; i < columnFields.size(); i++) {
                jsCode.append(columnFields.get(i));
                String s1 = row.get(i).replace("'", "");
                String s = "'" + s1 + "', ";
                jsCode.append(s);
            }

            jsCode.delete(jsCode.length() - 2, jsCode.length());
            jsCode.append(" }, ");

        }
        return jsCode.substring(0, jsCode.length() - 2) + "];";
    }



    //Cria a pagina HTML
    public boolean generateHtmlPage() {
        boolean success = true;

        // Cria um documento HTML
        Document doc = Document.createShell("");

        // Põe o titulo da pagina HTML
        doc.title("Horário");

        // Cria e adiciona o elemento head
        Element head = doc.appendElement("head");

        // Adiciona o link para o ficheiro CSS do tabulator
        head.appendElement("link")
                .attr("rel", "stylesheet")
                .attr("href", "https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css");

        // Cria e adiciona o elemento body
        Element body = doc.appendElement("body");

        // Cria e adiciona o elemento div com o id "horario"
        Element tableDiv = body.appendElement("div")
                .attr("id", "horario");

        // Cria e adiciona o elemento script com o link para o ficheiro JavaScript do tabulator
        body.appendElement("script")
                .attr("src", "https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js");

        // Código JavaScript para criar a tabela e as suas propriedades
        String javascriptTable = """
                var table = new Tabulator('#horario', {
                    	data: tableData,
                    	pagination:"local",
                    	layout: 'fitDatafill',
                    	paginationSize:10,
                    	movableColumns:true,
                        paginationCounter:"rows",
                        paginationSizeSelector:[5, 10, 20, 40],
                        initialSort:[{column:"building",dir:"asc"},],
                        columns: [
                            {title: 'Curso', field: 'cursoL', headerFilter:'input'},
                            {title: 'Unidade Curricular', field: 'ucL', headerFilter:'input'},
                            {title: 'Turno', field: 'turnoL', headerFilter:'input'},
                            {title: 'Turma', field: 'turmaL', headerFilter:'input'},
                            {title: 'Inscritos no turno', field: 'inscritosL', headerFilter:'input'},
                            {title: 'Dia da semana', field: 'diaL', headerFilter:'input'},
                            {title: 'Hora de início da aula' , field: 'horaInicioL', headerFilter:'input'},
                            {title: 'Hora de fim da aula', field: 'horaFimL', headerFilter:'input'},
                            {title: 'Data da aula', field: 'dataL', headerFilter:'input'},
                            {title: 'Características da sala pedida para a aula', field: 'caracteristicasL', headerFilter:'input'},
                            {title: 'Sala atribuída', field: 'salaL', headerFilter:'input'
                            },],});;
                """;

        // Completa o código JavaScript com os dados da tabela
        String javascriptCode = formatDataForHtml() + "\n" + javascriptTable;

        // Adiciona o código JavaScript à página HTML
        body.appendElement("script")
                .attr("type", "text/javascript")
                .text(javascriptCode);
        try {
            //Gera a string que representa o HTML
            String htmlContent = doc.html();

            //Escreve a string no ficheiro HTML
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

    public String getHtmlPath() {
        return pageFilePath;
    }

}
