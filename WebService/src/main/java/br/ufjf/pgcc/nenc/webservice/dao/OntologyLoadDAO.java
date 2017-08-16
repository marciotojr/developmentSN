/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.dao;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import java.io.*;

/**
 *
 * @author Marcio Júnior
 */
public class OntologyLoadDAO {

    public static Model read() {
        Model model = ModelFactory.createDefaultModel();
        InputStream input = FileManager.get().open("WEB-INF\\social-network.owl");
        if (input == null) {
            throw new IllegalArgumentException(
                    "File: " + "ontology\\social-network.owl" + " not found");
        }

// read the RDF/XML file
        model.read(input, null);
        model.setNsPrefix("onto", "http://www.semanticweb.org/marciojúnior/ontologies/2017/6/developer_s-social-network#");
        return model;
    }

}
