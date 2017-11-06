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
public class Consulta {
    private String nombre;
    private String[] valores;
    
    Consulta(String nombre, String[] valores) {
        this.nombre = nombre;
        this.valores = valores;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    public String[] getValores() {
        return this.valores;
    }
    
    @Override
    public boolean equals(Object o) {
        Consulta c = (Consulta)o;
        return this.nombre.equals(c.nombre) && Arrays.equals(this.valores, c.valores);
    }
    
}
