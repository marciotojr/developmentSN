/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Marcio Júnior
 */
@Path("recommendation")
public class GetRecommendation {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetRecommendation
     */
    public GetRecommendation() {
    }

    /**
     * Retrieves representation of an instance of br.ufjf.pgcc.nenc.webservice.services.GetRecommendation
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "deu certo rapa";
    }

    /**
     * PUT method for updating or creating an instance of GetRecommendation
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
