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
public class ParserConsulta {
    
    public boolean esConsultaValida(String linea) {
        String regex = "^\\s*[a-z_]+\\s*\\(((\\s*[a-z_]+\\s*),)*((\\s*[a-z_]+\\s*))\\)\\s*$";
        return Pattern.matches(regex, linea);
    }
    
    public Consulta parsearLinea(String linea) {
        if (!esConsultaValida(linea))
            throw new ParsingException();
        
        String nombre = linea.split("\\(")[0].replace(" ", "");
	String[] valores = linea.split("\\(")[1].replace(" ","").replace(").", "").split(",");
        return new Consulta(nombre, valores);
    }
}
    