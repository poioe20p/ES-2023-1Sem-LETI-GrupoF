package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.user_interface.UserForm;

class TestUserForm {
	static UserForm uf;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		uf = new UserForm();
	}

	@Test
	void testUserForm() {
		assertNotNull(uf);
	}

	@Test
	void testGetCsvFileLocation() {
		assertNotNull(uf.getCsvFileLocation());
	}

	@Test
	void testGetSubmitFileButton() {
		assertNotNull(uf.getSubmitFileButton());
	}

}
