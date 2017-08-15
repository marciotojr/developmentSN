/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.pgcc.nenc.webservice.model;

import org.apache.jena.rdf.model.RDFNode;

/**
 *
 * @author Marcio Júnior
 */
public class Person {
    RDFNode self;
    Skill[] interest;

    public Person(RDFNode self) {
        this.self = self;
        interest = new Skill[0];
    }
    
    

    public RDFNode getSelf() {
        return self;
    }

    public void setSelf(RDFNode self) {
        this.self = self;
    }

    public Skill[] getInterest() {
        return interest;
    }

    public void setInterest(Skill[] interest) {
        this.interest = interest;
    }
    
    public void addInterest(Skill interest){
        Skill[] aux = this.interest;
        this.interest = new Skill[aux.length+1];
        for(int i=0;i<aux.length;i++)this.interest[i]=aux[i];
        this.interest[aux.length]=interest;
    }
    
}
