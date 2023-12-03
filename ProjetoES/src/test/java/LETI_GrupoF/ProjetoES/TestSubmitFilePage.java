package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.user_interface.SubmitFilePage;

class TestSubmitFilePage {
	static SubmitFilePage uf;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		uf = new SubmitFilePage();
	}

	@Test
	void testUserForm() {
		assertNotNull(uf);
	}

	@Test
	void testGetCsvFileLocation() {
		assertNotNull(uf.getCsvFileLocationTextField());
	}
//
//	@Test
////	void testGetSubmitFileButton() {
////		assertNotNull(uf.getOpenScheduleButton());
////	}


}
