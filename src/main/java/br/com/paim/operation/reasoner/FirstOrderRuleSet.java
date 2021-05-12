/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

/**
 *
 * @author pablo
 */

public abstract class FirstOrderRuleSet extends RuleSet{

    public abstract void setData();
    
    public final void addRule(FirstOrderRule rule){
        super.addRule(rule);
    }

    public final void removeRule(FirstOrderRule rule){
        super.removeRule(rule);
    }
}
