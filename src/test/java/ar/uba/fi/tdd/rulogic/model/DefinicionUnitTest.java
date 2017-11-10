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
	public void consulta_definicion_deberia_ser_true_test() {
            Evaluable d = new Definicion("padre", new String[]{"roberto", "alejandro"});
            Consulta c = new Consulta("padre", new String[]{"roberto", "alejandro"});
            
            Assert.assertTrue(d.evaluar(c, null));
	}
        
        @Test
        public void consulta_definicion_deberia_ser_false() {
            Evaluable d = new Definicion("padre", new String[]{"roberto", "alejandro"});
            Consulta c = new Consulta("padre", new String[]{"roberto", "juan"});
            
            Assert.assertFalse(d.evaluar(c, null));            
        }
        
                
        @Test
        public void consulta_definicion_deberia_ser_false2() {
            Evaluable d = new Definicion("padre", new String[]{"roberto", "alejandro"});
            Consulta c = new Consulta("abuelo", new String[]{"roberto", "alejandro"});
            
            Assert.assertFalse(d.evaluar(c, null));            
        }
        
        @Test
        public void consulta_definicion_deberia_ser_false3() {
            Evaluable d = new Definicion("padre", new String[]{"roberto", "alejandro"});
            Consulta c = new Consulta("padre", new String[]{"roberto", "alejandro", "pepe"});
            
            Assert.assertFalse(d.evaluar(c, null));            
        }
        
        @Test
	public void son_definiciones_diferentes_1() {
            Definicion d1 = new Definicion("varon", new String[]{"juan"});
            Definicion d2 = new Definicion("varon", new String[]{"marcos"});
            Assert.assertNotEquals(d1, d2);
	}
        
        @Test
	public void son_definiciones_diferentes_2() {
            Definicion d1 = new Definicion("varon", new String[]{"juan"});
            Definicion d2 = new Definicion("mujer", new String[]{"juan"});
            Assert.assertNotEquals(d1, d2);
	}
        @Test
	public void son_definiciones_diferentes_3() {
            Definicion d1 = new Definicion("varon", new String[]{"juan"});
            Definicion d2 = new Definicion("mujer", new String[]{"marcos"});
            Assert.assertNotEquals(d1, d2);
	}
}