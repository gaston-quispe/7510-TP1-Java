package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
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

           Evaluable e = parserDB.parsearEvaluable(linea.replaceAll("\\. *$", ""));
           
           if (e == null) {
               this.dbBroken = true;
               throw new RuntimeException("Error de parseo en una linea");
           }
           
           this.diccionario.addEvaluable(e);
        }
        
        return true;
    }

    public boolean answer(String query) {
        if (this.dbBroken)
            throw new RuntimeException("La base de datos esta corrompida");

        Consulta c = parserDB.parsearConsulta(query);
        //System.out.println("xxx:" + c.getNombre() + "@" + Arrays.toString(c.getValores()));
        return this.diccionario.consultar(c);
    }
}
