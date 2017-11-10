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
        int numero_linea = 1;
        while(it.hasNext()) {
           String linea = it.next();

           String linea_sin_punto_al_final = linea.replaceAll("\\. *$", "");
           Evaluable e = parserDB.parsearEvaluable(linea_sin_punto_al_final);
           
           if (e == null) {
               this.dbBroken = true;
               throw new ParsingException("Error de parseo en la linea numero " + numero_linea + ": " + linea);
           }
           
           this.diccionario.addEvaluable(e);
           numero_linea++;
        }
        
        return true;
    }

    public boolean answer(String query) {
        if (this.dbBroken)
            throw new DatabaseBrokenException();
        
        Consulta c = parserDB.parsearConsulta(query);
        
        if (c == null)
            throw new InvalidQueryException();
        
        return this.diccionario.consultar(c);
    }
}
