package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReglaUnitTest {

        @BeforeClass
        public static void beforeClass() {        
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void crear_regla_valida() {
            String[] parametros = {"X", "Y"};
            List<ConsultaParametrica> listaCP = new ArrayList<ConsultaParametrica>();
            listaCP.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP.add(new ConsultaParametrica("padre", new String[]{"Y", "X"}));

            Evaluable regla = new Regla("hijo", parametros, listaCP);
            Assert.assertNotNull(regla);
	}
        
                @Test
	public void dos_reglas_deberian_ser_diferentes_test() {
            String[] parametros1 = {"X", "Y"};
            List<ConsultaParametrica> listaCP1 = new ArrayList<ConsultaParametrica>();
            listaCP1.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP1.add(new ConsultaParametrica("padre", new String[]{"Y", "X"}));
            Evaluable regla1 = new Regla("hijo", parametros1, listaCP1);
            
            String[] parametros2 = {"X", "Y"};
            List<ConsultaParametrica> listaCP2 = new ArrayList<ConsultaParametrica>();
            listaCP2.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP2.add(new ConsultaParametrica("padre", new String[]{"Y", "X"}));
            Evaluable regla2 = new Regla("hijoxxx", parametros2, listaCP2);
            Assert.assertNotEquals(regla1,regla2);
	}
        
        @Test
	public void dos_reglas_deberian_ser_diferentes_2_test() {
            String[] parametros1 = {"X", "Y"};
            List<ConsultaParametrica> listaCP1 = new ArrayList<ConsultaParametrica>();
            listaCP1.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP1.add(new ConsultaParametrica("padre", new String[]{"Y", "X"}));
            Evaluable regla1 = new Regla("hijo", parametros1, listaCP1);
            
            String[] parametros2 = {"X", "Y"};
            List<ConsultaParametrica> listaCP2 = new ArrayList<ConsultaParametrica>();
            listaCP2.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP2.add(new ConsultaParametrica("padre", new String[]{"Y", "X", "Z"}));
            Evaluable regla2 = new Regla("hijo", parametros2, listaCP2);
            Assert.assertNotEquals(regla1,regla2);
	}
        
        @Test
	public void dos_reglas_deberian_ser_diferentes_3_test() {
            String[] parametros1 = {"X", "Y"};
            List<ConsultaParametrica> listaCP1 = new ArrayList<ConsultaParametrica>();
            listaCP1.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP1.add(new ConsultaParametrica("padre", new String[]{"Y", "X"}));
            Evaluable regla1 = new Regla("hijo", parametros1, listaCP1);
            
            String[] parametros2 = {"X", "Z"};
            List<ConsultaParametrica> listaCP2 = new ArrayList<ConsultaParametrica>();
            listaCP2.add(new ConsultaParametrica("varon", new String[]{"X"}));
            listaCP2.add(new ConsultaParametrica("padre", new String[]{"Y", "Z"}));
            Evaluable regla2 = new Regla("hijo", parametros2, listaCP2);
            Assert.assertNotEquals(regla1,regla2);
	}
}