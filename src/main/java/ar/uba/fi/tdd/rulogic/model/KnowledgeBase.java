package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KnowledgeBase {

    private boolean dbBroken;
    private final Parser parserDB;
    private final Diccionario diccionario;
    
    public KnowledgeBase() {
        this.dbBroken = false;
        List<EstrategiaParser> listaParsersLinea = new ArrayList<EstrategiaParser>();
        listaParsersLinea.add(new EstrategiaParserDefinicion());
        listaParsersLinea.add(new EstrategiaParserRegla());
        
        parserDB = new Parser(new ParserConsulta(), listaParsersLinea);
        diccionario = new Diccionario();    
    }
    public boolean parseDB(Iterator<String> it) {
        while(it.hasNext()) {
           String linea = it.next();

           String linea_sin_punto_al_final = linea.replaceAll("\\. *$", "");
           Evaluable e = parserDB.parsearEvaluable(linea_sin_punto_al_final);
           
           if (e == null) {
               this.dbBroken = true;
               throw new ParsingException();
           }
           
           this.diccionario.addEvaluable(e);
        }
        
        return true;
    }

    public boolean answer(String query) {
        if (this.dbBroken)
            throw new DatabaseBrokenException();

        Consulta c = parserDB.parsearConsulta(query);
        return this.diccionario.consultar(c);
    }
}
