package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnsOrderingPage extends JFrame implements LayoutDefinable {

    private JButton openScheduleButton;
    private DefaultListModel<String> userListModel;
    private JButton scheduleQualityButton;
    GridBagConstraints gbc = new GridBagConstraints();
    private List<String> defaultColumnTitles = new ArrayList<>(List.of(
            "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
            "Dia da semana", "Hora de início da aula", "Hora de fim da aula", "Data da aula",
            "Caracaterísticas da sala atribuída para a aula", "Sala de aula atribuída"));

    public ColumnsOrderingPage(List<String> userColumnTitles, JFrame previousFrame) {
        LayoutDefinable.basicLayout("Columns Ordering", this, Color.darkGray);

        //Estruturação da página
        gbc = resetGBC(gbc);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.darkGray);
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.darkGray);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.darkGray);

        //Criação dos elementos do painel lateral esquerdo
        userListModel = new DefaultListModel<>();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        if(userColumnTitles.size() > defaultColumnTitles.size()) {
            int i = defaultColumnTitles.size();
            while(i < userColumnTitles.size() ) {
                defaultColumnTitles.add(" <Empty> ");
                i++;
            }
        }

        for(String userColumnTitle: userColumnTitles) {
            userListModel.addElement(userColumnTitle);
        }
        for(String defaultColumnTitle: defaultColumnTitles) {
            defaultListModel.addElement(defaultColumnTitle);
        }

        //Cria uma outra lista de colunas (com base da lista prévia) que pode ter uma barra lateral para arrastar o display da lista
        JList<String> userList = new JList<>(userListModel);
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

        JTextArea textArea = LayoutDefinable.defineTextAreaLayout(
                "Select a line and click \"Move UP\" or \"Move Down\" to reorder your columns. \n" +
                        "Please make sure you follow the default columns order.",
                "Arial", Font.PLAIN, 15, Color.darkGray, Color.WHITE);

        scheduleQualityButton = LayoutDefinable.defineButtonLayout(Color.BLUE,
                Color.WHITE, "Schedule Quality", new Dimension(150, 50));

        openScheduleButton = LayoutDefinable.defineButtonLayout(Color.BLUE,
                Color.WHITE, "Open Schedule", new Dimension(150, 50));

        JButton moveUpButton = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE, "Move Up", new Dimension(130, 50));
        moveUpButton.addActionListener(e -> moveSelectedColumn(userList, -1));
        JButton moveDownButton = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE, "Move Down", new Dimension(130, 50));
        moveDownButton.addActionListener(e -> moveSelectedColumn(userList, 1));
        JButton confirmButton = LayoutDefinable.defineButtonLayout(Color.GREEN, Color.WHITE, "Confirm", new Dimension(130, 50));
        confirmButton.addActionListener(e -> popUpConfirmationPage());
        JButton goBackButton = LayoutDefinable.defineButtonLayout(Color.RED, Color.WHITE, "Go Back", new Dimension(130, 50));

        goBackButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            previousFrame.setVisible(true);
        });

        gbc.gridwidth = 2;
        //Adiciona os elementos ao painel lateral esquerdo
        leftPanel.add(defaultColumnsLabel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.gridy = 1;
        leftPanel.add(defaultScrollPane, gbc);

        gbc = resetGBC(gbc);
        //Adiciona os elemenstos ao painel central
        gbc.gridwidth = 2;
        centerPanel.add(userColumnsLabel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.gridy = 1;
        centerPanel.add(userScrollPane, gbc);

        gbc = resetGBC(gbc);
        //Adiciona os elementos ao painel lateral direito
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        rightPanel.add(textArea, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.WEST;
        rightPanel.add(moveUpButton, gbc);
        gbc.gridy++;
        rightPanel.add(moveDownButton, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.CENTER;
        rightPanel.add(confirmButton, gbc);
        gbc.gridy++;
        rightPanel.add(goBackButton, gbc);

        gbc = resetGBC(gbc);
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.33;
        gbc.weighty = 1;
        add(leftPanel, gbc);
        gbc.gridx++;
        add(centerPanel, gbc);
        gbc.gridx++;
        add(rightPanel, gbc);
    }

    private void popUpConfirmationPage() {
        JDialog dialog = new JDialog(this, "Confirm Ordering", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new GridBagLayout());
        dialog.setBackground(Color.darkGray);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setBackground(Color.darkGray);
        dialogPanel.setLayout(new GridBagLayout());
        gbc = resetGBC(gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        dialog.add(dialogPanel, gbc);

        gbc = resetGBC(gbc);
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField dialogTextField = LayoutDefinable.defineTextFieldLayout(
                "Are you sure you want to confirm this ordering?",
                "Arial", Font.PLAIN, 15, Color.darkGray, Color.WHITE);

        JButton goBack = LayoutDefinable.defineButtonLayout(Color.RED,
                Color.WHITE, "Go Back", new Dimension(150, 50));
        goBack.addActionListener(e -> {
            dialogPanel.setVisible(false);
            dialog.dispose();
            this.setVisible(true);
        });

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        dialogPanel.add(dialogTextField, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        dialogPanel.add(scheduleQualityButton, gbc);
        gbc.gridx++;
        dialogPanel.add(openScheduleButton, gbc);
        gbc.gridx++;
        dialogPanel.add(goBack, gbc);

        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public JButton getOpenScheduleButton () {
        return openScheduleButton;
    }

    public JButton getScheduleQualityButton () {
        return scheduleQualityButton;
    }

    /**
     * Move a coluna selecionada para cima ou para baixo.
     * @param list
     * @param direction
     */

    //Metodo que move as colunas visualmente e na lista de colunas de acordo com o botao que é clicado
    private void moveSelectedColumn(JList<String> list, int direction) {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            int newIndex = selectedIndex + direction;
            if (newIndex >= 0 && newIndex < userListModel.getSize()) {
                String selectedValue = userListModel.remove(selectedIndex);
                userListModel.add(newIndex, selectedValue);
                list.setSelectedIndex(newIndex);
            }
        }
    }

    /**
     * Devolve uma lista com os titulos das colunas ordenados pelo utilizador.
     *
     * @return uma lista com os titulos das colunas ordenados pelo utilizador
     */

    //Devolve uma lista com as colunas ordenadas pelo utilizador
    public List<String> getUserOrderedColumnTitles() {
        List<String> columnTitlesUpdated = new ArrayList<>();
        for (int i = 0; i < userListModel.getSize(); i++) {
            columnTitlesUpdated.add(userListModel.get(i));
        }
        return columnTitlesUpdated;
    }

    public static void main(String[] args) {
        //Create an instance to run the page please
        ColumnsOrderingPage cOP = new ColumnsOrderingPage(new ArrayList<>(List.of(
                "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
                "Dia da semana", "Hora de início da aula", "Hora de fim da aula", "Data da aula",
                "Caracaterísticas da sala atribuída para a aula", "Sala de aula atribuída")), new JFrame());
        LayoutDefinable.setVisibility(cOP, true);
    }
}
