package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Este UserForm como indica é a pagina que o utilizador vai ver quando entrar no programa
public class UserForm extends JFrame {

    private final JButton submitFileButton;
    private final JTextField csvFileLocation;
    private final JTextField classValue;
    private DefaultListModel<String> columnListModel;

    //Construtor para criar a pagina
    public UserForm() {

        //Estas linhas de código suavizam o look da GUI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        submitFileButton = new JButton("Submit File");

        //Ajustes feitas na pagina da GUI
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_VERT);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        //Criação do painel central de topo
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTop = new GridBagConstraints();

        //Posicionamento dos paineis
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // 2 colunas
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; // Take all horizontal space
        gbc.weighty = 0.5; // Cada painel adicionado ocupará metade do espaço vertical

        //Mesma lógica mas para o painel de topo
        gbcTop.gridx = 0;
        gbcTop.gridy = 0;
        gbcTop.gridheight = 2; // 2 linhas
        gbcTop.fill = GridBagConstraints.CENTER;
        gbcTop.weighty = 0.5;


        //Titulo que é adicionado ao painel de topo
        JLabel headerLabel = new JLabel("CSV to WebBrowser Schedule ");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 60));
        headerLabel.setForeground(new Color(44, 62, 80));
        topPanel.add(headerLabel, gbcTop);

        //Caixa de texto para submeter o ficheiro e adição ao painel de topo
        csvFileLocation = new JTextField("Please submit full .csv file location here", 90);
        gbcTop.gridy = 3;
        topPanel.add(csvFileLocation, gbcTop);

        //Por o painel de topo na pagina
        add(topPanel, gbc);


        //Criação do painel lateral esquerdo de baixo
        JPanel bottomLeftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottomLeft = new GridBagConstraints();
        gbcBottomLeft.gridx = 0;
        gbcBottomLeft.gridy = 0;
        gbcBottomLeft.anchor = GridBagConstraints.CENTER;

        //Ajuste da posição para o painel lateral esquerdo de baixo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset grid width to 1
        gbc.weighty = 0.5; // Half the vertical space

        //Caixa de texto para especificar a turma e adição ao painel lateral esquerdo de baixo
        classValue = new JTextField("Please submit class designation here", 60);
        bottomLeftPanel.add(classValue, gbcBottomLeft);

        //Por o painel lateral esquerdo de baixo na pagina
        add(bottomLeftPanel, gbc);

        //Mesma lógica do painel lateral esquerdo de baixo mas para o painel lateral direito de baixo
        JPanel bottomRightPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottomRight = new GridBagConstraints();
        gbcBottomRight.gridx = 0;
        gbcBottomRight.gridy = 0;
        gbcBottomRight.anchor = GridBagConstraints.CENTER;

        JButton continueButton = new JButton("Continue");
        continueButton.setBackground(Color.BLUE);
        continueButton.setForeground(Color.WHITE);
        continueButton.setPreferredSize(new Dimension(180, 70));
        continueButton.addActionListener(e -> {
            openReorderDialog();
        });
        bottomRightPanel.add(continueButton, gbcBottomRight);

        gbc.gridx = 1;
        add(bottomRightPanel, gbc);

        //Faz com que a pagina ao ser iniciada apareça justinha aos elementos que contém
        pack();

        //Faz com que a pagina ao ser iniciada apareça no centro do ecrã
        setLocationRelativeTo(null);
    }

    private void openReorderDialog() {
        JDialog dialog = new JDialog(this, "Reorder Columns", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setBackground(Color.darkGray);

        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Posicionamento dos paineis
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3; // 3 colunas
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5; // Leva metade do espaço horizontal
        gbc.weighty = 1; // Leva o espaço vertical por completo

        JPanel dialogPanelEsquerdo = new JPanel(new GridBagLayout());
        GridBagConstraints gbl = new GridBagConstraints();
        gbl.fill = GridBagConstraints.CENTER;
        gbl.gridheight = 1;

        List<String> columnTitles = new ArrayList<>(List.of(
                "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
                "Dia da semana", "Hora de início da aula", "Hora de fim da aula", "Data da aula",
                "Caracaterísticas da sala atribuída para a aula", "Sala de aula atribuída"));

        //Inicializa o objeto que representa a lista de coluna
        // percorre a lista de colunas e adiciona cada uma à lista
        columnListModel = new DefaultListModel<>();
        for (String columnTitle : columnTitles) {
            columnListModel.addElement(columnTitle);
        }

        //Cria uma outra lista de colunas (com base da lista prévia) que pode ter uma barra lateral para arrastar o display da lista
        JList<String> dialogList = new JList<>(columnListModel);

        //Display/Painel com barra lateral deslizante onde é adicionado a lista acima
        JScrollPane dialogScrollPane = new JScrollPane(dialogList);

        //Adiciona o painel com barra deslizante ao painel lateral esquerdo da pagina
        dialogPanelEsquerdo.add(dialogScrollPane, gbc);

        //Poe o painel no lado esquerdo da pagina popup
        dialog.add(dialogPanelEsquerdo, gbc);

        //Poe o painel no lado direito da pagina popup
        gbc.gridx = 1;
        JPanel dialogPanelDireito = new JPanel(new GridBagLayout());
        dialogPanelDireito.setBackground(Color.darkGray);
        GridBagConstraints gbd = new GridBagConstraints();
        gbd.gridx = 0;
        gbd.gridy = 0;
        gbd.gridwidth = 4;
        gbd.fill = GridBagConstraints.BOTH;
        gbd.weightx = 0.33;

        //Texto que aparece na pagina popup
        JTextArea textArea = new JTextArea("Select a line and click a 'Move UP' or 'Move Down' to reorder the columns, please note " +
                "that the order is from top to bottom", 1, 1);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.WHITE);
        dialogPanelDireito.add(textArea, gbd);

        //Botoes para alterar a ordem das colunas e inicializacao do botao para submeter o ficheiro
        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(e -> moveSelectedColumn(dialogList, 1));
        moveDownButton.setBackground(Color.BLUE);
        moveDownButton.setForeground(Color.WHITE);
        moveDownButton.setPreferredSize(new Dimension(120, 40));
        gbd.gridy = 2;
        dialogPanelDireito.add(moveDownButton, gbd);

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(e -> moveSelectedColumn(dialogList, -1));
        moveUpButton.setBackground(Color.BLUE);
        moveUpButton.setForeground(Color.WHITE);
        moveUpButton.setPreferredSize(new Dimension(120, 40));
        gbd.gridy = 1;
        dialogPanelDireito.add(moveUpButton, gbd);

        submitFileButton.setBackground(Color.BLUE);
        submitFileButton.setForeground(Color.WHITE);
        submitFileButton.setPreferredSize(new Dimension(120, 40));
        gbd.gridy = 3;
        dialogPanelDireito.add(submitFileButton, gbd);



        //Poe o painel no lado direito da pagina popup
        dialog.add(dialogPanelDireito, gbc);

        //Faz com que a pagina ao ser iniciada apareça justinha aos elementos que contém
        dialog.pack();

        //Faz com que a pagina ao ser iniciada apareça no centro da pagina anterior ou seja da pagina principal
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    //Metodo que move as colunas visualmente e na lista de colunas de acordo com o botao que é clicado
    private void moveSelectedColumn(JList<String> list, int direction) {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            int newIndex = selectedIndex + direction;
            if (newIndex >= 0 && newIndex < columnListModel.getSize()) {
                String selectedValue = columnListModel.remove(selectedIndex);
                columnListModel.add(newIndex, selectedValue);
                list.setSelectedIndex(newIndex);
            }
        }
    }

    //Metodos que devolvem elementos da pagina
    public JTextField getCsvFileLocationTextField() {
        return csvFileLocation;
    }

    public JButton getSubmitFileButton() {
        return submitFileButton;
    }

    public JTextField getClassValue() {return classValue;}

    //Devolve uma lista com as colunas ordenadas pelo utilizador
    public List<String> getColumnTitles() {
        List<String> columnTitlesUpdated = new ArrayList<>();
        for (int i = 0; i < columnListModel.getSize(); i++) {
            columnTitlesUpdated.add(columnListModel.get(i));
        }
        return columnTitlesUpdated;
    }

}
