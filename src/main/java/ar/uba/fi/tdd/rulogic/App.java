package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import java.util.ArrayList;
import java.util.List;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
		System.out.println("I shall answer all your questions!");
                List<String> db = new ArrayList<String>();
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
                
                KnowledgeBase kb = new KnowledgeBase();
                kb.parseDB(db.iterator());
                System.out.println("RTA:" + kb.answer("varon(juan)"));
                
    }
}
