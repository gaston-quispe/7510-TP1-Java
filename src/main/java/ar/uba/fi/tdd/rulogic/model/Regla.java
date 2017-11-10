/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author gaston
 */
public class Regla implements Evaluable {
    
    private String nombre;
    private String[] parametros;
    private List<ConsultaParametrica> consultasParametricas;
    
    public Regla(String nombre, String[] parametros, List<ConsultaParametrica> consultasParametricas) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.consultasParametricas = consultasParametricas;
    }
    
    private HashMap<String, String> generarCorresponencia(String[] parametros, String[] valores) {
        HashMap<String,String> corresponencia = new HashMap<String,String>();
        for (int i = 0; i < parametros.length; i++) {
            corresponencia.put(parametros[i], valores[i]);
        }            
        return corresponencia;
    }
        
    private List<Consulta> generarNuevasConsultas(Consulta consultaOriginal) {
        HashMap<String,String> corresponencia = this.generarCorresponencia(parametros, consultaOriginal.getValores());
        List<Consulta> nuevasConsultas = new ArrayList<Consulta>();
        
        for(ConsultaParametrica cp : consultasParametricas) 
            nuevasConsultas.add(cp.reemplazarParametros(corresponencia));
        
        return nuevasConsultas;
    }
    
    public boolean evaluar(Consulta consultaOriginal, Diccionario diccionario) {
        if (!consultaOriginal.getNombre().equals(this.nombre))
            return false;
        
        List<Consulta> nuevasConsultas = generarNuevasConsultas(consultaOriginal);
        
        int exitos = 0;
        
        for(Consulta c : nuevasConsultas) 
            if (diccionario.consultar(c))
                exitos++;

        return exitos == nuevasConsultas.size();
    }
    
    @Override
    public boolean equals(Object o) {
        Regla r = (Regla)o;
        if (!this.nombre.equals(r.nombre))
            return false;
        
        if (!Arrays.equals(this.parametros, r.parametros))
            return false;
        
        return this.consultasParametricas.equals(r.consultasParametricas);
    }
    
}
