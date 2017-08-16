/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.controller;

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
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author Marcio Júnior
 */
public class GetInterest {

    public String getInterest(int userId) {
        Model model = OntologyLoadDAO.read();
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX onto: <http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#>\n"
                + "\n"
                + "SELECT DISTINCT ?person ?skill\n"
                + "WHERE {{\n"
                + "	?person onto:postsA ?post.\n"
                + "	?post onto:isPostedOn ?space.\n"
                + "	?space onto:isAboutSkill ?skill} \n"
                + "	UNION{\n"
                + "	?person onto:follows ?repo.\n"
                + "	?repo onto:requiresSkill ?skill}}";

        Dataset dataset = DatasetFactory.create(model);

        Query consulta = QueryFactory.create(query);

        QueryExecution qexec = QueryExecutionFactory.create(consulta, dataset);
        ResultSet resultado = qexec.execSelect();
        ArrayList<Person> people = new ArrayList<>();
        Person person = null;
        Skill skill = null;
        while (resultado.hasNext()) {
            QuerySolution linha = (QuerySolution) resultado.next();
            if (person == null) {
                person = new Person(linha.get("person"));
            } else if (!person.getSelf().equals(linha.get("person"))) {
                people.add(person);
                person = new Person(linha.get("person"));
            }
            skill = new Skill(linha.get("skill"));
            person.addInterest(skill);
        }
        people.add(person);
        Model newModel = ModelFactory.createDefaultModel();
        newModel.setNsPrefix("onto", "http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#");
        for (Person p : people) {
            Resource np = newModel.createResource(p.getSelf().asResource());
            for (Skill s : p.getInterest()) {
                np.addProperty(model.getProperty("http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#hasInterestIn"), s.getSelf());
            }
        }
        return newModel.toString();
    }
    /*
   
    public static Person[] getInterestArr() {
        Model model = OntologyLoadDAO.read();
        /* StmtIterator iter = model.listStatements();
        model.setNsPrefix("http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#", "onto");
        /*while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");

            System.err.println("");
        }
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "PREFIX onto: <http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#>\n"
                + "\n"
                + "SELECT DISTINCT ?person ?skill\n"
                + "WHERE {{\n"
                + "	?person onto:postsA ?post.\n"
                + "	?post onto:isPostedOn ?space.\n"
                + "	?space onto:isAboutSkill ?skill} \n"
                + "	UNION{\n"
                + "	?person onto:follows ?repo.\n"
                + "	?repo onto:requiresSkill ?skill}}";

        Dataset dataset = DatasetFactory.create(model);
        // Fazendo o parse da string da consulta e criando o objecto Query
        Query consulta = QueryFactory.create(query);
        // Executando a consulta e obtendo o resultado
        QueryExecution qexec = QueryExecutionFactory.create(consulta, dataset);
        ResultSet resultado = qexec.execSelect();
        // Imprimindo os resultados
        ArrayList<Person> people = new ArrayList<>();
        Person person = null;
        Skill skill = null;
        while (resultado.hasNext()) {
            // Cada linha contém dois campos: "casado" e "conjuge", assim foi definido na string da consulta
            System.err.println("\n");
            QuerySolution linha = (QuerySolution) resultado.next();
            if (person == null) {
                person = new Person(linha.get("person"));
            } else if (!person.getSelf().equals(linha.get("person"))) {
                people.add(person);
                person = new Person(linha.get("person"));
            }
            skill = new Skill(linha.get("skill"));
            person.addInterest(skill);
        }
        people.add(person);
        Model newModel = ModelFactory.createDefaultModel();
        newModel.setNsPrefix("onto", "http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#");
        for (Person p : people) {
            Resource np = newModel.createResource(p.getSelf().asResource());
            for (Skill s : p.getInterest()) {
                np.addProperty(model.getProperty("http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#hasInterestIn"), s.getSelf());
            }
        }
        return (Person[])people.toArray();
    }*/
}
