package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Este UserFrome como indica é a pagina que o utilizador vai ver quando entrar no programa
public class UserForm extends JFrame {

    private JButton submitFileButton;

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

        submitFileButton = new JButton("Submit File");
        submitFileButton.setBackground(Color.WHITE);
        submitFileButton.setForeground(Color.BLACK);
        submitFileButton.setPreferredSize(new Dimension(120, 40));


        centerPanel.add(submitFileButton, gbc);
        add(centerPanel, BorderLayout.CENTER);
    }

    //Este método permite definir o que acontece quando o utilizador carrega no botão
    void setSubmitFileButtonActionListener(ActionListener actionListener) {
        submitFileButton.addActionListener(actionListener);
    }
}
