/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice;

import br.ufjf.pgcc.nenc.webservice.dao.OntologyLoadDAO;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.StmtIterator;

/**
 *
 * @author Marcio Júnior
 */
public class Main {
    public static void main(String args[]){
        Model model = OntologyLoadDAO.read();
        StmtIterator iter = model.listStatements();
        System.err.println("");
    }
}
