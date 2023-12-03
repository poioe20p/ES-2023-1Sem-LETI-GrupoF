package LETI_GrupoF.ProjetoES;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestHtmlCreator.class, TestReader.class, TestSubmitFilePage.class, TestUserInteraction.class, TestSchedule.class })

public class AllTests {

}
