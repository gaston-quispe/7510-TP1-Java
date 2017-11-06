/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.Arrays;

/**
 *
 * @author gaston
 */
public class Definicion implements Evaluable {

    private String nombre;
    private String[] valores;
    
    public Definicion(String nombre, String[] valores) {
        this.nombre = nombre;
        this.valores = valores;
    }
    
    public boolean evaluar(Consulta c, Diccionario d) {

            if (!c.getNombre().equals(this.nombre))
                    return false;

            if (this.valores.length != c.getValores().length)
                    return false;

            return Arrays.equals(this.valores, c.getValores());
    }
    
    @Override
    public boolean equals(Object o) {
        Definicion d = (Definicion)o;
        return this.nombre.equals(d.nombre) && Arrays.equals(this.valores, d.valores);
    }
}
