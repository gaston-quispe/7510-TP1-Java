package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class ParserConsultaParametricaUnitTest {

    	@InjectMocks
        private static ParserConsultaParametrica parserConsultaParametrica;

        @BeforeClass
        public static void beforeClass() {
            parserConsultaParametrica = new ParserConsultaParametrica();            
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}
        
        @Test
	public void es_consulta_parametrica_valida_test() {
            String linea = "padre(X, Y)";    
            Assert.assertTrue(parserConsultaParametrica.esLineaValida(linea));
	}
        
                
        @Test
	public void crear_consulta_parametrica_valida_test() {
            String linea = "padre(X, Y)";
            ConsultaParametrica consulta_parametrica_parseada = parserConsultaParametrica.parsearLinea(linea);
            ConsultaParametrica consulta_parametrica_esperada = new ConsultaParametrica("padre", new String[]{"X", "Y"});
            
            Assert.assertEquals(consulta_parametrica_parseada, consulta_parametrica_esperada);
	}
        
        @Test
	public void es_consulta_parametrica_invalida_test() {
            String linea = "padre(@, Y)";    
            Assert.assertFalse(parserConsultaParametrica.esLineaValida(linea));
	}
        
        @Test
	public void crear_consulta_parametrica_invalida_test() {
            String linea = "padre(@, Y)";
            ConsultaParametrica cp = parserConsultaParametrica.parsearLinea(linea);
            Assert.assertNull(cp);
	}
        
        @Test
	public void son_consultas_parametricas_diferentes_1() {
            ConsultaParametrica c1 = new ConsultaParametrica("padre", new String[]{"X", "Y"});
            ConsultaParametrica c2 = new ConsultaParametrica("padre", new String[]{"X", "Z"});
            Assert.assertNotEquals(c1, c2);
	}
        
        @Test
	public void son_consultas_parametricas_diferentes_2() {
            ConsultaParametrica c1 = new ConsultaParametrica("padre", new String[]{"X", "Y"});
            ConsultaParametrica c2 = new ConsultaParametrica("madre", new String[]{"X", "Y"});
            Assert.assertNotEquals(c1, c2);
	}
        @Test
	public void son_consultas_parametricas_diferentes_3() {
            ConsultaParametrica c1 = new ConsultaParametrica("padre", new String[]{"X", "Y"});
            ConsultaParametrica c2 = new ConsultaParametrica("madre", new String[]{"X", "Z"});
            Assert.assertNotEquals(c1, c2);
	}

}