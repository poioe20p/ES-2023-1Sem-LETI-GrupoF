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
        Assertions.assertEquals("title",frame.getTitle() );
        Assertions.assertEquals( Color.white,frame.getBackground());
        Assertions.assertEquals( JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        Assertions.assertEquals(JFrame.MAXIMIZED_BOTH,frame.getExtendedState() );
        Assertions.assertEquals(1,frame.getWindowListeners().length);
        LayoutDefinable.setVisibility(frame, true);
        Assertions.assertTrue(frame.isVisible());
        LayoutDefinable.setVisibility(frame, false);
        Assertions.assertFalse(frame.isVisible());
    }

    @Test
    void testDefineButtonLayout() {
        JButton button = LayoutDefinable.defineButtonLayout(Color.white, Color.black, "text", new Dimension(10, 10));
        Assertions.assertNotNull(button);
        Assertions.assertEquals( Color.white,button.getBackground());
        Assertions.assertEquals(Color.black,button.getForeground() );
        Assertions.assertEquals( "text",button.getText());
        Assertions.assertEquals(new Dimension(10, 10),button.getPreferredSize() );
    }

    @Test
    void testDefineTextFieldLayout() {
        JTextField textField = LayoutDefinable.defineTextFieldLayout("text", "font", 10, 10, Color.white, Color.black);
        Assertions.assertNotNull(textField);
        Assertions.assertEquals(Color.white,textField.getBackground() );
        Assertions.assertEquals( Color.black,textField.getForeground());
        Assertions.assertEquals( "text", textField.getText());
        Assertions.assertEquals("font",textField.getFont().getName());
        Assertions.assertEquals( 10,textField.getFont().getSize());
     }

    @Test
    void testDefineTextAreaLayout() {
        JTextArea textArea = LayoutDefinable.defineTextAreaLayout("text", "font",
                10, 10, Color.white, Color.black);
        Assertions.assertNotNull(textArea);
        Assertions.assertEquals(Color.white, textArea.getBackground());
        Assertions.assertEquals( Color.black,textArea.getForeground());
        Assertions.assertEquals("text",textArea.getText());
        Assertions.assertEquals( "font",textArea.getFont().getName());
        Assertions.assertEquals( 10,textArea.getFont().getSize());
    }



}
