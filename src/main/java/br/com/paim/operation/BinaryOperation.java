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
import br.com.paim.type.FuzzyFunctionData;
import br.com.paim.type.GenericData;
import br.com.paim.type.NumericalData;
import br.com.paim.type.ObservableData;
import java.util.ArrayList;

public abstract class BinaryOperation extends Operation {
        private IOperation              operation1;
        private IOperation              operation2;
	private GenericData             data1;
	private GenericData             data2;

        public BinaryOperation(){
            this.operation1=null;
            this.operation2=null;
            this.data1=null;
            this.data2=null;
        }

        public BinaryOperation(NumericalData data1, double data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=data1;
            this.data2=new NumericalData(data2);
        }

        public BinaryOperation(double data1, NumericalData data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=new NumericalData(data1);
            this.data2=data2;
        }

        public BinaryOperation(double data1, double data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=new NumericalData(data1);
            this.data2=new NumericalData(data2);
        }


        public BinaryOperation(BooleanData data1, boolean data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=data1;
            this.data2=new BooleanData(data2);
        }

        public BinaryOperation(boolean data1, BooleanData data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=new BooleanData(data1);
            this.data2=data2;
        }

        public BinaryOperation(boolean data1, boolean data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=new BooleanData(data1);
            this.data2=new BooleanData(data2);
        }


        public BinaryOperation(double data1, FuzzyFunctionData data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=new NumericalData(data1);
            this.data2=data2;
        }

        public BinaryOperation(GenericData data1, GenericData data2){
            this.operation1=null;
            this.operation2=null;
            this.data1=data1;
            this.data2=data2;
        }

        public BinaryOperation(IOperation operation1, GenericData data2){
            this.operation1=operation1;
            this.operation2=null;
            this.data1=null;
            this.data2=data2;
        }

        public BinaryOperation(GenericData data1,IOperation operation2){
            this.operation1=null;
            this.operation2=operation2;
            this.data1=data1;
            this.data2=null;
        }

        public BinaryOperation(IOperation operation1, double data2){
            this.operation1=operation1;
            this.operation2=null;
            this.data1=null;
            this.data2=new NumericalData(data2);
        }

        public BinaryOperation(double data1, IOperation operation2){
            this.operation1=null;
            this.operation2=operation2;
            this.data1=new NumericalData(data1);
            this.data2=null;
        }

        public BinaryOperation(IOperation operation1, boolean data2){
            this.operation1=operation1;
            this.operation2=null;
            this.data1=null;
            this.data2=new BooleanData(data2);
        }

        public BinaryOperation(boolean data1, IOperation operation2){
            this.operation1=null;
            this.operation2=operation2;
            this.data1=new BooleanData(data1);
            this.data2=null;
        }

        public BinaryOperation(IOperation operation1, IOperation operation2){
            this.operation1=operation1;
            this.operation2=operation2;
            this.data1=null;
            this.data2=null;
        }

        public void setData1(GenericData data1){
            this.data1=data1;
        }

        public void setData2(GenericData data2){
            this.data2=data2;
        }

        public ArrayList getDataList() {
            ArrayList list = new ArrayList();
            if (this.data1 != null)
                list.add(data1);
            if (this.data2 != null)
                list.add(data2);
            return list;
	}
        
	public ArrayList getObjectList() {
            ArrayList list = new ArrayList();
            if (this.operation1 != null)
                list.add(operation1);
            if (this.operation2 != null)
                list.add(operation2);
            if (operation1 != null && operation2 != null)
                return list;
            if (this.data1 != null)
                list.add(data1);
            if (this.data2 != null)
                list.add(data2);
            return list;
	}

        public String getExpr(){
            ArrayList datas = getDataList();
            String expr = "";
            int i=0;
            Object [] objs = new Object[2];
            while (i < 2){
                if (datas.get(i) instanceof ObservableData){
                    if (datas.get(i) instanceof NumericalData){
                        Double v = 0.0;
                        v = ((NumericalData)datas.get(i)).getValue();
                        objs [i] = v;
                    }
                    if (datas.get(i) instanceof BooleanData){
                        Boolean v = false;
                        v = ((BooleanData)datas.get(i)).getValue();
                        objs [i] = v;
                    }
                }
                if (datas.get(i) instanceof FuzzyFunctionData){
                    objs [i] = ((FuzzyFunctionData)datas.get(i)).getDescription();
                }
                i++;
            }
            expr = objs[0] + " " + getSymbol() + " " + objs[1];
            return expr;
        }

}
 
