package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KnowledgeBase {

    private boolean dbBroken;
    private final ParserDB parserDB;
    private final Diccionario diccionario;
    
    public KnowledgeBase() {
        this.dbBroken = false;
        List<EstrategiaParser> listaParsersLinea = new ArrayList<EstrategiaParser>();
        listaParsersLinea.add(new EstrategiaParserDefinicion());
        listaParsersLinea.add(new EstrategiaParserRegla());
        
        parserDB = new ParserDB(new ParserConsulta(), listaParsersLinea);
        diccionario = new Diccionario();    
    }
    public boolean parseDB(Iterator<String> it) {
        while(it.hasNext()) {
           String linea = it.next();

           Evaluable ev = parserDB.parsearEvaluable(linea);
           
           if (ev == null) {
               this.dbBroken = true;
               throw new RuntimeException("Error de parseo en una linea");
           }
           
           diccionario.addEvaluable(ev);
        }
        return true;
    }

	public boolean answer(String query) {
            if (this.dbBroken)
                throw new RuntimeException("La base de datos esta corrompida");
            return true;
	}

}
