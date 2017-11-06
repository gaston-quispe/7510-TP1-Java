package ar.uba.fi.tdd.rulogic.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

public class ParserConsultaUnitTest {
        
        @Rule
        public ExpectedException thrown = ExpectedException.none();
    
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

//	@Test
//	public void parsear_consulta_juan_con_un_valor_test() {
//                String linea = "varon(juan).";
//                Consulta consultaParseada = parserConsulta.parsearLinea(linea);
//                
//                String[] valores = {"juan"};
//                Consulta consultaEsperada = new Consulta("varon", valores);
//		Assert.assertTrue(consultaParseada.equals(consultaEsperada));
//	}
//        
//        @Test
//	public void parsear_consulta_roberto_con_dos_valores_test() {
//                String linea = "padre(roberto, alejandro).";
//                Consulta consultaParseada = parserConsulta.parsearLinea(linea);
//                
//                String[] valores = {"roberto", "alejandro"};
//                Consulta consultaEsperada = new Consulta("padre", valores);
//		Assert.assertTrue(consultaParseada.equals(consultaEsperada));
//	}
//        
//        @Test
//	public void parsear_consulta_maria_con_tres_valores_test() {
//                String linea = "maria(roberto, alejandro, pepe).";
//                Consulta consultaParseada = parserConsulta.parsearLinea(linea);
//                
//                String[] valores = {"roberto", "alejandro", "pepe"};
//                Consulta consultaEsperada = new Consulta("maria", valores);
//                
//		Assert.assertTrue(consultaParseada.equals(consultaEsperada));
//	}
        
        @Test//(expected = ParsingException.class) 
	public void es_consulta_valida_test() {
            String linea = "toto(roberto,alejandro,pepe)";    
            Assert.assertTrue(parserConsulta.esConsultaValida(linea));
	}
                
                
        @Test
        public void intentar_parsear_consulta_invalida_test() {
            String linea = "maria@roberto, alejandro, pepe)";
            
            thrown.expect(ParsingException.class);
            parserConsulta.parsearLinea(linea);
        }
}