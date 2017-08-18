/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.services;

import br.ufjf.pgcc.nenc.webservice.controller.GetInterest;
import br.ufjf.pgcc.nenc.webservice.controller.GetIntitutionsInEcosystem;
import br.ufjf.pgcc.nenc.webservice.controller.GetRecomendedDevelopers;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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
     * Retrieves representation of an instance of
     * br.ufjf.pgcc.nenc.webservice.services.GetRecommendation
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getInterests/{userId}")
    public String getRecommendationsForUser(@PathParam("userId") int userId) throws MalformedURLException, UnsupportedEncodingException, IOException {
        GetInterest gi = new GetInterest();
        return gi.getUsersInterests(userId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getInstitutions/")
    public String getJson()throws MalformedURLException, UnsupportedEncodingException, IOException {
        GetIntitutionsInEcosystem gi = new GetIntitutionsInEcosystem();
        return gi.getInstitutions();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getCollaborators/{roleId}")
    public String getRecommendationsForRole(@PathParam("roleId") int roleId) throws MalformedURLException, UnsupportedEncodingException, IOException {
        GetRecomendedDevelopers gi = new GetRecomendedDevelopers();
        return gi.getUsersInterests(roleId);
    }
    /**
     * PUT method for updating or creating an instance of GetRecommendation
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
