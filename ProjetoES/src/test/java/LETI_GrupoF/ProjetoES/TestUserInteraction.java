package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import LETI_GrupoF.ProjetoES.user_interface.UserInteraction;

class TestUserInteraction {
	static UserInteraction ui;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ui = new UserInteraction();
	}

	@Test
	void testUserInteraction() {
		assertNotNull(ui);
	}

}
