package ar.uba.fi.tdd.rulogic.model;

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