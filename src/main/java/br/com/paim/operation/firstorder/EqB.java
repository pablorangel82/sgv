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



public class EqB extends BinaryOperation implements IFirstOrderOperation{

        public EqB(NumericalData data1, NumericalData data2){
            super(data1,data2);
        }

        public EqB(NumericalData data1, double data2){
            super(data1,data2);
        }

        public EqB(double data1, NumericalData data2){
            super(data1,data2);
        }

        public EqB(double data1, double data2){
            super(data1,data2);
        }

        public EqB(IFirstOrderOperation operation, NumericalData data){
            super(operation,data);
        }

        public EqB(NumericalData data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public EqB(IFirstOrderOperation operation, double data){
            super(operation,data);
        }

        public EqB(double data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public EqB(IFirstOrderOperation operation1, IFirstOrderOperation operation2){
            super(operation1,operation2);
        }

        public String getSymbol(){
            return "=";
        }

	public boolean doFirstOrderOperation() {
            ArrayList datas = super.getDataList();
            Object obj1 = (datas.get(0));
            Object obj2 = (datas.get(1));
            double data1 = ((NumericalData)obj1).getValue();
            double data2 = ((NumericalData)obj2).getValue();
            return data1 == data2 ? true:false;
	}
	 
}
 
