package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Este UserForm como indica é a pagina que o utilizador vai ver quando entrar no programa
public class UserForm extends JFrame {

    private final JButton openScheduleButton;
    private final JTextField csvFileLocation;
    private DefaultListModel<String> columnListModel;
    private boolean isRemoteFile = false;

    private List<String> columnTitles = new ArrayList<>(List.of(
            "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
            "Dia da semana", "Hora de início da aula", "Hora de fim da aula", "Data da aula",
            "Caracaterísticas da sala atribuída para a aula", "Sala de aula atribuída"));

    private JButton continueButton;

    //Construtor para criar a pagina
    public UserForm() {

        //Estas linhas de código suavizam o look da GUI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        openScheduleButton = new JButton("Open Schedule");

        //Ajustes feitas na pagina da GUI
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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

        //Criação do painel de baixo
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottomPanel = new GridBagConstraints();
        gbcBottomPanel.gridx = 0;
        gbcBottomPanel.gridy = 0;
        gbcBottomPanel.anchor = GridBagConstraints.CENTER;

        continueButton = new JButton("Submit & Continue");
        continueButton.setBackground(Color.BLUE);
        continueButton.setForeground(Color.WHITE);
        continueButton.setPreferredSize(new Dimension(180, 70));
        bottomPanel.add(continueButton, gbcBottomPanel);

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

    public void openReorderPage(List<String> userColumnTitles) {
        JFrame reorderFrame = new JFrame();
        reorderFrame.setTitle("Reorder Columns");
        reorderFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        reorderFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        reorderFrame.setBackground(Color.darkGray);

        reorderFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Posicionamento dos paineis
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3; // 3 colunas
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.33; // Leva metade do espaço horizontal
        gbc.weighty = 1; // Leva o espaço vertical por completo

        JPanel reorderPanelLeft = new JPanel(new GridBagLayout());
        reorderPanelLeft.setBackground(Color.darkGray);
        GridBagConstraints gbl = new GridBagConstraints();
        gbl.gridx = 0;
        gbl.gridy = 0;
        gbl.fill = GridBagConstraints.NONE;
        gbl.gridwidth = 2;

        DefaultListModel<String> defaultListModel = new DefaultListModel<>();

        JPanel reorderPanelCenter = new JPanel(new GridBagLayout());
        reorderPanelCenter.setBackground(Color.darkGray);
        GridBagConstraints gbrc = new GridBagConstraints();
        gbrc.gridx = 0;
        gbrc.gridy = 0;
        gbrc.fill = GridBagConstraints.NONE;
        gbrc.gridwidth = 2;

        //No caso do utilizador ter mais colunas que as predefinidas adiciona colunas vazias
        if(userColumnTitles.size() > columnTitles.size()) {
            int i = columnTitles.size();
            while(i < userColumnTitles.size()) {
                columnTitles.add(" <Empty> ");
                i++;
            }
        }

        //Inicializa o objeto que representa a lista de coluna
        // percorre a lista de colunas e adiciona cada uma à lista
        for (String columnTitle : columnTitles) {
            defaultListModel.addElement(columnTitle);
        }

        columnListModel = new DefaultListModel<>();
        for (String userColumnTitle : userColumnTitles) {
            columnListModel.addElement(userColumnTitle);
        }

        //Cria uma outra lista de colunas (com base da lista prévia) que pode ter uma barra lateral para arrastar o display da lista
        JList<String> userList = new JList<>(columnListModel);
        JList<String> defaultList = new JList<>(defaultListModel);

        //Display/Painel com barra lateral deslizante onde é adicionado a lista acima
        JScrollPane userScrollPane = new JScrollPane(userList);
        JScrollPane defaultScrollPane = new JScrollPane(defaultList);

        JLabel defaultColumnsLabel = new JLabel("Default Columns Order");
        defaultColumnsLabel.setForeground(Color.WHITE);
        defaultColumnsLabel.setFont(new Font("Arial", Font.BOLD, 30));


        JLabel userColumnsLabel = new JLabel("Your Columns Order");
        userColumnsLabel.setForeground(Color.WHITE);
        userColumnsLabel.setFont(new Font("Arial", Font.BOLD, 30));

        reorderPanelLeft.add(defaultColumnsLabel, gbl);
        gbl.fill = GridBagConstraints.BOTH;
        gbl.weighty = 1;
        gbl.gridy = 1;
        //Adiciona o painel com barra deslizante ao painel lateral esquerdo da pagina
        reorderPanelLeft.add(defaultScrollPane, gbl);

        reorderPanelCenter.add(userColumnsLabel, gbrc);
        gbrc.fill = GridBagConstraints.BOTH;
        gbrc.weighty = 1;
        gbrc.gridy = 1;
        //Adiciona o painel com barra deslizante ao painel lateral esquerdo da pagina
        reorderPanelCenter.add(userScrollPane, gbrc);

        gbc.gridx = 1;
        reorderFrame.add(reorderPanelLeft, gbc);

        //Poe o painel no lado esquerdo da pagina popup
        gbc.gridx = 2;
        reorderFrame.add(reorderPanelCenter, gbc);

        //Poe o painel no lado direito da pagina popup
        gbc.gridx = 3;
        JPanel reorderPanelDireito = new JPanel(new GridBagLayout());
        reorderPanelDireito.setBackground(Color.darkGray);
        GridBagConstraints gbd = new GridBagConstraints();
        gbd.gridx = 0;
        gbd.gridy = 0;
        gbd.gridwidth = 4;
        gbd.fill = GridBagConstraints.NONE;
        gbd.weightx = 0.33;

        //Texto que aparece na pagina popup
        JTextArea textArea = new JTextArea("Select a line and click 'Move UP' or 'Move Down' to reorder your columns", 1, 1);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.WHITE);
        reorderPanelDireito.add(textArea, gbd);

        //Botoes para alterar a ordem das colunas e inicializacao do botao para submeter o ficheiro
        JButton moveDownButton = new JButton("Move Down");
        moveDownButton.addActionListener(e -> moveSelectedColumn(userList, 1));
        moveDownButton.setBackground(Color.BLUE);
        moveDownButton.setForeground(Color.WHITE);
        moveDownButton.setPreferredSize(new Dimension(130, 50));
        gbd.gridy = 2;
        reorderPanelDireito.add(moveDownButton, gbd);

        JButton moveUpButton = new JButton("Move Up");
        moveUpButton.addActionListener(e -> moveSelectedColumn(userList, -1));
        moveUpButton.setBackground(Color.BLUE);
        moveUpButton.setForeground(Color.WHITE);
        moveUpButton.setPreferredSize(new Dimension(130, 50));
        gbd.gridy = 1;
        reorderPanelDireito.add(moveUpButton, gbd);

        openScheduleButton.setBackground(Color.BLUE);
        openScheduleButton.setForeground(Color.WHITE);
        openScheduleButton.setPreferredSize(new Dimension(130, 50));
        gbd.gridy = 3;
        reorderPanelDireito.add(openScheduleButton, gbd);


        //Poe o painel no lado direito da pagina popup
        reorderFrame.add(reorderPanelDireito, gbc);

        //Faz com que a pagina ao ser iniciada apareça justinha aos elementos que contém
//        dialog.pack();

        //Faz com que a pagina ao ser iniciada apareça no centro da pagina anterior ou seja da pagina principal
        reorderFrame.setLocationRelativeTo(this);
        reorderFrame.setVisible(true);
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

    public JButton getOpenScheduleButton() {
        return openScheduleButton;
    }

    public JButton getContinueButton() { return continueButton;}

    public boolean isRemoteFile() { return isRemoteFile;}

    public void setRemoteFile(boolean remoteFile) { isRemoteFile = remoteFile;}

    //Devolve uma lista com as colunas ordenadas pelo utilizador
    public List<String> getColumnTitles() {
        List<String> columnTitlesUpdated = new ArrayList<>();
        for (int i = 0; i < columnListModel.getSize(); i++) {
            columnTitlesUpdated.add(columnListModel.get(i));
        }
        return columnTitlesUpdated;
    }

}
