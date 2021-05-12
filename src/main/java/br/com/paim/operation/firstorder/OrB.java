/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.firstorder;

import br.com.paim.operation.BinaryOperation;
import br.com.paim.operation.IFirstOrderOperation;
import br.com.paim.type.BooleanData;
import java.util.ArrayList;

/**
 *
 * @author pablo
 */



public class OrB extends BinaryOperation implements IFirstOrderOperation{

        public OrB(BooleanData data1, BooleanData data2){
            super(data1,data2);
        }

        public OrB(BooleanData data1, boolean data2){
            super(data1,data2);
        }

        public OrB(boolean data1, BooleanData data2){
            super(data1,data2);
        }

        public OrB(boolean data1, boolean data2){
            super(data1,data2);
        }

        public OrB(IFirstOrderOperation operation, BooleanData data){
            super(operation,data);
        }

        public OrB(BooleanData data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public OrB(IFirstOrderOperation operation, boolean data){
            super(operation,data);
        }

        public OrB(boolean data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public OrB(IFirstOrderOperation operation1, IFirstOrderOperation operation2){
            super(operation1,operation2);
        }

        public String getSymbol(){
            return "OR";
        }

	public boolean doFirstOrderOperation() {
            ArrayList datas = super.getDataList();
            boolean data1 = ((BooleanData)datas.get(0)).getValue();
            boolean data2 = ((BooleanData)datas.get(1)).getValue();
            return data1 || data2;
	}
}
 
