package LETI_GrupoF.ProjetoES;

import LETI_GrupoF.ProjetoES.user_interface.ScheduleQualityCalculationPage;
import LETI_GrupoF.ProjetoES.user_interface.ScheduleQualityTable;
import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class TesteScheduleQualityCalculationPage {

    UserInteraction userInteraction = new UserInteraction();
    ScheduleQualityCalculationPage scheduleQualityCalculationPage = (ScheduleQualityCalculationPage) userInteraction.getSubmitFilePage();

//    @Test
//    void testOpenMetricScheduleButton() {
//        Assertions.assertNotNull(scheduleQualityCalculationPage.getCalculateScheduleQualityButton());
//    }

//    @Test
//    void testeScheduleMetricsNotNull() {
//        Assertions.assertNotNull(scheduleQualityCalculationPage.getScheduleMetrics());
//    }
}
