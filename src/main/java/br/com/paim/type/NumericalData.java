/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.type;

/**
 *
 * @author pablo
 */

public class NumericalData extends ObservableData {
    double value;

    public NumericalData(double value){
        this.value=value;
    }

    public NumericalData(){
        this.value=0.0;
    }

    public final void setValue(double value){
        this.value=value;
        super.investigate();
    }

    public final double getValue(){
        return this.value;
    }
	 
}
 
