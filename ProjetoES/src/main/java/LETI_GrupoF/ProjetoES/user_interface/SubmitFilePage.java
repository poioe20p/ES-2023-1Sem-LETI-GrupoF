package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;

//Este UserForm como indica é a pagina que o utilizador vai ver quando entrar no programa

/**
 * A classe UserForm representa a pagina principal da GUI.
 */
public class SubmitFilePage extends JFrame {

    private final JTextField csvFileLocation;
    private boolean isRemoteFile = false;
    private JButton continueButton;
    private JButton reloadLastSessionButton;

    /**
     * Construtor da classe UserForm.
     *
     */

    //Construtor para criar a pagina
    public SubmitFilePage() {

        //Estas linhas de código suavizam o look da GUI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        openScheduleButton = new JButton("Open Schedule");

        //Ajustes feitas na pagina da GUI
        setTitle("Main Page");

        GridBagConstraints gbc = setUpPageFrame(this, "Main Page");


        //Criação do painel central de topo
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTop = new GridBagConstraints();

        //Posicionamento dos paineis
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // 2 colunas
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; // Take all horizontal space
        gbc.weighty = 0.5; // Cada painel adicionado ocupará metade do espaço vertical

        //Mesma lógica mas para o painel de topo
        gbcTop.gridx = 0;
        gbcTop.gridy = 0;
        gbcTop.fill = GridBagConstraints.CENTER;
        gbcTop.weighty = 0.5;

        //Titulo que é adicionado ao painel de topo
        JLabel headerLabel = new JLabel("CSV to WebBrowser Schedule ");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 60));
        headerLabel.setForeground(new Color(44, 62, 80));
        topPanel.add(headerLabel, gbcTop);

        gbcTop.weighty = 0;
        //Caixa de texto para submeter o ficheiro e adição ao painel de topo
        JLabel csvFileLocationLabel = new JLabel("CSV File Location");
        csvFileLocationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        csvFileLocationLabel.setForeground(new Color(44, 62, 80));
        gbcTop.gridy = 2;
        topPanel.add(csvFileLocationLabel, gbcTop);
        csvFileLocation = new JTextField("Please submit full .csv file location here", 90);
        gbcTop.gridy = 3;
        topPanel.add(csvFileLocation, gbcTop);

        //Por o painel de topo na pagina
        add(topPanel, gbc);

        //Criação do painel de baixo
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottomPanel = new GridBagConstraints();
        gbcBottomPanel.gridx = 0;
        gbcBottomPanel.gridy = 0;
        gbcBottomPanel.weightx = 0.5;
        gbcBottomPanel.anchor = GridBagConstraints.CENTER;

        continueButton = new JButton("Submit & Continue");
        continueButton.setBackground(Color.BLUE);
        continueButton.setForeground(Color.WHITE);
        continueButton.setPreferredSize(new Dimension(150, 50));
        bottomPanel.add(continueButton, gbcBottomPanel);
        gbcBottomPanel.gridx = 1;
        reloadLastSessionButton = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE, "Reload Last Session", new Dimension(150, 50));
        bottomPanel.add(reloadLastSessionButton, gbcBottomPanel);


        //Ajuste da posição para o painel lateral esquerdo de baixo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset grid width to 1
        gbc.weighty = 1; // Leva o espaço vertical por completo

        gbc.gridx = 1;
        add(bottomPanel, gbc);

        //Faz com que a pagina ao ser iniciada apareça no centro do ecrã
        setLocationRelativeTo(null);
    }

   static public GridBagConstraints setUpPageFrame(JFrame frame, String title) {
        frame.setTitle(title);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setBackground(Color.darkGray);
        frame.setLayout(new GridBagLayout());
        return new GridBagConstraints();
    }

    /**
     * Devolve a caixa de texto quer serve para o utilizador submeter a locaização do ficheiro
     *
     * @return devolve a caixa de texto
     */

    //Metodos que devolvem elementos da pagina
    public JTextField getCsvFileLocationTextField() {
        return csvFileLocation;
    }

    /**
     * Devolve o botao para abrir a pagina HTML.
     *
     * @return devolve o botão que tem o objetivo de ser usado para abrir o horario no browser
     */

    /**
     * Devolve o botao para submeter o ficheiro.
     *
     * @return notão que tem o intuito de ser usado para levar o utilizador para a proxima pagina
     */
    public JButton getContinueButton() { return continueButton;}

    /**
     * Devolve uma lista com os titulos das colunas ordenados pelo utilizador.
     *
     * @return o valor do booleano que indica se o ficheiro csv é remoto ou não
     */
    public boolean isRemoteFile() { return isRemoteFile;}


    /**
     * Devolve uma lista com os titulos das colunas ordenados pelo utilizador.
     *
     * @param remoteFile
     */
    public void setRemoteFile(boolean remoteFile) { isRemoteFile = remoteFile;}

    /**
     * Devolve o botão para carregar a ultima sessão.
     *
     * @return botão que tem o intuito de ser usado para carregar a ultima sessão
     */
    public JButton getReloadLastSessionButton() { return reloadLastSessionButton;}

}
