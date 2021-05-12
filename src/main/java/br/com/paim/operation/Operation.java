/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation;

/**
 *
 * @author pablo
 */

import  java.util.ArrayList;

public abstract class Operation implements IOperation {
    private         boolean booleanResult;
    private         double  doubleResult;

    public void doOperation(){
        if (this instanceof IFuzzyOperation)
            this.setDoubleResult(((IFuzzyOperation)this).doFuzzyOperation());
        if (this instanceof IFirstOrderOperation)
            this.setBooleanResult(((IFirstOrderOperation)this).doFirstOrderOperation());
    }

    public abstract String getExpr();
    public abstract ArrayList getObjectList() ;
    public abstract ArrayList getDataList() ;
    public abstract String getSymbol();

    public boolean getBooleanResult() {
        return booleanResult;
    }

    public void setBooleanResult(boolean booleanResult) {
        this.booleanResult = booleanResult;
    }

    public double getDoubleResult() {
        return doubleResult;
    }

    public void setDoubleResult(double doubleResult) {
        this.doubleResult = doubleResult;
    }
}
 
