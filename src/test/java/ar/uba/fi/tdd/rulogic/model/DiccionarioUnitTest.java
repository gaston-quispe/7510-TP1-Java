package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class DiccionarioUnitTest {

    @InjectMocks
    private static Diccionario diccionario;

    @BeforeClass
    public static void beforeClass() {
        diccionario = new Diccionario();
        diccionario.addEvaluable(new Definicion("varon",new String[]{"juan"}));
        diccionario.addEvaluable(new Definicion("varon",new String[]{"pepe"}));
        diccionario.addEvaluable(new Definicion("padre",new String[]{"juan", "pepe"}));
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void consulta_es_true() {
        Consulta c = new Consulta("padre", new String[]{"juan", "pepe"});
        boolean res = diccionario.consultar(c);
        Assert.assertTrue(res);
    }
    
    @Test
    public void consulta_es_false() {
        Consulta c = new Consulta("padre", new String[]{"juan", "xxx"});
        boolean res = diccionario.consultar(c);
        Assert.assertFalse(res);
    }
}
