/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.paim.type;

/**
 *
 * @author pablo
 */
public class TrapezeFunctionData extends FuzzyFunctionData{

    private double points [] = new double[4];


    public TrapezeFunctionData(double a, double b, double c, double d){
        double [] pts = new double [4];
        pts [0] = a;
        pts [1] = b;
        pts [2] = c;
        pts [3] = d;
        this.setValue(pts);
    }

    public TrapezeFunctionData(double a, double b, double c, double d, String description){
        this (a,b,c,d);
        super.setDescription(description);
    }

    public double getAverage(){
        if ((points[0]==points[1]) && (points[1]==points[2]) && (points[2]==points[3]))
            return points[0];

        if ((points[1]==points[2]))
            return points[1];
        else
            return ((points[2] + points[1]) /2);
    }

    public void setValue(double [] value){
        if (value == null)
            return;
        if (value.length <4)
            return;

        if ( (value[0]>value[1]) || (value[0]>value[2]) || (value[0]>value[3]) )
            return;
        if ( (value[1]>value[2]) || (value[1]>value[3]) )
            return;
        if ( (value[2]>value[3]) )
            return;

        this.points = value;
    }

    public double [] getValue(){
        return this.points;
    }

    /* Example: (10,10,10,10); */
    public boolean isSingleton (double data){
        if ((points[0]==points[1]) && (points[1]==points[2]) && (points[2]==points[3])){
           if (data == points [0])
               super.setPertinence(1.0);
            else
               super.setPertinence(0.0);
            return true;
        }
        return false;
    }

    /* Example: (0,0,8,9) */
    public boolean isRightShoulder (double data){
        if ((points[0]==points[1]) && (points[1]!=points[2]) && (points[2]!=points[3])){
           if (data < points [0] || data >= points [3])
              super.setPertinence(0.0);
            else
                if (data >= points [1] && data <= points [2])
                    super.setPertinence(1.0);
                else
                    super.setPertinence(((points[3]-data)/(points[3]-points[2])));
            return true;
        }
        return false;
    }

    /* Example: (0,30,40,40); */
    public boolean isLeftShoulder (double data){
        if ((points[0]<points[1]) && (points[1] < points[2]) && (points[2]==points[3])){
           if (data <= points [0] || data > points [3])
              super.setPertinence(0.0);
            else
                if (data >= points [1] && data <= points [2])
                    super.setPertinence(1.0);
                else
                    super.setPertinence(((data-points[0])/(points[1]-points[0])));
            return true;
        }
        return false;
    }

    /* Example: (0,10,10,10); */
    public boolean isLeftArm (double data){
        if ((points[0]<points[1]) && (points[1] == points[2]) && (points[2]==points[3])){
           if (data <= points [0] || data > points [3])
                super.setPertinence(0.0);
            else
                super.setPertinence(((data-points[0])/(points[1]-points[0])));
            return true;
        }
        return false;
    }

    /* Example: (15,15,15,30); */
    public boolean isRightArm (double data){
        if ((points[0]==points[1]) && (points[1]==points[2]) && (points[2]!=points[3])){
            if (data < points [0] || data >= points [3])
               super.setPertinence(0.0);
            else
               super.setPertinence(((points[3]-data)/(points[3]-points[2])));
            return true;
        }
        return false;
    }


    public boolean isRegularShoulder (double data){
        if ((points[0]!=points[1]) && (points[1]!=points[2]) && (points[2]!=points[3])){
           if (data <= points [0] || data >= points [3])
              super.setPertinence(0.0);
            else
                if (data >= points [1] && data <= points [2])
                    super.setPertinence(1.0);
                else
                    if (data < points [1])
                        super.setPertinence(((data-points[0])/(points[1]-points[0])));
                    else
                        super.setPertinence(((points[3]-data)/(points[3]-points[2])));
            return true;
        }
        return false;
    }
    
    /* Example: (0,20,20,40) */
    public boolean isTriangle (double data){
        if ((points[0]<points[1]) && (points[1]==points[2]) && (points[2]<points[3])){
           if (data <= points [0] || data >= points [3])
              super.setPertinence(0.0);
            else
                if (data == points [1])
                    super.setPertinence(1.0);
                else
                    if (data < points [1])
                        super.setPertinence(((data-points[0])/(points[1]-points[0])));
                    else
                        super.setPertinence(((points[3]-data)/(points[3]-points[2])));
            return true;
        }
        return false;
    }

    public double getPertinence(double data){
        if (isSingleton(data)){
            return getPertinence();
        }
        if (isLeftArm(data)){
            return getPertinence();
        }
        if (isLeftShoulder(data)){
            return getPertinence();
        }
        if (isRegularShoulder(data)){
            return getPertinence();
        }
        if (isRightArm(data)){
            return getPertinence();
        }
        if (isRightShoulder(data)){
            return getPertinence();
        }
        if (isSingleton(data)){
            return getPertinence();
        }
        if (isTriangle(data)){
            return getPertinence();
        }
        return super.getPertinence();
    }

}
