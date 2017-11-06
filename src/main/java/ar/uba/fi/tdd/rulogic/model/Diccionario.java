/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaston
 */
public class Diccionario {

    private List<Evaluable> evaluables;

    @Override
    public String toString() {
        return "Diccionario{" + evaluables.size() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Diccionario other = (Diccionario) obj;
        return true;
    }
    
    public Diccionario() {
        this.evaluables = new ArrayList<Evaluable>();
    }

    void addEvaluable(Evaluable e) {
        this.evaluables.add(e);
    }

    boolean consultar(Consulta c) {
        for (Evaluable e : evaluables)
            if (e.evaluar(c, this))
                return true;
        
        return false;
    }
    
}
