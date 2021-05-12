/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.firstorder;

/**
 *
 * @author pablo
 */

import br.com.paim.operation.BinaryOperation;
import br.com.paim.operation.IFirstOrderOperation;
import br.com.paim.type.BooleanData;
import java.util.ArrayList;

public class XOrB extends BinaryOperation implements IFirstOrderOperation{

        public XOrB(BooleanData data1, BooleanData data2){
            super(data1,data2);
        }

        public XOrB(BooleanData data1, boolean data2){
            super(data1,data2);
        }

        public XOrB(boolean data1, BooleanData data2){
            super(data1,data2);
        }

        public XOrB(boolean data1, boolean data2){
            super(data1,data2);
        }

        public XOrB(IFirstOrderOperation operation, BooleanData data){
            super(operation,data);
        }

        public XOrB(BooleanData data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public XOrB(IFirstOrderOperation operation, boolean data){
            super(operation,data);
        }

        public XOrB(boolean data, IFirstOrderOperation operation){
            super(data,operation);
        }

        public XOrB(IFirstOrderOperation operation1, IFirstOrderOperation operation2){
            super(operation1,operation2);
        }

        public String getSymbol(){
            return "XOR";
        }

	public boolean doFirstOrderOperation() {
            ArrayList datas = super.getDataList();
            boolean p = ((BooleanData)datas.get(0)).getValue();
            boolean q = ((BooleanData)datas.get(1)).getValue();
            return ( (p && q) || (!p && !q) );
	}
}
 
