package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PokemonAcceptanceTest {

        private static List<String> db;
	private static KnowledgeBase kb;

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        
        @BeforeClass
        public static void db() {
            db = new ArrayList<String>();
            db.add("varon(ash).");
            db.add("varon(tracey).");
            
            db.add("mujer(misty).");
            db.add("mujer(yenni).");
            
            db.add("entrenador(ash).");
            db.add("entrenador(misty).");
            
            db.add("campeon(ash)");
            
            db.add("pokemon(pikachu).");            
            db.add("pokemon(raichu).");
            db.add("pokemon(bulbasaur).");
            db.add("pokemon(charmander).");
            db.add("pokemon(starmie).");
            db.add("pokemon(psyduck).");
            db.add("pokemon(gyarados).");
            
            db.add("evolucion(pikachu, raichu).");
            
            db.add("duenio(ash, pikachu).");
            db.add("duenio(ash, balbasour).");
            db.add("duenio(ash, charmander).");
            db.add("duenio(misty, staryu).");
            db.add("duenio(misty, psyduck).");
            db.add("duenio(misty, goldeen).");
            
            db.add("tipo(electrico).");
            db.add("tipo(planta).");
            db.add("tipo(fuego).");
            db.add("tipo(agua).");
            db.add("tipo(psiquico).");
            db.add("tipo(volador).");
            
            db.add("pokemon_tipo(pikachu, electrico).");
            db.add("pokemon_tipo(raichu, electrico).");
            db.add("pokemon_tipo(bulbasaur, planta).");
            db.add("pokemon_tipo(charmander, fuego).");
            db.add("pokemon_tipo(starmie, agua).");
            db.add("pokemon_tipo(starmie, psiquico).");
            db.add("pokemon_tipo(psyduck, agua).");
            db.add("pokemon_tipo(gyarados, agua).");
            db.add("pokemon_tipo(gyarados, volador).");
            
            db.add("fuerte_contra(electrico, agua).");
            db.add("fuerte_contra(agua, fuego).");
            db.add("fuerte_contra(fuego, planta).");
            db.add("fuerte_contra(volador, planta).");
            db.add("fuerte_contra(volador, fuego).");
            
            db.add("es_entrenador_mujer(X) :- mujer(X), entrenador(X).");
            db.add("es_entrenador_varon(X) :- varon(X), entrenador(X).");
            db.add("entrenador_x_tiene_pokemon_y_de_tipo_z(X, Y, Z) :- entrenador(X), pokemon(Y), tipo(Z), pokemon_tipo(Y,Z), duenio(X, Y).");
            db.add("pokemon_x_de_tipo_y_es_fuerte_contra_tipo_z(X,Y,Z) :- pokemon(X), tipo(Y), tipo(Z), fuerte_contra(Y, Z).");
            db.add("los_dos_pokemones_son_del_tipo_z(X,Y,Z) :- pokemon(X), pokemon(Y), pokemon_tipo(X,Z), pokemon_tipo(Y,Z).");
            db.add("pokemon_x_evoluciona_a_y_de_tipo_z(X, Y, Z) :- pokemon(X), pokemon(Y), evolucion(X, Y), pokemon_tipo(Y,Z).");

            kb = new KnowledgeBase();
            
            kb.parseDB(db.iterator());    
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

        /////////////// TESTS DE DEFINICIONES ///////////////
        
	@Test
	public void entrenador_existe_en_db_test() {
            Assert.assertTrue(kb.answer("entrenador(misty)"));
	}
        
        @Test
	public void entrenador_no_existe_en_db_test() {
            Assert.assertFalse(kb.answer("entrenador(tracey)"));
	}
        
        @Test
	public void definicion_con_dos_valores_existe_en_db_test() {
            Assert.assertTrue(kb.answer("fuerte_contra(agua, fuego)"));
	}
        
        @Test
	public void definicion_con_muchos_espacios_extra_es_valida_test() {
            Assert.assertTrue(kb.answer("     fuerte_contra      (     agua    ,    fuego    )   "));
	}
        
        /////////////// TESTS DE REGLAS ///////////////
        
        @Test
	public void misty_es_entrenador_mujer_deberia_ser_true_test() {
            Assert.assertTrue(kb.answer("es_entrenador_mujer(misty)"));
	}
        
        @Test
	public void ash_es_entrenador_mujer_deberia_ser_false_test() {
            Assert.assertFalse(kb.answer("es_entrenador_mujer(ash)"));
	}
        
        @Test
	public void entrenador_x_tiene_pokemon_y_de_tipo_z_deberia_ser_true_test() {
            Assert.assertTrue(kb.answer("entrenador_x_tiene_pokemon_y_de_tipo_z(ash, pikachu, electrico)"));
	}
        
        @Test
	public void pokemon_x_de_tipo_y_es_fuerte_contra_tipo_z_deberia_ser_false_test() {
            Assert.assertFalse(kb.answer("pokemon_x_de_tipo_y_es_fuerte_contra_tipo_z(ash, humano, gaviotas)"));
	}
        
        @Test
	public void los_dos_pokemones_son_del_tipo_z_deberia_ser_true_test() {
            Assert.assertTrue(kb.answer("los_dos_pokemones_son_del_tipo_z(psyduck, gyarados, agua)"));
	}
        
        @Test
        public void los_dos_pokemones_son_del_tipo_z_con_muchos_espacios_redundantes_deberia_ser_true_test() {
            Assert.assertTrue(kb.answer("   los_dos_pokemones_son_del_tipo_z   (     psyduck   ,   gyarados,     agua    )   "));
	}

        @Test
        public void pokemon_x_evoluciona_a_y_de_tipo_z_deberia_ser_true_test() {
            Assert.assertTrue(kb.answer("pokemon_x_evoluciona_a_y_de_tipo_z(pikachu, raichu, electrico)"));
	}
        
        @Test
        public void pokemon_x_evoluciona_a_y_de_tipo_z_deberia_ser_false_test() {
            Assert.assertFalse(kb.answer("pokemon_x_evoluciona_a_y_de_tipo_z(pikachu, raichu, agua)"));
	}
        
        /////////////// CONSULTAS NO LOCALIZADAS ///////////////
        
        @Test
        public void entrenador_que_no_esta_en_la_bd_deberia_ser_false_test() {
            Assert.assertFalse(kb.answer("entrenador(no_estoy_en_la_base)"));
	}
        
        @Test
        public void entrenador_que_no_esta_en_la_bd_deberia_ser_false_2_test() {
            Assert.assertFalse(kb.answer("es_entrenador_mujer(no_estoy_en_la_base)"));
	}
        
        @Test
        public void entrenador_que_no_esta_en_la_bd_deberia_ser_false_3_test() {
            Assert.assertFalse(kb.answer("no_estoy_en_la_base(ash)"));
	}
        
        @Test
        public void entrenador_que_no_esta_en_la_bd_deberia_ser_false_4_test() {
            Assert.assertFalse(kb.answer("no_estoy_en_la_base(tampoco_estoy_en_la_base)"));
	}
        
        @Test
        public void entrenador_que_no_esta_en_la_bd_deberia_ser_false_5_test() {
            Assert.assertFalse(kb.answer("entrenador_x_tiene_pokemon_y_de_tipo_z(ash, pikachu)"));
	}
        
        /////////////// CONSULTAS INVALIDAS ///////////////
        
        @Test
        public void cosas_sin_formato_deberia_lanzar_excepcion_test() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("cosas_sin_formato_asdasd3$·$·$DASDWQW)EQWE)(()()");
	}
        
        @Test
        public void mayusculas_en_valores_deberia_lanzar_excepcion() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("es_entrenador_mujer(maYuscula)");
	}
        
        @Test
        public void mayusculas_en_nombre_deberia_lanzar_excepcion() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("maYuscua(ash)");
	}
        
        @Test
        public void numeros_en_nombre_deberia_lanzar_excepcion() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("num3ro5(ash)");
	}
        
        @Test
        public void numero_en_valores_deberia_lanzar_excepcion() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("entrenador(num3ro)");
	}
        
        @Test
        public void sin_valores_deberia_lanzar_una_excepcion() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("entrenador()");
	}
        
        @Test
        public void sin_muchos_valores_deberia_lanzar_una_excepcion() {
            thrown.expect(InvalidQueryException.class);
            kb.answer("entrenador(,,)");
	}
        
}