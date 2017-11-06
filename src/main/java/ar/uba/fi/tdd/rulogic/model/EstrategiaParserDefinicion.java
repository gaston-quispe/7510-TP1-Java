/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

/**
 *
 * @author gaston
 */
public class EstrategiaParserDefinicion implements EstrategiaParser{
    public Evaluable parsearLinea(String linea) {
        String nombre = linea.split("\\(")[0].replace(" ", "");
	String[] valores = linea.split("\\(")[1].replace(" ","").replace(").", "").split(",");
        return new Definicion(nombre, valores);
    }
}
    