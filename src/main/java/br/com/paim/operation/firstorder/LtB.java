/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.firstorder;

import br.com.paim.operation.BinaryOperation;
import br.com.paim.operation.IFirstOrderOperation;
import br.com.paim.type.NumericalData;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */



public class LtB extends BinaryOperation implements IFirstOrderOperation{

        public LtB(NumericalData data1, NumericalData data2){
            super(data1,data2);
        }

        public LtB(NumericalData data1, double data2){
            super(data1,data2);
        }

        public LtB(double data1, NumericalData data2){
            super(data1,data2);
        }

        public LtB(double data1, double data2){
            super(data1,data2);
        }

        public LtB(IFirstOrderOperation operation, NumericalData data){
            super(operation,data);
        }

        public LtB(NumericalData data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public LtB(IFirstOrderOperation operation, double data){
            super(operation,data);
        }

        public LtB(double data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public LtB(IFirstOrderOperation operation1, IFirstOrderOperation operation2){
            super(operation1,operation2);
        }

        public String getSymbol(){
            return "<";
        }

	public boolean doFirstOrderOperation() {
            ArrayList datas = super.getDataList();
            Object obj1 = (datas.get(0));
            Object obj2 = (datas.get(1));
            double data1 = ((NumericalData)obj1).getValue();
            double data2 = ((NumericalData)obj2).getValue();
            boolean res = data1 < data2 ? true:false;
            return res;
	}
	 
}
 
