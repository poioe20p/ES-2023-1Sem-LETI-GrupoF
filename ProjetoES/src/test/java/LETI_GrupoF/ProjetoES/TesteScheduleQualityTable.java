package LETI_GrupoF.ProjetoES;

import LETI_GrupoF.ProjetoES.user_interface.ScheduleQualityTable;
import LETI_GrupoF.ProjetoES.user_interface.SubmitFilePage;
import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TesteScheduleQualityTable {

    UserInteraction userInteraction = new UserInteraction();
    ScheduleQualityTable scheduleQualityTable = (ScheduleQualityTable) userInteraction.getSubmitFilePage();

//    @Test
//    void testOpenMetricScheduleButton() {
//        assertNotNull(scheduleQualityTable.getOpenMetricScheduleButton());
//    }

//    @Test
//    void testTableNotNull() {
//        assertNotNull(scheduleQualityTable.getTable());
//    }

//    @Test
//    void testNotNullData() {
//        assertNotNull(scheduleQualityTable.getData());
//    }


}
