/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.controller;

import br.ufjf.pgcc.nenc.webservice.dao.OntologyLoadDAO;
import br.ufjf.pgcc.nenc.webservice.model.Institution;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Marcio Júnior
 */
public class GetIntitutionsInEcosystem {

    public GetIntitutionsInEcosystem() {
    }

    public String getInstitutions() {
        Model model = OntologyLoadDAO.getInstance();
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "                 PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
                + "                 PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n"
                + "                 PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
                + "                 PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "                 PREFIX onto: <http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#>\n"
                + "                 \n"
                + "                 SELECT DISTINCT ?institution\n"
                + "                 WHERE {{\n"
                + "	?institution onto:develops ?solution.\n"
                + "	?solution onto:builtForPlatform ?platform.\n"
                + "	?platform onto:hasEcosystem onto:androidEcosystem  } \n"
                + "	UNION{\n"
                + "	?institution onto:develops ?platform.\n"
                + "	?platform onto:hasEcosystem onto:androidEcosystem  } \n"
                + "	UNION  {\n"
                + "	?institution onto:develops ?component.\n"
                + "	?component onto:builtForSolution ?solution.\n"
                + "	?solution onto:builtForPlatform ?platform.\n"
                + "	?platform onto:hasEcosystem onto:androidEcosystem}?institution rdf:type onto:Institution}";

        Dataset dataset = DatasetFactory.create(model);

        Query consulta = QueryFactory.create(query);

        QueryExecution qexec = QueryExecutionFactory.create(consulta, dataset);
        ResultSet resultado = qexec.execSelect();
        ArrayList<Institution> institutions = new ArrayList<>();
        Institution institution = null;
        if (!resultado.hasNext()) {
            return "[]";
        }
        while (resultado.hasNext()) {
            QuerySolution tuple = (QuerySolution) resultado.next();
            if (institution == null) {
                institution = new Institution(tuple.get("institution"));
            } else if (!institution.getSelf().equals(tuple.get("institution"))) {
                institutions.add(institution);
                institution = new Institution(tuple.get("institution"));
            }
        }
        institutions.add(institution);

        JSONArray jInstitutions = new JSONArray();

        for (Institution inst : institutions) {
            JSONObject jInstitution = new JSONObject();
            jInstitution.put("Institution", inst.getSelf().toString());
            jInstitutions.put(jInstitution);
        }
        JSONObject output = new JSONObject();
        output.put("Institutions", jInstitutions);
        return output.toString();
    }
}
