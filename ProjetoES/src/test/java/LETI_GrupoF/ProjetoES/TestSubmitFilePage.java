package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.user_interface.SubmitFilePage;

class TestSubmitFilePage {

	@Test
	void testCsvFileLocationTextField() {
		SubmitFilePage submitFilePage = new SubmitFilePage();
		assertNotNull(submitFilePage.getCsvFileLocationTextField());
	}

	@Test
	void testContinueButton() {
		SubmitFilePage submitFilePage = new SubmitFilePage();
		assertNotNull(submitFilePage.getContinueButton());
	}

	@Test
	void testIsRemoteFile() {
		SubmitFilePage submitFilePage = new SubmitFilePage();
		assertFalse(submitFilePage.isRemoteFile()); // Assuming default is false
	}

	@Test
	void testSetRemoteFile() {
		SubmitFilePage submitFilePage = new SubmitFilePage();
		submitFilePage.setRemoteFile(true);
		assertTrue(submitFilePage.isRemoteFile());
	}
}
