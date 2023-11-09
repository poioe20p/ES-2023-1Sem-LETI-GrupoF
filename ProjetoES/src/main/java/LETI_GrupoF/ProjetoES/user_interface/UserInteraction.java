package LETI_GrupoF.ProjetoES.user_interface;

import LETI_GrupoF.ProjetoES.HtmlCreator;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;

//Esta class dá interatividade a pagina acedida pelo uitlizador
public class UserInteraction {

    //Pagina da GUI
    private final UserForm userForm;
    private HtmlCreator htmlCreator;

    //Para fazer correr o programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInteraction::new);
    }

    //Construtor para inicializar a pagina da GUI
    public UserInteraction() {
        userForm = new UserForm();
        userForm.setVisible(true);

        //Define o comportamento do boão quando o mesmo é clicado
        userForm.getSubmitFileButton().addActionListener( e -> {
                    //Vai buscar o input do utilizador
                    String input = userForm.getCsvFileLocation().getText();

                    if(input != null && !input.isEmpty()) {

                        //Verifica se o input é um URL ou um caminho para um ficheiro
                        if( input.matches("^https?://.*")) {
                            try {
                                URL remoteFile = new URL(input);
                                if(saveToLocalFile(remoteFile.openStream(), "HorarioRemoto.csv")) {
                                    htmlCreator = new HtmlCreator("HorarioRemoto.csv");
                                    openSchedule();
                                }
                                else {
                                    JOptionPane.showMessageDialog(userForm, "Error processing remote file, please try again", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        //Neste caso o input é um caminho para um ficheiro local
                        else {
                            File file = new File(input);
                            if (file.exists()) {
                                //Se existir usa o csv para gerar os dados para a pagina HTML e depois abre a pagina
                                htmlCreator = new HtmlCreator(input);
                                openSchedule();
                            } else {
                                //No caso de não existir o ficheiro aparece uma mensagem de erro
                                JOptionPane.showMessageDialog(userForm, "File does not exist: " + file, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        //No caso de não existir o ficheiro aparece uma mensagem de erro
                        JOptionPane.showMessageDialog(userForm, "Invalid file path.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );

    }

    private boolean saveToLocalFile(InputStream inputStream, String localFilePath) {
        try {
            File localFile = new File(localFilePath);
            localFile.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(localFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    //Este método abre a pagina HTML com a tabela no browser
    // verifica também se a pagina HTML foi gerada com sucesso
    private void openSchedule() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            File file = new File(htmlCreator.getHtmlPath());
            if (file.exists()) {
                try {
                    if(htmlCreator.generateHtmlPage()){
                        System.out.println("HTML page generated successfully");
                        desktop.browse(file.toURI());
                    } else {
                        System.out.println("Error generating HTML page");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //No caso de não existir o ficheiro aparece uma mensagem de erro
                JOptionPane.showMessageDialog(userForm, "File does not exist: " + file, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(userForm, "Desktop is not supported on this platform", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
