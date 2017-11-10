/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author gaston
 */
public class EstrategiaParserRegla implements EstrategiaParser{

    public boolean esLineaValida(String linea) {
        String regex = "^.*:-.*$";
        if (Pattern.matches(regex, linea)) {
            String[] izq_der = linea.split(":-");
            String izq = izq_der[0];
            String der = izq_der[1];
            
            String regex_izq = "^\\s*[a-z_]+\\s*\\(((\\s*[A-Z]\\s*),)*((\\s*[A-Z]\\s*))\\)\\s*$";
            String regex_der = "^\\s*([a-z_]+\\s*\\(((\\s*[A-Z]\\s*),\\s*)*((\\s*[A-Z]\\s*))\\)\\s*,\\s*)*[a-z_]+\\s*\\(((\\s*[A-Z]\\s*),)*((\\s*[A-Z]\\s*))\\)\\s*$";
            
            if (Pattern.matches(regex_izq, izq) && Pattern.matches(regex_der, der))
                return true;
        }
        return false;
    }
    
    
    public Evaluable parsearLinea(String linea) {
        if (!esLineaValida(linea))
            return null;
        
        String sinespacios = linea.replace(" ", "");
        String[] izq_der = sinespacios.split(":-");
        String izq = izq_der[0];
        String der = izq_der[1];
        
  	String nombre = izq.split("\\(")[0];
        String[] parametros = izq.replaceAll("^.*\\(|\\s|\\)$", "").split(",");
        String[] aux = der.replaceAll("\\),", "\\)#").split("#");
        
        ParserConsultaParametrica parserConsultaParametrica = new ParserConsultaParametrica();
        List<ConsultaParametrica> consultasParametricas = new ArrayList<ConsultaParametrica>();
        for (String elem : aux)
            consultasParametricas.add(parserConsultaParametrica.parsearLinea(elem));

        return new Regla(nombre, parametros, consultasParametricas);
    }
}