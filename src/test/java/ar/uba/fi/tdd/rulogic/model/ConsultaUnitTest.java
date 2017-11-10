package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsultaUnitTest {

        @BeforeClass
        public static void beforeClass() {        
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void crear_consulta_valida() {
            Consulta c = new Consulta("varon", new String[]{"juan"});
            Assert.assertNotNull(c);
	}
        
        @Test
	public void son_consultas_diferentes_1() {
            Consulta c1 = new Consulta("varon", new String[]{"juan"});
            Consulta c2 = new Consulta("varon", new String[]{"marcos"});
            Assert.assertNotEquals(c1, c2);
	}
        
        @Test
	public void son_consultas_diferentes_2() {
            Consulta c1 = new Consulta("varon", new String[]{"juan"});
            Consulta c2 = new Consulta("mujer", new String[]{"juan"});
            Assert.assertNotEquals(c1, c2);
	}
        @Test
	public void son_consultas_diferentes_3() {
            Consulta c1 = new Consulta("varon", new String[]{"juan"});
            Consulta c2 = new Consulta("mujer", new String[]{"marcos"});
            Assert.assertNotEquals(c1, c2);
	}
}