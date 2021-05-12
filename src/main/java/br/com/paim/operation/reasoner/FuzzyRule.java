/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

import br.com.paim.conclusion.FuzzyConclusion;
import br.com.paim.operation.IFuzzyOperation;

/**
 *
 * @author pablo
 */

public class FuzzyRule extends Rule {


    public FuzzyRule(){
        super(null,null);
    }

    public FuzzyRule(IFuzzyOperation operation, FuzzyConclusion conclusion) {
        super(operation,conclusion);
    }

}
 
