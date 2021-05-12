/*
 * To change this template, choose Tools | Templates
 * and  Teste 2open the template in the editor.
 */

package br.com.paim.type;

/**
 *
 * @author Bla  pablo
 * teste
 */

public class BooleanData extends ObservableData {
    boolean value;

    public BooleanData(){
        this.value=false;
    }

    public BooleanData(boolean value){
        this.value=value;
    }

    public final void setValue(boolean value){
        this.value=value;
        super.investigate();
    }

    public final boolean getValue(){
        return this.value;
    }
	 
}
 
