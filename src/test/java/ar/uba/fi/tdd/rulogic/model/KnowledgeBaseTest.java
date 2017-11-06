package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KnowledgeBaseTest {

        private static List<String> db;
	private static KnowledgeBase kb;

        @BeforeClass
        public static void db() {
            db = new ArrayList<String>();
            db.add("varon(juan).");
            db.add("varon(pepe).");
            db.add("varon(hector).");
            db.add("varon(roberto).");
            db.add("varon(alejandro).");
            db.add("mujer(maria).");
            db.add("mujer(cecilia).");
            db.add("padre(juan, pepe).");
            db.add("padre(juan, pepa).");
            db.add("padre(hector, maria).");
            db.add("padre(roberto, alejandro).");
            db.add("padre(roberto, cecilia).");
            db.add("hijo(X, Y) :- varon(X), padre(Y, X).");
            db.add("hija(X, Y) :- mujer(X), padre(Y, X).");
            
            kb = new KnowledgeBase();
            
            kb.parseDB(db.iterator());    
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

        // TESTS DE DEFINICIONES
        
	@Test
	public void test() {
            Assert.assertTrue(kb.answer("varon(juan)"));
	}
        
        @Test
        public void test_Varon_Juan_Should_be_true() {
		Assert.assertTrue(kb.answer("varon(juan)"));
	}
        
        @Test
        public void test_Varon_Maria_Should_be_false() {
		Assert.assertFalse(kb.answer("varon(maria)"));
        }
        
        @Test
        public void test_Mujer_Cecilia_Should_be_true() {
		Assert.assertTrue(kb.answer("mujer(cecilia)"));
	}
        
        @Test
        public void test_Padre_Juan_Pepe_Should_be_true() {
		Assert.assertTrue(kb.answer("padre(juan, pepe)"));
	}
        
        @Test
        public void test_Padre_Mario_Pepe_Should_be_false() {
		Assert.assertFalse(kb.answer("padre(mario, pepe)"));
	}
        
        
        // TESTS DE REGLAS
        @Test
        public void hijo_pepe_juan_shoud_be_true_test() {
            Assert.assertTrue(kb.answer("hijo(pepe, juan)"));
        }
        
        @Test
        public void hija_maria_roberto_should_be_false_test() {
            Assert.assertFalse(kb.answer("hija(maria, roberto)"));
        }
        
        @Test
        public void hijo_pepe_juan_should_be_true_test() {
            Assert.assertTrue(kb.answer("hijo(pepe, juan)"));
        }                
}