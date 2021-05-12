/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.type;

/**
 *
 * @author pablo
 */

import br.com.paim.operation.reasoner.Reasoner;
import br.com.paim.operation.reasoner.Rule;
import java.util.ArrayList;

public abstract class ObservableData extends GenericData{
    private ArrayList rules;

    public ObservableData(){
        this.rules=new ArrayList();
    }

    public void addRule(Rule rule) {
        if (!rules.contains(rule))
            rules.add(rule);
    }

    protected void investigate(){
        Reasoner reasoner = new Reasoner(false);
        reasoner.execute(rules);
    }
	 
}
 
