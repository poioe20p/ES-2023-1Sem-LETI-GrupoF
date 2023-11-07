package LETI_GrupoF.ProjetoES.user_interface;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import javax.swing.*;

import LETI_GrupoF.ProjetoES.Reader;


import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Esta class dá interatividade a pagina acedida pelo uitlizador
public class UserInteraction {

    private UserForm userForm;
    static final private String pageFilePath = "ProjetoES/Horario.html";
    static final private String dataFilePath = "ProjetoEs/HorarioDeExemplo.csv";
    static final private String turma = "ET-A7";

    //Para fazer correr o programa


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInteraction userInteraction = new UserInteraction();
        });
    }

    //Construtor para inicializar a pagina da GUI
    public UserInteraction() {
        userForm = new UserForm();
        userForm.setVisible(true);

        userForm.setSubmitFileButtonActionListener(e -> {
            openSchedule(pageFilePath);
        });

    }


    //Este método abre a pagina HTML com a tabela no browser
    void openSchedule(String pageFilePath) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(pageFilePath);
            if (file.exists()) {
                try {
                    Reader horario = new Reader(dataFilePath, turma);
                    if(generateHtmlPage(horario.getTableData(), horario.getColumnTitles())){
                        System.out.println("HTML page generated successfully");
                        desktop.browse(file.toURI());
                    } else {
                        System.out.println("Error generating HTML page");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(userForm, "File does not exist: " + file, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(userForm, "Desktop is not supported on this platform", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public boolean generateHtmlPage(List<List<String>> dataForHtml, List<String> columnTitles) {
        boolean success = true;

        // Create a new HTML document
        Document doc = Document.createShell("");

        // Set the title of the HTML page
        doc.title("Horário");

        // Create the head element and add it to the document
        Element head = doc.appendElement("head");

        // Add the Tabulator CSS file
        head.appendElement("link")
                .attr("rel", "stylesheet")
                .attr("href", "https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css");

        // Create the body element and add it to the document
        Element body = doc.appendElement("body");

        // Create a div element for the table and add it to the body
        Element tableDiv = body.appendElement("div")
                .attr("id", "horario");

        // Create the script element for the Tabulator library and add it to the body
        body.appendElement("script")
                .attr("src", "https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js");

        String javascriptCode = """
                var tableData = [{ cursoL: "ME", turnoL: 'B', ucL: 'Projeto de Engenharia de Software', turmaL: 'ET-A7', inscritosL: '20', diaL: 'Segunda-feira', horaInicioL: '14:00', horaFimL: '16:00', dataL: '2021-05-03', caracteristicasL: 'Laboratório', salaL: 'B.1.01' },
                { cursoL: 'ME', turnoL: 'B', ucL: 'Projeto de Engenharia de Software', turmaL: 'ET-A2', inscritosL: '20', diaL: 'Quarta-feira', horaInicioL: '14:00', horaFimL: '16:00', dataL: '2021-05-05', caracteristicasL: 'Laboratório', salaL: 'B.1.01' },
                { cursoL: 'ME', turnoL: 'B', ucL: 'Projeto de Engenharia de Software', turmaL: 'ET-A7', inscritosL: '20', diaL: 'Sexta-feira', horaInicioL: '14:00', horaFimL: '16:00', dataL: '2021-05-07', caracteristicasL: 'Laboratório', salaL: 'B.1.01'}];
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
                            {title: 'Sala atribuída', field: 'salaL', headerFilter:'input'},],});;
                		""";

        // Add the JavaScript code to the body as a script element
        body.appendElement("script")
                .attr("type", "text/javascript")
                .text(javascriptCode);

        try {

            // Generate the HTML content as a string
            String htmlContent = doc.html();

            // Write the HTML content to the file
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

}
