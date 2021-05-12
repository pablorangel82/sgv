/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

import br.com.paim.conclusion.IConclusion;
import br.com.paim.operation.IOperation;
import br.com.paim.type.ObservableData;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author pablo
 */


public abstract class Rule {

    private RuleSet         ruleSet   = null;
    private IOperation      operation;
    private IConclusion     conclusion;

    public Rule(IOperation operation, IConclusion conclusion) {
        this.operation=operation;
        this.conclusion=conclusion;
    }

    private void performRegister(IOperation op){
        ArrayList dataList = op.getObjectList();
        Iterator dataIterator = dataList.iterator();
        while (dataIterator.hasNext()){
            Object obj = dataIterator.next();
            if (obj instanceof ObservableData){
                ((ObservableData)obj).addRule(this);
            }
            if (obj instanceof IOperation){
                this.performRegister((IOperation) obj);
            }
        }
    }

    public void register(){
        this.performRegister(this.getOperation());
    }

    public IOperation getOperation() {
        return operation;
    }

    public IConclusion getConclusion() {
        return conclusion;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public void setOperation(IOperation operation) {
        this.operation = operation;
    }

    public void setConclusion(IConclusion conclusion) {
        this.conclusion = conclusion;
    }

}
 
