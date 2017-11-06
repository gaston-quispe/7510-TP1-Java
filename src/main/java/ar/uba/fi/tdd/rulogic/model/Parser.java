/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

/**
 *
 * @author gaston
 */
public class Parser {
    private ParserConsulta parserConsulta;
    private List<EstrategiaParser> listaParsersLinea;
    
    public Parser(ParserConsulta pc, List<EstrategiaParser> listaParsersLinea) {
        this.parserConsulta = pc;
        this.listaParsersLinea = listaParsersLinea;
    }

    public Evaluable parsearEvaluable(String linea) {
        for (EstrategiaParser ep : listaParsersLinea) {
            Evaluable e = ep.parsearLinea(linea);
            if (e != null)
                return e;
        }       
        return null;
    }
    
    public Consulta parsearConsulta(String linea) {
        return parserConsulta.parsearLinea(linea);
    }
}
