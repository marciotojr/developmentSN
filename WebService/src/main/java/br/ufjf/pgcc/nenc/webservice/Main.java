/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice;

import br.ufjf.pgcc.nenc.webservice.controller.GetInterest;
import br.ufjf.pgcc.nenc.webservice.dao.OntologyLoadDAO;
import br.ufjf.pgcc.nenc.webservice.model.Person;
import br.ufjf.pgcc.nenc.webservice.model.Skill;
import java.util.ArrayList;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.tdb.migrate.A2;

/**
 *
 * @author Marcio Júnior
 */
public class Main {

    public static void main(String args[]) {
       Model model = GetInterest.getInterest();
        System.out.println(model.toString());;
        model.write(System.out, "Turtle");
    }
}
