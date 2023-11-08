package LETI_GrupoF.ProjetoES;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestHtmlCreator.class, TestReader.class, TestUserForm.class, TestUserInteraction.class })

public class AllTests {

}
