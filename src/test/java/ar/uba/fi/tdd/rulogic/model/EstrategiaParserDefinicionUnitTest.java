package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class EstrategiaParserDefinicionUnitTest {

    @InjectMocks
    private static EstrategiaParser estrategiaParserDefinicion;

    @BeforeClass
    public static void beforeClass() {
        estrategiaParserDefinicion = new EstrategiaParserDefinicion();            
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void crear_definicion_juan_test() {
        String linea = "varon(juan).";
        Evaluable definicionParseada = estrategiaParserDefinicion.parsearLinea(linea);

        String[] valores = {"juan"};
        Evaluable definicionEsperada = new Definicion("varon", valores);
        Assert.assertTrue(definicionParseada.equals(definicionEsperada));
    }

    @Test
    public void crear_definicion_padre_test() {
        String linea = "padre(roberto, alejandro).";
        Evaluable definicionParseada = estrategiaParserDefinicion.parsearLinea(linea);

        String[] valores = {"roberto", "alejandro"};
        Evaluable definicionEsperada = new Definicion("padre", valores);
        Assert.assertTrue(definicionParseada.equals(definicionEsperada));
    }
                
}