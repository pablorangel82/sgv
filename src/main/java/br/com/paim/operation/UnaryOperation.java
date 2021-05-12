/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation;

/**
 *
 * @author pablo
 */


import br.com.paim.type.BooleanData;
import br.com.paim.type.NumericalData;
import br.com.paim.type.ObservableData;
import java.util.ArrayList;

public abstract class UnaryOperation extends Operation{
    private IOperation      operation;
    private ObservableData  data;

    public UnaryOperation(){
        this.operation=null;
        this.data=null;
    }

    public UnaryOperation(ObservableData data){
        this.operation=null;
        this.data=data;
    }

    public UnaryOperation(boolean data){
        this.operation=null;
        this.data=new BooleanData();
        ((BooleanData)this.data).setValue(data);
    }

    public UnaryOperation(double data){
        this.operation=null;
        this.data=new NumericalData();
        ((NumericalData)this.data).setValue(data);
    }

    public UnaryOperation(IOperation operation){
        this.data=null;
        this.operation=operation;
    }

    public void setData(ObservableData data){
        this.data=data;
    }

    public ArrayList getDataList() {
        ArrayList list = new ArrayList();
        if (data != null)
            list.add(data);
        return list;
    }

    public ArrayList getObjectList() {
        ArrayList list = new ArrayList();
        if (data != null)
            list.add(data);
        if (operation != null)
            list.add(operation);
        return list;
    }

     public String getExpr(){
        ArrayList datas = getDataList();
        String expr = "";
        if (datas.get(0) instanceof NumericalData){
            double dt1 = ((NumericalData)datas.get(0)).getValue();
            expr = getSymbol() + " " + Double.toString(dt1);
        }
        if (datas.get(0) instanceof BooleanData){
            boolean dt1 = ((BooleanData)datas.get(0)).getValue();
            expr = getSymbol() + " " + Boolean.toString(dt1);
        }
        return expr;
    }

}
 
