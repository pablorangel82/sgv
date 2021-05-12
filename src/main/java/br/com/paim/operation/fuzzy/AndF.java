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
import br.com.paim.type.NumericalData;
import java.util.ArrayList;

public class AndF extends BinaryOperation implements IFuzzyOperation {

        public AndF(NumericalData data1, NumericalData data2){
            super(data1,data2);
        }

        public AndF(NumericalData data1, double data2){
            super(data1,data2);
        }

        public AndF(double data1, NumericalData data2){
            super(data1,data2);
        }

        public AndF(double data1, double data2){
            super(data1,data2);
        }

        public AndF(IFuzzyOperation operation, NumericalData data){
            super(operation,data);
        }

        public AndF(NumericalData data, IFuzzyOperation operation){
            super(data,operation);
        }

        public AndF(IFuzzyOperation operation, double data){
            super(operation,data);
        }

        public AndF(double data, IFuzzyOperation operation){
            super(data,operation);
        }

        public AndF(IFuzzyOperation operation1, IFuzzyOperation operation2){
            super(operation1,operation2);
        }

        public String getSymbol(){
            return "AndF";
        }

        public double doFuzzyOperation() {
            ArrayList datas = super.getDataList();
            double mi1 = ((NumericalData)datas.get(0)).getValue();
            double mi2 = ((NumericalData)datas.get(1)).getValue();
            return mi1<=mi2?mi1:mi2;
	}
	 
}

