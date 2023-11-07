package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Este UserForm como indica é a pagina que o utilizador vai ver quando entrar no programa
public class UserForm extends JFrame {

    private JButton submitFileButton;
    private JTextField csvFileLocation;

    private JTextField classValue;

    //Construtor para criar a pagina
    public UserForm() {

        //Estas linhas de código suavizam o look da GUI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        //Criação do painel central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel headerLabel = new JLabel("CSV to WebBrowser Schedulle ");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(new Color(44, 62, 80));

        csvFileLocation = new JTextField( "Please submit full .csv file location here" ,60);


        submitFileButton = new JButton("Submit File");
        submitFileButton.setBackground(Color.BLUE);
        submitFileButton.setForeground(Color.WHITE);
        submitFileButton.setPreferredSize(new Dimension(120, 40));

        centerPanel.add(headerLabel, gbc);
        gbc.gridy++;
        centerPanel.add(csvFileLocation, gbc);
        gbc.gridy++;
        centerPanel.add(submitFileButton, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    JTextField getCsvFileLocation() {
        return csvFileLocation;
    }
    JButton getSubmitFileButton() {
        return submitFileButton;
    }

}
