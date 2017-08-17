/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.dao;

import static com.sun.xml.ws.security.trust.WSTrustElementFactory.getContext;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import java.io.*;
import javax.servlet.ServletContext;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;

/**
 *
 * @author Marcio Júnior
 */
public class OntologyLoadDAO {

    private static Model instance;

    private OntologyLoadDAO(){
        
    }
    
    public static Model getInstance() {
        if (instance == null) {
            Model baseModel = ModelFactory.createDefaultModel();
            Reasoner resoner = ReasonerRegistry.getOWLReasoner();
            //Model model = ModelFactory.createDefaultModel();
            InputStream input = FileManager.get().open("C:\\\\social-network.owl");
            if (input == null) {
                throw new IllegalArgumentException(
                        "File: " + "social-network.owl" + " not found");
            }

            baseModel.read(input, null);
            instance = ModelFactory.createInfModel(resoner, baseModel);
        }
        return instance;
    }

}
