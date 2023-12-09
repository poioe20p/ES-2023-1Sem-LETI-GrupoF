package LETI_GrupoF.ProjetoES;

import LETI_GrupoF.ProjetoES.user_interface.LayoutDefinable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TesteLayoutDefinable {

    public class testingFrame implements LayoutDefinable {

    }

    @Test
    void testResetGBC() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        testingFrame test = new testingFrame();
        Assertions.assertNotNull(test.resetGBC(gridBagConstraints));
    }

    @Test
    void notNullColor() {
        List<Color> colors = new ArrayList<>();
        colors.add(LayoutDefinable.getColor("white"));
        colors.add(LayoutDefinable.getColor("black"));
        colors.add(LayoutDefinable.getColor("red"));
        colors.add(LayoutDefinable.getColor("green"));
        colors.add(LayoutDefinable.getColor("blue"));
        colors.add(LayoutDefinable.getColor("yellow"));
        colors.add(LayoutDefinable.getColor("cyan"));
        colors.add(LayoutDefinable.getColor("magenta"));
        colors.add(LayoutDefinable.getColor("orange"));
        colors.add(LayoutDefinable.getColor("pink"));
        colors.add(LayoutDefinable.getColor("gray"));
        colors.add(LayoutDefinable.getColor("default"));
        for (Color color : colors) {
            Assertions.assertNotNull(color);
        }
    }

    @Test
    void testBasicLayout() {
        JFrame frame = new JFrame();
        testingFrame test = new testingFrame();
        LayoutDefinable.basicLayout("title", frame, Color.white);
        Assertions.assertEquals(frame.getTitle(), "title");
        Assertions.assertEquals(frame.getBackground(), Color.white);
        Assertions.assertEquals(frame.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
        Assertions.assertEquals(frame.getExtendedState(), JFrame.MAXIMIZED_BOTH);
        Assertions.assertEquals(frame.getWindowListeners().length, 1);
        LayoutDefinable.setVisibility(frame, true);
        Assertions.assertTrue(frame.isVisible());
        LayoutDefinable.setVisibility(frame, false);
        Assertions.assertFalse(frame.isVisible());
    }

    @Test
    void testDefineButtonLayout() {
        JButton button = LayoutDefinable.defineButtonLayout(Color.white, Color.black, "text", new Dimension(10, 10));
        Assertions.assertNotNull(button);
        Assertions.assertEquals(button.getBackground(), Color.white);
        Assertions.assertEquals(button.getForeground(), Color.black);
        Assertions.assertEquals(button.getText(), "text");
        Assertions.assertEquals(button.getPreferredSize(), new Dimension(10, 10));
    }

    @Test
    void testDefineTextFieldLayout() {
        JTextField textField = LayoutDefinable.defineTextFieldLayout("text", "font", 10, 10, Color.white, Color.black);
        Assertions.assertNotNull(textField);
        Assertions.assertEquals(textField.getBackground(), Color.white);
        Assertions.assertEquals(textField.getForeground(), Color.black);
        Assertions.assertEquals(textField.getText(), "text");
        Assertions.assertEquals(textField.getFont().getName(), "font");
        Assertions.assertEquals(textField.getFont().getSize(), 10);
     }

    @Test
    void testDefineTextAreaLayout() {
        JTextArea textArea = LayoutDefinable.defineTextAreaLayout("text", "font",
                10, 10, Color.white, Color.black);
        Assertions.assertNotNull(textArea);
        Assertions.assertEquals(textArea.getBackground(), Color.white);
        Assertions.assertEquals(textArea.getForeground(), Color.black);
        Assertions.assertEquals(textArea.getText(), "text");
        Assertions.assertEquals(textArea.getFont().getName(), "font");
        Assertions.assertEquals(textArea.getFont().getSize(), 10);
    }



}
