/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.type;

/**
 *
 * @author pablo
 */

public abstract class FuzzyFunctionData extends GenericData{
    private double pertinence = 0.0;
    private String description = "";

    public abstract double getAverage();
    public abstract void   setValue(double [] value);
    public abstract double [] getValue();

    public abstract double getPertinence(double data);
    
    protected double getPertinence() {
        return pertinence;
    }

    public void setPertinence(double pertinence) {
        this.pertinence = pertinence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
