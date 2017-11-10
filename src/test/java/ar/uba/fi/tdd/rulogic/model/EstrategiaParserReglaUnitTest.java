package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class EstrategiaParserReglaUnitTest {

    @InjectMocks
    private static EstrategiaParserRegla estrategiaParserRegla;

    @BeforeClass
    public static void beforeClass() {
        estrategiaParserRegla = new EstrategiaParserRegla();            
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void parsear_linea_valida() {
        String linea = "hijo(X, Y) :- varon(X), padre(Y, X)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        String[] parametros = {"X", "Y"};
        List<ConsultaParametrica> listaCP = new ArrayList<ConsultaParametrica>();
        listaCP.add(new ConsultaParametrica("varon", new String[]{"X"}));
        listaCP.add(new ConsultaParametrica("padre", new String[]{"Y", "X"}));
        
        Evaluable regla_esperada = new Regla("hijo", parametros, listaCP);
        Assert.assertEquals(regla_parseada, regla_esperada);
    }
    
    @Test
    public void parsear_linea_invalida() {
        String linea = "hijo(X, Y@@varon(X), padre(Y, X)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        Assert.assertNull(regla_parseada);
    }
    
    @Test
    public void parsear_linea_invalida_2() {
        String linea = "hijo(X, Y) :- varon(X), padre(Y, X@@)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        Assert.assertNull(regla_parseada);
    }

    @Test
    public void parsear_linea_invalida_3() {
        String linea = "hijo(|@#, Y) :- varon(X), padre(Y, X)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        Assert.assertNull(regla_parseada);
    }
    
    @Test
    public void parsear_linea_invalida_4() {
        String linea = "hijo(XXX, Y) :- varon(X), padre(Y, X)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        Assert.assertNull(regla_parseada);
    }
    
    @Test
    public void parsear_linea_invalida_5() {
        String linea = "hijo(XXX, Y) :- varon(X), padre(Y, XYZ)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        Assert.assertNull(regla_parseada);
    }
    
    @Test
    public void parsear_linea_invalida_6() {
        String linea = "hijo(X, Y) :- varon(X), padre(Y, XYZ)";
        Evaluable regla_parseada = estrategiaParserRegla.parsearLinea(linea);
        
        Assert.assertNull(regla_parseada);
    }
               
    @Test
    public void es_regla_valida_test() {
        String linea = "hijo(X, Y) :- varon(X), padre(Y, X)";
        Assert.assertTrue(estrategiaParserRegla.esLineaValida(linea));
    }

    @Test
    public void es_regla_invalida_test() {
        String linea = "hijo(X, Y) :- varon(X), padre(Y, X";    
        Assert.assertFalse(estrategiaParserRegla.esLineaValida(linea));
    }
                
}