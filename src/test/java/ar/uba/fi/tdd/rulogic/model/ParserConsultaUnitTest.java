package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class ParserConsultaUnitTest {

	@InjectMocks
        private static ParserConsulta parserConsulta;

        @BeforeClass
        public static void beforeClass() {
            parserConsulta = new ParserConsulta();            
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test() {
                String linea = "varon(juan).";
                Consulta consultaParseada = parserConsulta.parsearLinea(linea);
                
                String[] valores = {"juan"};
                Consulta consultaEsperada = new Consulta("varon", valores);
		Assert.assertTrue(consultaParseada.equals(consultaEsperada));
	}
        
        	@Test
	public void test2() {
                String linea = "padre(roberto, alejandro).";
                Consulta consultaParseada = parserConsulta.parsearLinea(linea);
                
                String[] valores = {"roberto", "alejandro"};
                Consulta consultaEsperada = new Consulta("padre", valores);
		Assert.assertTrue(consultaParseada.equals(consultaEsperada));
	}
                
}