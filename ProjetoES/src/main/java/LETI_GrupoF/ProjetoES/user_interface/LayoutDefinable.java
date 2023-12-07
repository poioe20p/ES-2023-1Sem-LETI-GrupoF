package LETI_GrupoF.ProjetoES.user_interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public interface LayoutDefinable {

    default GridBagConstraints resetGBC (GridBagConstraints gbc) {
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        return gbc;
    }

    static Color getColor(String color) {
        return switch (color) {
            case "white" -> Color.white;
            case "black" -> Color.black;
            case "red" -> new Color(130,0,0);
            case "green" -> new Color(0,88,0);
            case "blue" -> new Color(0,30,180);
            case "yellow" -> Color.yellow;
            case "cyan" -> Color.cyan;
            case "magenta" -> Color.magenta;
            case "orange" -> Color.orange;
            case "pink" -> Color.pink;
            case "gray" -> new Color(65,65,65);
            default -> Color.darkGray;
        };
    }

    static void basicLayout(String title, JFrame frame, Color color) {
        frame.setTitle(title);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setLayout(new GridBagLayout());
        frame.setBackground(color);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
    }

    static JButton defineButtonLayout(Color backgroundColor, Color foregroundColor, String text, Dimension dimension) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setPreferredSize(dimension);
        return button;
    }

    static JTextField defineTextFieldLayout(String text, String fontName, int font, int size, Color backgroundColor, Color foregroundColor) {
        JTextField textField = new JTextField(text);
        textField.setFont(new Font(fontName, font, size));
        textField.setBackground(backgroundColor);
        textField.setForeground(foregroundColor);
        textField.setEditable(false);
        textField.setOpaque(false);
        return textField;
    }

    static JTextArea defineTextAreaLayout(String text, String fontName, int font, int size, Color backgroundColor, Color foregroundColor) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font(fontName, font, size));
        textArea.setBackground(backgroundColor);
        textArea.setForeground(foregroundColor);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(false);
        return textArea;
    }

    static void setVisibility(JFrame jframe, boolean isVisible) {
        jframe.setVisible(isVisible);
    }
}
