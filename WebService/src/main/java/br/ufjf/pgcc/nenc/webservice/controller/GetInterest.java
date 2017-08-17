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
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import javax.json.JsonValue;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marcio Júnior
 */
public class GetInterest {

    public GetInterest() {
    }

    public String getUsersInterests(int userId) {
        Model model = OntologyLoadDAO.getInstance();
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "                 PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "                 PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n"
                + "                 PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "                 PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "                 PREFIX onto: <http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#>\n"
                + "                 \n"
                + "                 SELECT DISTINCT ?person ?skill\n"
                + "                 WHERE {{\n"
                + "                 ?person onto:hasId '" + userId + "'^^xsd:integer.\n"
                + "                 	?person onto:postsA ?post.\n"
                + "                 	?post onto:isPostedOn ?space.\n"
                + "                 	?space onto:isAboutSkill ?skill} \n"
                + "                 	UNION{\n"
                + "                 	?person onto:hasId '" + userId + "'^^xsd:integer.\n"
                + "                 	?person onto:follows ?repo.\n"
                + "                 	?repo onto:requiresSkill ?skill}\n"
                + "                 	UNION{\n"
                + "                 	?person onto:hasId '" + userId + "'^^xsd:integer.\n"
                + "                 	?person onto:follows ?repo.\n"
                + "                 	?repo onto:hosts ?software.\n"
                + "                 	?software onto:needsSkill ?skill}}";

        Dataset dataset = DatasetFactory.create(model);

        Query consulta = QueryFactory.create(query);

        QueryExecution qexec = QueryExecutionFactory.create(consulta, dataset);
        ResultSet resultado = qexec.execSelect();
        ArrayList<Person> people = new ArrayList<>();
        Person person = null;
        Skill skill = null;
        if (!resultado.hasNext()) {
            return "[]";
        }
        while (resultado.hasNext()) {
            QuerySolution tuple = (QuerySolution) resultado.next();
            if (person == null) {
                person = new Person(tuple.get("person"));
            } else if (!person.getSelf().equals(tuple.get("person"))) {
                people.add(person);
                person = new Person(tuple.get("person"));
            }
            skill = new Skill(tuple.get("skill"));
            person.addInterest(skill);
        }
        people.add(person);
        JSONObject obj = new JSONObject();

        JSONArray jPeople = new JSONArray();

        for (Person p : people) {
            JSONObject jPerson = new JSONObject();
            jPerson.put("Person", p.getSelf().toString());
            JSONArray jInterests = new JSONArray();
            for (Skill s : p.getInterest()) {
                JSONObject jSkill = new JSONObject();
                jSkill.put("Skill", s.getSelf().toString());
                jInterests.put(jSkill);
            }
            jPerson.put("Interests", jInterests);
            jPeople.put(jPerson);
        }

        return jPeople.toString();
    }
}
