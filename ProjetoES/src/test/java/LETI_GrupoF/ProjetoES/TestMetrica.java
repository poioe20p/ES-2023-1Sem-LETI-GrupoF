package LETI_GrupoF.ProjetoES;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestMetrica {
static Metrica metrica;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	metrica= new Metrica("Inscritos no turno - Capacidade Normal > 0");
	
	
	}

	void testMetrica() {
        assertNotNull(metrica);
    }

    @Test
    void testAulasComContribuicao() {
        assertNotNull(metrica.getAulasComComtribuicao());

       
    }

    @Test
    void testComponentesFormula() {
        assertNotNull(metrica.getComponentesFormula());
    }

    @Test
    void testFormula() {
        assertNotNull(metrica.getFormula());

       
    }

    @Test
    void testTotal() {
        assertNotNull(metrica.getTotal());

        
       
    }
}
