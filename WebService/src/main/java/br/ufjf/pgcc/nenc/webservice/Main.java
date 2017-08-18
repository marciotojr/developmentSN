/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice;

import br.ufjf.pgcc.nenc.webservice.controller.GetInterest;
import br.ufjf.pgcc.nenc.webservice.controller.GetIntitutionsInEcosystem;
import br.ufjf.pgcc.nenc.webservice.controller.GetRecomendedDevelopers;
import javax.json.JsonValue;

/**
 *
 * @author Marcio Júnior
 */
public class Main {

    public static void main(String args[]) {
        GetRecomendedDevelopers gi = new GetRecomendedDevelopers();
        String output = gi.getUsersInterests(18);
        System.err.println(output);
    }
}
