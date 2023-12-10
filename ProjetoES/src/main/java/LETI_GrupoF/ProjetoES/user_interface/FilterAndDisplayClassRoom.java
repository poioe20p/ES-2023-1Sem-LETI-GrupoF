package LETI_GrupoF.ProjetoES.user_interface;


import LETI_GrupoF.ProjetoES.Salas;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterAndDisplayClassRoom extends JFrame implements LayoutDefinable {

    private final DefaultListModel<String> roomCaracteristics = new DefaultListModel<>();
    private final JButton viewClassRooms;
    private GridBagConstraints gbc;
    private final List<String> userSelectedRoomTypes = new ArrayList<>();
    private final List<String> userRoomCapacitySelection = new ArrayList<>();

    public FilterAndDisplayClassRoom(Salas salas, JFrame previousFrame) {
        LayoutDefinable.basicLayout("Filter and Display Class Rooms", this, LayoutDefinable.getColor("gray"));

        viewClassRooms = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE,
                "View Class Rooms", new Dimension(160, 50));
        JPanel lefPanel = setUpLeftPanel();
        JPanel rightPanel = setUpRightPanel();

        JButton backButton = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE,
                "Back", new Dimension(130, 50));
        backButton.addActionListener(
                b -> {
                    previousFrame.setVisible(true);
                    this.dispose();
                }
        );

        gbc = resetGBC(new GridBagConstraints());
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 1;

        add(lefPanel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        add(rightPanel, gbc);
    }

    private JPanel setUpLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.darkGray);

        String[] classRoomTypes = {"Laboratorio de Arquitectura de Computadores I", "Laboratorio de Arquitectura de Computadores II",
                "Laboratorio de Bases de Engenharia", "Laboratorio de Electrónica", "Laboratorio de Informatica",
                "Laboratorio de Jornalismo", "Laboratorio de Redes de Computadores I", "Laboratorio de Redes de Computadores II",
                "Laboratorio de Telecomunicacoes", "Sala de Aula", "Sala Aulas Mestrado", "Sala Aulas Plus", "Sala NEE", "Sala Provas", "Sala Reuniao",
                "Sala de Arquitectura", "Sala de Aulas normal", "Videoconferencia", "Atrio"};
        for (String classRoomType : classRoomTypes) {
            roomCaracteristics.addElement(classRoomType);
        }

        JList<String> list = new JList<>(roomCaracteristics);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane roomTypeScrollPane = new JScrollPane(list);
        roomTypeScrollPane.setPreferredSize(new Dimension(300, 395));

        JLabel classRoomTypesLabel = new JLabel("Class Room Types");
        classRoomTypesLabel.setForeground(Color.white);
        classRoomTypesLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.darkGray);

        JButton getRoomTypesButton = LayoutDefinable.defineButtonLayout(Color.BLUE, Color.WHITE,
                "Add Room Types", new Dimension(130, 50));
        getRoomTypesButton.addActionListener(gRTB -> userSelectedRoomTypes.addAll(list.getSelectedValuesList()));

        gbc = resetGBC(resetGBC(new GridBagConstraints()));
        gbc.insets = new Insets(20, 15, 20, 15);
        gbc.gridwidth = 4;
        leftPanel.add(classRoomTypesLabel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 1;
        leftPanel.add(roomTypeScrollPane, gbc);
        gbc.gridy = 2;
        leftPanel.add(getRoomTypesButton, gbc);
        return leftPanel;
    }

    private JPanel setUpRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.darkGray);

        JLabel classRoomTypesLabel = new JLabel("Normal Capacity");
        classRoomTypesLabel.setForeground(Color.white);
        classRoomTypesLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel classRoomTypesLabel2 = new JLabel("Exam Capacity");
        classRoomTypesLabel2.setForeground(Color.white);
        classRoomTypesLabel2.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel classRoomFilteringLabel = new JLabel("Click enter to confirm input");
        classRoomFilteringLabel.setForeground(Color.white);
        classRoomFilteringLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField minCapacityTextField = LayoutDefinable.defineTextFieldLayout( "", "Arial",
                Font.PLAIN, 15, Color.WHITE, Color.BLACK);
        minCapacityTextField.setColumns(7);

        JTextField maxCapacityTextField = LayoutDefinable.defineTextFieldLayout("", "Arial",
                Font.PLAIN, 15, Color.WHITE, Color.BLACK);
        maxCapacityTextField.setColumns(7);

        JTextField minCapacityTextField2 = LayoutDefinable.defineTextFieldLayout("", "Arial",
                Font.PLAIN, 15, Color.WHITE, Color.BLACK);
        minCapacityTextField2.setColumns(7);

        JTextField maxCapacityTextField2 = LayoutDefinable.defineTextFieldLayout("", "Arial",
                Font.PLAIN, 15, Color.WHITE, Color.BLACK);
        maxCapacityTextField2.setColumns(7);

        maxCapacityTextField.setEnabled(false);
        maxCapacityTextField2.setEnabled(false);

        JComboBox<String> conditionsListNormalCapacity = new JComboBox<>(new String[]{"=", "<", ">"});
        JComboBox<String> conditionsListNormalCapacity2 = new JComboBox<>(new String[]{"<", ">"});
        JComboBox<String> conditionsListExamCapacity = new JComboBox<>(new String[]{"=", "<", ">"});
        JComboBox<String> conditionsListExamCapacity2 = new JComboBox<>(new String[]{"<", ">"});

        conditionsListNormalCapacity.setEnabled(false);
        conditionsListNormalCapacity2.setEnabled(false);
        conditionsListExamCapacity.setEnabled(false);
        conditionsListExamCapacity2.setEnabled(false);

        setRightPanelButtons(
                minCapacityTextField,
                conditionsListNormalCapacity,
                conditionsListNormalCapacity2,
                maxCapacityTextField
        );

        setRightPanelButtons(
                minCapacityTextField2,
                conditionsListExamCapacity,
                conditionsListExamCapacity2,
                maxCapacityTextField2
        );

        //Adicção de elementos para filtragem atraves da capacidade normal
        gbc = resetGBC(new GridBagConstraints());
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.gridwidth = 6;
        rightPanel.add(classRoomFilteringLabel, gbc);
        gbc = resetGBC(new GridBagConstraints());
        gbc.gridy++;
        rightPanel.add(minCapacityTextField, gbc);
        gbc.gridx++;
        rightPanel.add(conditionsListNormalCapacity, gbc);
        gbc.gridx++;
        rightPanel.add(classRoomTypesLabel, gbc);
        gbc.gridx++;
        rightPanel.add(conditionsListNormalCapacity2, gbc);
        gbc.gridx++;
        rightPanel.add(maxCapacityTextField, gbc);

        //Adicção de elementos para filtragem atraves da capacidade de exame
        gbc.gridy = gbc.gridy + 2;
        gbc.gridx = 0;
        gbc.insets = new Insets(60, 5, 0, 5);
        gbc.gridy++;
        rightPanel.add(minCapacityTextField2, gbc);
        gbc.gridx++;
        rightPanel.add(conditionsListExamCapacity, gbc);
        gbc.gridx++;
        rightPanel.add(classRoomTypesLabel2, gbc);
        gbc.gridx++;
        rightPanel.add(conditionsListExamCapacity2, gbc);
        gbc.gridx++;
        rightPanel.add(maxCapacityTextField2, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.insets = new Insets(30, 5, 0, 5);
        rightPanel.add(viewClassRooms, gbc);
        return rightPanel;
    }

    private void setRightPanelButtons(JTextField minCapacityTextField , JComboBox<String> conditionsListNormalCapacity,
                                      JComboBox<String> conditionsListNormalCapacity2, JTextField maxCapacityTextField) {

        minCapacityTextField.setEditable(true);
        maxCapacityTextField.setEditable(true);
        restrictToIntegerOnly(minCapacityTextField);
        restrictToIntegerOnly(maxCapacityTextField);
        minCapacityTextField.addActionListener(mCTF -> {
            conditionsListNormalCapacity.setEnabled(true);
            userRoomCapacitySelection.add(minCapacityTextField.getText());
        });

        conditionsListNormalCapacity.addActionListener(cL -> {
            userRoomCapacitySelection.add((String) conditionsListNormalCapacity.getSelectedItem());
            if(!Objects.equals(conditionsListNormalCapacity.getSelectedItem(), "=")) conditionsListNormalCapacity2.setEnabled(true);
        });

        conditionsListNormalCapacity2.addActionListener(cL2 -> {
            userRoomCapacitySelection.add((String) conditionsListNormalCapacity2.getSelectedItem());
            maxCapacityTextField.setEnabled(true);
        });

        maxCapacityTextField.addActionListener(mCTF2 -> userRoomCapacitySelection.add(maxCapacityTextField.getText()));
    }

    private  void restrictToIntegerOnly(JTextField textField) {
        Document doc = textField.getDocument();
        ((AbstractDocument) doc).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.matches("-?\\d+")) { // Allow negative and positive integers
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Only numbers are allowed");
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
                if (newText.matches("-?\\d+")) { // Allow negative and positive integers
                    super.insertString(fb, offset, string, attr);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Only numbers are allowed");
                }
            }
        });
    }

    public static void main(String[] args) {
        new FilterAndDisplayClassRoom(new Salas("C:\\Users\\themo\\OneDrive\\Documents\\GitHub\\ES-2023-1Sem-LETI-GrupoF\\ProjetoES\\CaracterizaçãoDasSalas.csv"), new JFrame()).setVisible(true);
    }


}
