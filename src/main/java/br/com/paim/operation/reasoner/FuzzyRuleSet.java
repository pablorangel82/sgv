/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

/**
 *
 * @author pablo
 */


import br.com.paim.conclusion.FuzzyConclusion;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class FuzzyRuleSet extends RuleSet{

    public final static String AVG_MAX = "MEDIA DOS MAXIMOS";
    public final static String MAX_POINT = "MAIOR PONTO";

    private String defuzzificationMode = AVG_MAX;

    public FuzzyRuleSet(String defuzzificationMode){
        this.setDefuzzificationMode(defuzzificationMode);
        super.setMode(GROUP_DRIVEN);
    }

    public FuzzyRuleSet(){
        this.setDefuzzificationMode(AVG_MAX);
        super.setMode(GROUP_DRIVEN);
    }

    public abstract void response(double result);

    public final void addRule(FuzzyRule rule){
        super.addRule(rule);
    }

    public final void removeRule(FuzzyRule rule){
        super.removeRule(rule);
    }

    public String getDefuzzificationMode() {
        return defuzzificationMode;
    }

    public void setDefuzzificationMode(String defuzzificationMode) {
        this.defuzzificationMode = defuzzificationMode;
    }

    /* The propose of this function is to merge or extract the conclusions with MAX method.*/
    private ArrayList composition(){
        ArrayList conclusions = new ArrayList();
        Iterator iteratorRules = this.getRules().iterator();
        
        Boolean flag=false;
        while (iteratorRules.hasNext()){
            FuzzyConclusion conclusion = (FuzzyConclusion)((FuzzyRule)iteratorRules.next()).getConclusion();
            flag=false;
            Iterator iteratorConclusions = conclusions.iterator();
            while (iteratorConclusions.hasNext()){
                FuzzyConclusion existingConclusion = (FuzzyConclusion) iteratorConclusions.next();
                if (existingConclusion.getFuzzyFunctionData().equals(conclusion.getFuzzyFunctionData())){
                    if (conclusion.getMi() > existingConclusion.getMi())
                        existingConclusion.setMi(conclusion.getMi());
                    flag=true;
                    break;
                }
            }
            if (!flag){
                FuzzyConclusion of = new FuzzyConclusion(conclusion.getFuzzyFunctionData());
                of.setMi(conclusion.getMi());
                conclusions.add(of);
            }
                
        }
        return conclusions;
    }

    protected void avgMaxMethod(){
        double num =0.0;
        double den =0.0;

        ArrayList conclusions = this.composition();
        Iterator iterator = conclusions.iterator();
        while (iterator.hasNext()){
            FuzzyConclusion conclusion = (FuzzyConclusion)iterator.next();
            double mi  = conclusion.getMi();
            double avg = conclusion.getFuzzyFunctionData().getAverage();
            num=num+ (mi * avg);
            den=den+ mi;
        }
        if (den > 0.0)
            this.response(num/den);
        else
            this.response(0.0);
    }

    protected void maxPointMethod(){
        double num =0.0;
        double den =0.0;

        ArrayList conclusions = this.composition();
        Iterator iterator = conclusions.iterator();
        while (iterator.hasNext()){
            FuzzyConclusion conclusion = (FuzzyConclusion)iterator.next();
            double mi  = conclusion.getMi();
            double avg = conclusion.getFuzzyFunctionData().getAverage();
            num=num+ (mi * avg);
            den=den+ mi;
        }
        if (den > 0.0)
            this.response(num/den);
        else
            this.response(0.0);
    }

    protected void defuzzyfication(){
        if (this.getDefuzzificationMode().equals(AVG_MAX))
            this.avgMaxMethod();
        if (this.getDefuzzificationMode().equals(MAX_POINT))
            this.maxPointMethod();
    }
}
