/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Pattern;

/**
 *
 * @author gaston
 */
public class ParserConsultaParametrica {
    
    public boolean esLineaValida(String linea) {
        String regex = "^\\s*[a-z_]+\\s*\\(((\\s*[A-Z]+\\s*),)*((\\s*[A-Z]+\\s*))\\)\\s*$";
        
        return Pattern.matches(regex, linea);
    }
    
    public ConsultaParametrica parsearLinea(String linea) {
        if (!esLineaValida(linea))
            return null;
        
        String nombre = linea.split("\\(")[0].replace(" ", "");
	String[] valores = linea.split("\\(")[1].replace(" ","").replace(")", "").split(",");
        return new ConsultaParametrica(nombre, valores);
    }
}
