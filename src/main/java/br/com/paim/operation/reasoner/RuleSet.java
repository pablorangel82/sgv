/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

/**
 *
 * @author pablo
 */

import java.util.ArrayList;

public abstract class RuleSet {

    /* Options */
    public static int ATTRIBUTE_DRIVEN = 0;
    public static int GROUP_DRIVEN = 1;

    private ArrayList   rules   = null;
    private int         mode    = ATTRIBUTE_DRIVEN;

    public RuleSet(){
        this.rules=new ArrayList();
    }

    public abstract String getStatus();

    protected boolean addRule(Rule rule){
        if(!this.getRules().contains(rule)){
            getRules().add(rule);
            rule.setRuleSet(this);
            if (getMode() == ATTRIBUTE_DRIVEN)
                rule.register();
            return true;
        }
        return false;
    }

    protected boolean removeRule(Rule rule){
        if (rules.remove(rule)){
            rule.setRuleSet(null);
            return true;
        }
        return false;
    }

    public ArrayList getRules() {
        return rules;
    }

    public void setRules(ArrayList rules) {
        this.rules = rules;
    }

    public void investigate(){
        if (getMode() == GROUP_DRIVEN){
            this.setData();
            Reasoner reasoner = new Reasoner (true);
            reasoner.execute(rules);
        }
    }
    
    public abstract void setData();

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
