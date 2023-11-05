package LETI_GrupoF.ProjetoES.user_interface;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.swing.*;

import LETI_GrupoF.ProjetoES.Reader;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Esta class dá interatividade a pagina acedida pelo uitlizador
public class UserInteraction {

    private UserForm userForm;
    static final private String pageFilePath = "Horario.html";
    static final private String dataFilePath = "HorarioDeExemplo.csv";
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
    void openSchedule(String filePath) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(filePath);
            if (file.exists()) {
                try {
                    Reader horario = new Reader(dataFilePath, turma);
                    generateHtmlPage(filePath, horario.getTableData(), horario.getColumnTitles());
                    desktop.browse(file.toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(userForm, "File does not exist: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(userForm, "Desktop is not supported on this platform", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generateHtmlPage(String filePath, List<List<String>> data, List<String> columnTitles) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            //Vai buscar o template da pagina HTML
            cfg.setClassForTemplateLoading(this.getClass(), "ProjetoES/");
            Template template = cfg.getTemplate("TemplateHorario.html");

            // Cria ou reescreve a pagina HTML "Horario.html"
            File outputHTML = new File(filePath);
            FileWriter rewriteHTML = new FileWriter(outputHTML);

            // Preenche as variaveis do template
            Map<String, Object> root = new HashMap<>();
            root.put("data", data);
            root.put("columnTitles", columnTitles);

            template.process(root, rewriteHTML);

            rewriteHTML.close();

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }


}
