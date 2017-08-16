/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice;

import br.ufjf.pgcc.nenc.webservice.controller.GetInterest;

/**
 *
 * @author Marcio Júnior
 */
public class Main {

    public static void main(String args[]) {
        GetInterest gi = new GetInterest();
        String model = gi.getInterest(8);
        System.out.println(model);
    }
}
