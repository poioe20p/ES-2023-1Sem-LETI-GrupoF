package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ScheduleQualityCalculationPage extends JFrame implements LayoutDefinable {

    private JTextField classDesignationTextField;
    private JButton calculateScheduleQuality;
    private GridBagConstraints gbc = new GridBagConstraints();
    private JComboBox<String> listOfMatOperators;
    private JComboBox<String> listOfMatOperators2;
    private JTextField integerField;
    private JComboBox<String> listOfVariables;
    private JComboBox<String> listOfVariables2;
    private DefaultListModel<String> variableListModel;

    private List<String> metricas = new ArrayList<>();




    public ScheduleQualityCalculationPage(List<String> variablesForFormula, JFrame previousFrame) {
        LayoutDefinable.basicLayout("Schedule Quality", this, Color.darkGray);
        setLayout(new BorderLayout());

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

        listOfMatOperators = new JComboBox<>(new String[] {"Mathematical Operation","+", "-", "*", "/" });
        listOfMatOperators.setPreferredSize(new Dimension(200, 40));
        listOfMatOperators2 = new JComboBox<>(new String[] {"Condition Operator (Optional) ", ">", "<", "="});
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

    public List<String> getScheduleMetrics() { return metricas;}

    public static void main(String[] args) {
        List<String> variaveis = new ArrayList<>(List.of("AAAA", "SDKJFN", "AKDNSJ", "AKJDS"));
        JFrame frame = new JFrame();
        ScheduleQualityCalculationPage sqcp = new ScheduleQualityCalculationPage(variaveis,  frame);
        LayoutDefinable.setVisibility(sqcp, true);
    }

}
