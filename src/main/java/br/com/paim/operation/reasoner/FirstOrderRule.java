/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

import br.com.paim.conclusion.FirstOrderConclusion;
import br.com.paim.operation.IFirstOrderOperation;

/**
 * 
 * @author pablo
 */



public class FirstOrderRule extends Rule {

    public FirstOrderRule(){
        super(null,null);
    }

    public FirstOrderRule(IFirstOrderOperation operation, FirstOrderConclusion conclusion) {
        super(operation,conclusion);
    }

}
 
