package LETI_GrupoF.ProjetoES.user_interface;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Esta class dá interatividade ao utilizador atraves da pagina
public class UserInteraction {

    private UserForm userForm;
    static final private String filePath = "Horario.html";

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
            openSchedule(filePath);
        });

    }


    //Este método abre a pagina HTML com a tabela no browser
    void openSchedule(String filePath) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(filePath);
            if (file.exists()) {
                try {
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

    public void generateHtmlPage(String filePath, String[][] data) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            //Vai buscar o template da pagina HTML
            cfg.setClassForTemplateLoading(this.getClass(), "ProjetoES/");
            Template template = cfg.getTemplate("TemplateHorario.html");

            // Cria ou reescreve a pagina HTML "Horario.html"
            File outputHTML = new File(filePath);
            FileWriter rewriteHTML = new FileWriter(outputHTML);

            // Adiciona os dados à pagina HTML
            template.process(data, rewriteHTML);

            rewriteHTML.close();

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }


}
