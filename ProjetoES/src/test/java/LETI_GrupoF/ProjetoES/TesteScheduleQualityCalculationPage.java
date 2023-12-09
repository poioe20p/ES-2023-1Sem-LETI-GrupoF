package LETI_GrupoF.ProjetoES;

import LETI_GrupoF.ProjetoES.user_interface.ScheduleQualityCalculationPage;
import LETI_GrupoF.ProjetoES.user_interface.ScheduleQualityTable;
import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TesteScheduleQualityCalculationPage {

 Horario horario = new Horario("C:\\Users\\themo\\OneDrive\\Documents\\GitHub\\ES-2023-1Sem-LETI-GrupoF\\ProjetoES\\HorarioDeExemplo.csv");
 List<String> userVariables = new ArrayList<>(List.of("Inscritos no turno", "Sala Atribuida", "Características da sala atribuída para a aula"
 , "Capacidade Normal", "Capacidade Exame"));
    ScheduleQualityCalculationPage scheduleQualityCalculationPage =
            new ScheduleQualityCalculationPage(userVariables, new JFrame(), horario);
    @Test
    void testGetScheduleQualityTable() {
        Assertions.assertNotNull(scheduleQualityCalculationPage.getCalculateScheduleQualityButton());
    }

}
