/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.conclusion;

/**
 *
 * @author pablo
 * Teste
 */

import br.com.paim.type.FuzzyFunctionData;

public class FuzzyConclusion implements IConclusion{

    private FuzzyFunctionData   fuzzyFunctionData   = null;
    private double              mi                  = 0.0;

    public void response(double result){};


    public FuzzyConclusion(){
         
    }

    public FuzzyConclusion(FuzzyFunctionData fuzzyFunctionData){
        this.setConclusion(fuzzyFunctionData);
    }

    public void setConclusion(FuzzyFunctionData fuzzyFunctionData){
        this.setFuzzyFunctionData(fuzzyFunctionData);
    }

    public double getMi() {
        return mi;
    }

    public void setMi(double mi) {
        this.mi = mi;
    }

    public FuzzyFunctionData getFuzzyFunctionData() {
        return fuzzyFunctionData;
    }

    public void setFuzzyFunctionData(FuzzyFunctionData fuzzyFunctionData) {
        this.fuzzyFunctionData = fuzzyFunctionData;
    }

}
