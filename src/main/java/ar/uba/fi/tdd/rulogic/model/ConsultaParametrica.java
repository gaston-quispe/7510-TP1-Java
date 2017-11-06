/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author gaston
 */
class ConsultaParametrica {

    private String nombre;
    private String[] parametros;
    
    ConsultaParametrica(String nombre, String[] parametros) {
        this.nombre = nombre;
        this.parametros = parametros;
    }
    
    public Consulta reemplazarParametros(HashMap<String,String> corresponencia) {
            List<String> valores = new ArrayList<String>();

            for (String p : parametros)
                valores.add(corresponencia.get(p));                    

            return new Consulta(nombre, valores.toArray(new String[valores.size()]));
    }

}
