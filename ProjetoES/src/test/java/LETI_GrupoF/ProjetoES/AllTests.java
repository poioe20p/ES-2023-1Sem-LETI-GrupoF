package LETI_GrupoF.ProjetoES;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestHorario.class, TestHtmlCreator.class, TestMetrica.class, TestReader.class, TestSala.class, TestSalas.class,
	TesteLayoutDefinable.class, TesteScheduleQualityCalculationPage.class, TesteScheduleQualityTable.class, TestSubmitFilePage.class,
	TestUserInteraction.class })

public class AllTests {

}
