package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.user_interface.SubmitFilePage;

class TestSubmitFilePage {

	@Test
	void testCsvFileLocationTextField() {
		UserInteraction userInteraction = new UserInteraction();
		SubmitFilePage submitFilePage = (SubmitFilePage) userInteraction.getSubmitFilePage();
		assertNotNull(submitFilePage.getCsvFileLocationTextField());
	}

//	@Test
//	void testContinueButton() {
//		assertNotNull(submitFilePage.getContinueButton());
//	}

	@Test
	void testIsRemoteFile() {
		UserInteraction userInteraction = new UserInteraction();
		SubmitFilePage submitFilePage = (SubmitFilePage) userInteraction.getSubmitFilePage();
		assertFalse(submitFilePage.isRemoteFile()); // Assuming default is false
	}

	@Test
	void testSetRemoteFile() {
		UserInteraction userInteraction = new UserInteraction();
		SubmitFilePage submitFilePage = (SubmitFilePage) userInteraction.getSubmitFilePage();
		submitFilePage.setRemoteFile(true);
		assertTrue(submitFilePage.isRemoteFile());
	}
}
