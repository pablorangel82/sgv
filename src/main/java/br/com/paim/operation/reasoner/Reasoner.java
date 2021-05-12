/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.reasoner;

import br.com.paim.conclusion.FirstOrderConclusion;
import br.com.paim.conclusion.FuzzyConclusion;
import br.com.paim.operation.BinaryOperation;
import br.com.paim.operation.IFirstOrderOperation;
import br.com.paim.operation.IFuzzyOperation;
import br.com.paim.operation.IOperation;
import br.com.paim.operation.Operation;
import br.com.paim.operation.UnaryOperation;
import br.com.paim.type.BooleanData;
import br.com.paim.type.NumericalData;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author pablo
 */



public class Reasoner {
    private boolean printExpr = false;
    private ArrayList expr = null;

    public Reasoner(boolean printExpr){
        this.printExpr=printExpr;
    }

    public void performOperation(IOperation op, int level){
        ArrayList datas = op.getObjectList();
        Iterator iterator = datas.iterator();
        int i = 0;
        while(iterator.hasNext()){
            Object obj = iterator.next();
            if (obj instanceof IFirstOrderOperation){
                BooleanData bd = new BooleanData();
                this.performOperation((Operation)obj, level+1);
                bd.setValue(((Operation)obj).getBooleanResult());
                if (op instanceof UnaryOperation){
                    ((UnaryOperation)op).setData(bd);
                }
                if (op instanceof BinaryOperation){
                    if (i == 0){
                        ((BinaryOperation)op).setData1(bd);
                    }
                    if (i == 1){
                        ((BinaryOperation)op).setData2(bd);
                    }
                }
            }
            if (obj instanceof IFuzzyOperation){
                NumericalData dd = new NumericalData();
                this.performOperation((Operation)obj,level+1);
                dd.setValue(((Operation)obj).getDoubleResult());
                if (op instanceof UnaryOperation){
                    ((UnaryOperation)op).setData(dd);
                }
                if (op instanceof BinaryOperation){
                    if (i == 0){
                        ((BinaryOperation)op).setData1(dd);
                    }
                    if (i == 1){
                        ((BinaryOperation)op).setData2(dd);
                    }
                }
            }

            i++;
        }
        op.doOperation();
        if (this.printExpr)
            expr.add("[" + Integer.toString(level)  + "](" + op.getExpr()+ ")");
        return;
    }


    public void execute (ArrayList rules){
        if (rules.isEmpty())
            return;
        if (this.printExpr){
            this.expr=new ArrayList();
            System.out.println("Starting engine inference... OK.");
        }

        ArrayList fuzziesSet = new ArrayList();

        Iterator iterator = rules.iterator();
        while (iterator.hasNext()){
            boolean result = false;
            Rule rule = (Rule)iterator.next();
            IOperation op=rule.getOperation();
            this.performOperation(op, 0);
            if (op instanceof IFuzzyOperation){
                if (op.getDoubleResult() >= 0.0){
                    ((FuzzyConclusion)rule.getConclusion()).setMi(op.getDoubleResult());
                    if(!fuzziesSet.contains(((FuzzyRule)rule).getRuleSet()))
                        fuzziesSet.add(((FuzzyRule)rule).getRuleSet());
                }
            }
            if (op instanceof IFirstOrderOperation){
                ((FirstOrderConclusion)rule.getConclusion()).response(op.getBooleanResult());
            }
        }
        iterator = fuzziesSet.iterator();
        while (iterator.hasNext()){
            FuzzyRuleSet fuzzySet = (FuzzyRuleSet)iterator.next();
            fuzzySet.defuzzyfication();
        }
        if (this.printExpr){
            for (int i= 0; i < this.expr.size(); i++)
            //for (int i= this.expr.size()-1; i >= 0; i--)
                System.out.println((String)this.expr.get(i));
            System.out.println("Terminated");
        }
    }

}
