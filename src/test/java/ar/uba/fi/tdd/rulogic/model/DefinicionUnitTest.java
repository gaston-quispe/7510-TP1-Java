package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefinicionUnitTest {

        @BeforeClass
        public static void beforeClass() {        
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void consultar_definicion_test() {
            Evaluable d = new Definicion("padre", new String[]{"roberto", "alejandro"});
            Consulta c = new Consulta("padre", new String[]{"roberto", "alejandro"});
            
            Assert.assertTrue(d.evaluar(c, null));
	}
        
}