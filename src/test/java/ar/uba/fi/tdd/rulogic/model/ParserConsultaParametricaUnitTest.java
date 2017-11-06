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
            Assert.assertTrue(parserConsultaParametrica.esConsultaParametricaValida(linea));
	}
        
        @Test
	public void es_consulta_parametrica_invalida_test() {
            String linea = "padre(@, Y)";    
            Assert.assertFalse(parserConsultaParametrica.esConsultaParametricaValida(linea));
	}
//                
//        @Test
//        public void intentar_parsear_consulta_invalida_test() {
//            String linea = "padre(@,Y)";
//            
//            Consulta c = parserConsulta.parsearLinea(linea);
//            Assert.assertNull(c);            
//        }
}