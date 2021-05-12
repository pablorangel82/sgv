/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.operation.fuzzy;

/**
 *
 * @author pablo
 */



import br.com.paim.operation.BinaryOperation;
import br.com.paim.operation.IFuzzyOperation;
import br.com.paim.type.FuzzyFunctionData;
import br.com.paim.type.NumericalData;
import java.util.ArrayList;

public class IsF extends BinaryOperation implements IFuzzyOperation{
        
        public IsF(NumericalData data1, FuzzyFunctionData data2){
            super(data1,data2);
        }

        public IsF(double data1, FuzzyFunctionData data2){
            super(data1,data2);
        }

        
        public String getSymbol(){
            return "IsF";
        }

	public double doFuzzyOperation() {
            ArrayList datas = super.getObjectList();
            double data      = ((NumericalData)datas.get(0)).getValue();
            FuzzyFunctionData ffd = ((FuzzyFunctionData)datas.get(1));
            return ffd.getPertinence(data);
	}
	 
}

