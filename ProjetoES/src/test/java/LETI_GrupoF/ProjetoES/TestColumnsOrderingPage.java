package LETI_GrupoF.ProjetoES;

import LETI_GrupoF.ProjetoES.user_interface.ColumnsOrderingPage;
import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TestColumnsOrderingPage {

    JFrame previousFrame = new JFrame();
    List<String> userColumnTitles = new ArrayList<>(List.of(
            "Curso", "Unidade Curricular", "Turno", "Turma", "Inscritos no turno",
            "Dia da semana", "Hora de início da aula", "Hora de fim da aula", "Data da aula",
            "Caracaterísticas da sala atribuída para a aula", "Sala de aula atribuída"));


//    @Test
//    void testGetOpenScheduleButton() {
//        ColumnsOrderingPage columnsOrderingPage = new ColumnsOrderingPage(userColumnTitles, previousFrame);
//        Assert.columnsOrderingPage.getOpenScheduleButton();
//    }
}
