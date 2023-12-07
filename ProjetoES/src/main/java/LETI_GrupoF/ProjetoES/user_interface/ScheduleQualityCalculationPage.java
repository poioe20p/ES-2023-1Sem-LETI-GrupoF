package LETI_GrupoF.ProjetoES.user_interface;

import LETI_GrupoF.ProjetoES.Horario;
import LETI_GrupoF.ProjetoES.Metrica;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ScheduleQualityCalculationPage extends JFrame implements LayoutDefinable {

    private JButton calculateScheduleQuality;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JComboBox<String> listOfMatOperators;
    private JComboBox<String> listOfMatOperators2;
    private JTextField integerField;
    private JComboBox<String> listOfVariables;
    private JComboBox<String> listOfVariables2;
    private DefaultListModel<String> variableListModel;
    private List<String> metricas = new ArrayList<>();
    private Horario horario;
    private List<String> variablesForFormula = new ArrayList<>();
    private boolean isListAltered = false;



    public ScheduleQualityCalculationPage(List<String> variablesForFormula, JFrame previousFrame, Horario horario) {
        LayoutDefinable.basicLayout("Schedule Quality", this, Color.darkGray);
        setLayout(new BorderLayout());

        this.horario = horario;
        this.variablesForFormula = variablesForFormula;

        // Panel que corresponde ao separador que contém os elementos para criação de variaveis
        JPanel createFormulaPanel = new JPanel(new GridLayout(2,1));

        gbc = resetGBC(gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        createFormulaPanel.add(topFormulaCreatingPanel(variablesForFormula));
        gbc.gridy = 1;
        createFormulaPanel.add(bottomFormulaCreatingPanel(previousFrame));

//        Panel que corresponde ao separador que contém o display das fórmulas criadas
        JPanel createdFormulaDisplayPanel = createdFormulasDisplayPanel();
        JTabbedPane scheduleQualityTabs = new JTabbedPane();
        scheduleQualityTabs.add("Formula Creation Tab", createFormulaPanel);
        scheduleQualityTabs.add("Created Formulas", createdFormulaDisplayPanel);

        add(scheduleQualityTabs, BorderLayout.CENTER);
    }

    private JPanel createdFormulasDisplayPanel() {
        JPanel formulaListDisplayPanel = new JPanel(new GridBagLayout());
        formulaListDisplayPanel.setBackground(Color.darkGray);

        //Elementos do panel que corresponde ao separador que contém o display das fórmulas criadas
        variableListModel = new DefaultListModel<>();
        JList<String> metricList = new JList<>(variableListModel);

        JScrollPane metriclistPane = new JScrollPane(metricList);
        metriclistPane.setFont((new Font("Arial", Font.BOLD, 60)));

        JLabel paneLabel = new JLabel("Your Created Formulas");
        paneLabel.setFont(new Font("Arial", Font.BOLD, 30));
        paneLabel.setBackground(Color.darkGray);

        gbc = resetGBC(gbc);
        gbc.gridwidth = 2;
        formulaListDisplayPanel.add(paneLabel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.gridy = 1;
        formulaListDisplayPanel.add(metriclistPane, gbc);
        return formulaListDisplayPanel;
    }

    private JPanel topFormulaCreatingPanel(List<String> variablesForFormula) {
        JPanel formulaCreatingPanel = new JPanel(new GridBagLayout());
        formulaCreatingPanel.setBackground(Color.darkGray);
        gbc = resetGBC(gbc);

        JTextArea pageFunctionalityDescription = LayoutDefinable.defineTextAreaLayout("Choose variables and mathematical operatores to create a metric for schedule quality " +
                "calculation.", "Arial", Font.PLAIN, 25, Color.darkGray, Color.WHITE);
        variablesForFormula.add(0, "Variable");
        listOfVariables = new JComboBox<>(variablesForFormula.toArray(new String[0]));
        listOfVariables.setPreferredSize(new Dimension(200, 40));
        listOfVariables2 = new JComboBox<>(variablesForFormula.toArray(new String[0]));
        listOfVariables2.setPreferredSize(new Dimension(200, 40));
        updateLists();

        listOfMatOperators = new JComboBox<>(new String[]{"Operation", "=", "!=", "+", "-", "*", "/"});
        listOfMatOperators.setPreferredSize(new Dimension(200, 40));
        listOfMatOperators2 = new JComboBox<>(new String[] {"Condition Operator (Optional)", ">", "<", "="});
        listOfMatOperators2.setPreferredSize(new Dimension(200, 40));

        //Permite garantir que o utilizador apenas entra um inteiro na ultima coluna
        integerField = new JTextField("Number (Optional)");
        integerField.setColumns(13);


        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        formulaCreatingPanel.add(pageFunctionalityDescription, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;
        formulaCreatingPanel.add(listOfVariables, gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(listOfMatOperators, gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(listOfVariables2, gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(listOfMatOperators2, gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(integerField, gbc);;
        return formulaCreatingPanel;
    }

    private JPanel bottomFormulaCreatingPanel(JFrame previousFrame) {
        JPanel formulaCreatingPanel = new JPanel(new GridBagLayout());
        formulaCreatingPanel.setBackground(Color.darkGray);
        gbc = resetGBC(gbc);

        JButton removeLastAddedFormula = LayoutDefinable.defineButtonLayout(Color.RED,
                Color.white, "Remove Formula", new Dimension(130, 40));
        removeLastAddedFormula.addActionListener(e -> updateFormulaListPane(-1,  null));
        JButton addFormula = LayoutDefinable.defineButtonLayout(Color.GREEN,
                Color.WHITE, "Add Formula", new Dimension(130, 40));
        addFormula.addActionListener(e -> updateFormulaListPane(1, getVariablesFromFormula()));
        calculateScheduleQuality = LayoutDefinable.defineButtonLayout(Color.BLUE,
                Color.WHITE, "Schedule Quality", new Dimension(130, 40));
        JButton goBackButton = LayoutDefinable.defineButtonLayout(Color.RED,
                Color.white, "Go Back", new Dimension(130, 40));
        goBackButton.addActionListener(e -> {
            previousFrame.setVisible(true);
            LayoutDefinable.setVisibility(this, false);
        });

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(15, 15, 15, 15);
        formulaCreatingPanel.add(addFormula,gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(removeLastAddedFormula, gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(calculateScheduleQuality, gbc);
        gbc.gridx++;
        formulaCreatingPanel.add(goBackButton, gbc);
        return formulaCreatingPanel;
    }

    private void updateFormulaListPane(int operation, List<String> formula) {
        StringBuilder formulaEmString = new StringBuilder("Métrica: ");
        StringBuilder metrica = new StringBuilder();
        if(operation == -1) {
            if (!variableListModel.isEmpty()) variableListModel.remove(variableListModel.size() - 1);
        }
        else {
            for(int i = 0; i < formula.size(); i++) {
                formulaEmString.append(formula.get(i)).append(" ");
                if (i != (formula.size() - 1)) {
                    metrica.append(formula.get(i)).append(";");
                } else {
                    metrica.append(formula.get(i));
                }
            }
            metricas.add(metrica.toString());
            variableListModel.addElement(String.valueOf(formulaEmString));
        }
    }

    private List<String> getVariablesFromFormula() {
        List<String> formula = new ArrayList<>();
        String variable1 = (String) listOfVariables.getSelectedItem();
        String variable2 = (String) listOfVariables2.getSelectedItem();
        String matOperator1 = (String) listOfMatOperators.getSelectedItem();
        String matOperator2 = (String) listOfMatOperators2.getSelectedItem();
        String input = integerField.getText();

        if(Stream.of(variable1, variable2, matOperator1).filter(Objects::nonNull).anyMatch(String::isBlank)) {
            JOptionPane.showMessageDialog(this, "Please make sure that at least all variables and the mat operator are selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if(!matOperator1.isBlank() && input != null) {
                Stream.of(variable1, matOperator1, variable2, matOperator2, input).forEach(formula::add);
            }
            else {
                Stream.of(variable1, matOperator1, variable2).forEach(formula::add);
            }
        }
        return formula;
    }

    public JButton getCalculateScheduleQualityButton() {
        return calculateScheduleQuality;
    };


    public List<Metrica> getScheduleMetrics() {
        List<Metrica> metricasDoUtilizador = new ArrayList<>();
        for(String metrica : metricas) {
            metricasDoUtilizador.add(new Metrica(metrica));
        }
        return metricasDoUtilizador;
    }

    /**
     * Este metodo verifica se a variavel selecionada pelo utilizador tem valores inteiros associados ou não,
     * devolvendo true se tiver e false caso contrário.
     *
     * @param metricVariable variavel selecionada pelo utilizador
     * @return true se a variavel selecionada pelo utilizador tiver valores inteiros associados e false caso contrário
     */
    private boolean hasIntegerValues(String metricVariable) {
        List<List<String>> horarioCompleto = horario.getHorario();
        for (String header : horario.getColumnTitles()) {
            if(metricVariable.equals(header)) {
                try {
                    Integer.parseInt(horarioCompleto.get(0).get(horario.getColumnTitles().indexOf(header)));
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Este metodo atualiza as listas de variaveis e operadores matematicos de acordo com as variaveis que o utilizador
     * seleciona tendo em conta o tipo de variavel selecionada na lista. Pois pretende-se que o utilizador apenas possa fazer operaçõe com varivaeis do mesmo tipo.
     *
     */
    private void updateLists() {
        JComboBox<String> stringOperation = new JComboBox<>(new String[] {"String Operation", "=", "!="});
        stringOperation.setPreferredSize(new Dimension(200, 40));
        JComboBox<String> mathOperation = new JComboBox<>(new String[]{"Mathematical Operation", "+", "-", "*", "/"});
        mathOperation.setPreferredSize(new Dimension(200, 40));
        List<String> auxVariablesForFormula = new ArrayList<>(variablesForFormula);
        listOfVariables.addActionListener(e -> {
            String variable = (String) listOfVariables.getSelectedItem();
            if(!Objects.equals(variable, "Variable")) {
                auxVariablesForFormula.remove(variable);
                listOfVariables2.setModel(new DefaultComboBoxModel<>(auxVariablesForFormula.toArray(new String[0])));
                if(hasIntegerValues(variable)) {
                    listOfMatOperators.setModel(new DefaultComboBoxModel<>(new String[]{"Operation", "+", "-", "*", "/"}));
                    listOfVariables2.setModel(new DefaultComboBoxModel<>(auxVariablesForFormula.toArray(new String[0])));
                    isListAltered = true;
                }
                else {
                    listOfMatOperators.setModel(new DefaultComboBoxModel<>(new String[]{"Operation", "-", "=", "!="}));
                    auxVariablesForFormula.remove("Capacidade Normal");
                    auxVariablesForFormula.remove("Capacidade Exame");
                    auxVariablesForFormula.removeIf(this::hasIntegerValues);
                    listOfVariables2.setModel(new DefaultComboBoxModel<>(auxVariablesForFormula.toArray(new String[0])));
                    isListAltered = true;
                }
            }
            else {
//                isListAltered = false;
                if(isListAltered) {
                    listOfVariables2.setModel(new DefaultComboBoxModel<>(variablesForFormula.toArray(new String[0])));
                    listOfMatOperators.setModel(new DefaultComboBoxModel<>(new String[]{"Operation", "=", "!=", "+", "-", "*", "/"}));
                }
            }
        });

        listOfVariables2.addActionListener(e -> {
            String variable = (String) listOfVariables2.getSelectedItem();
            if(!Objects.equals(variable, "Variable")) {
                auxVariablesForFormula.remove(variable);
                listOfVariables.setModel(new DefaultComboBoxModel<>(auxVariablesForFormula.toArray(new String[0])));
                if(hasIntegerValues(variable)) {
                    listOfMatOperators.setModel(new DefaultComboBoxModel<>(new String[]{"Operation", "+", "-", "*", "/"}));
                    listOfVariables.setModel(new DefaultComboBoxModel<>(auxVariablesForFormula.toArray(new String[0])));
                    isListAltered = true;
                }
                else {
                    listOfMatOperators.setModel(new DefaultComboBoxModel<>(new String[]{"Operation", "-", "=", "!="}));
                    auxVariablesForFormula.remove("Capacidade Normal");
                    auxVariablesForFormula.remove("Capacidade Exame");
                    auxVariablesForFormula.removeIf(this::hasIntegerValues);
                    listOfVariables.setModel(new DefaultComboBoxModel<>(auxVariablesForFormula.toArray(new String[0])));
                    isListAltered = true;
                }
            }
            else {
//                isListAltered = false;
                if(isListAltered) {
                    listOfVariables.setModel(new DefaultComboBoxModel<>(variablesForFormula.toArray(new String[0])));
                    listOfMatOperators.setModel(new DefaultComboBoxModel<>(new String[]{"Operation", "=", "!=", "+", "-", "*", "/"}));
                }
            }
        });
        auxVariablesForFormula.clear();
        auxVariablesForFormula.addAll(variablesForFormula);
    }

    public static void main(String[] args) {
        List<String> variaveis = new ArrayList<>(List.of("AAAA", "SDKJFN", "AKDNSJ", "AKJDS"));
        JFrame frame = new JFrame();
//        ScheduleQualityCalculationPage sqcp = new ScheduleQualityCalculationPage(variaveis,  frame, new Horario("ProjetoES/HorarioRemoto.csv"));
//        LayoutDefinable.setVisibility(sqcp, true);
    }

}
