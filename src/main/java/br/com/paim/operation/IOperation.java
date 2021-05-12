/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation;

/**
 *
 * @author pablo
 */
public interface IOperation {
    java.util.ArrayList getObjectList();
    java.util.ArrayList getDataList();
    void doOperation();
    boolean getBooleanResult();
    void setBooleanResult(boolean booleanResult);
    public double getDoubleResult();
    public void setDoubleResult(double doubleResult);
    public String getExpr();
}
