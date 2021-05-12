/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.firstorder;

/**
 *
 * @author pablo
 */

import br.com.paim.operation.IFirstOrderOperation;
import br.com.paim.operation.UnaryOperation;
import br.com.paim.type.BooleanData;
import java.util.ArrayList;

public class NotB extends UnaryOperation implements IFirstOrderOperation{

        public NotB(BooleanData data){
            super(data);
        }

        public NotB(boolean data){
            super(data);
        }

        public NotB(IFirstOrderOperation operation){
            super(operation);
        }

        public String getSymbol(){
            return "NOT";
        }

	public boolean doFirstOrderOperation() {
            ArrayList datas = super.getDataList();
            Boolean data = ((BooleanData)datas.get(0)).getValue();
            return !data;
	}
	 
}

