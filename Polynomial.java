import lab2.Polynomial;

public class Polynomial {
    private double[] coefficients;
    public Polynomial(){
        coefficients = new double[]{0};
    }
    public Polynomial(double[] newlist){
        coefficients = newlist; 
    }
    public Polynomial add(Polynomial p){
        int length1 = p.coefficients.length;
        int length2 = this.coefficients.length;
        int maxlen = Math.max(length1, length2);
        double[] result = new double[maxlen];
        for(int i = 0; i<maxlen; i++)
        {   double a = 0;
            double b = 0;
            if(i<length1)
            {
                a = p.coefficients[i];
            }
            if(i<length2)
            {
                b = this.coefficients[i];
            }
            result[i] = a+b;
        }
    return new Polynomial(result);
    }
    public double evaluate(double x){
        double total = 0;
        for(int i = 0; i<this.coefficients.length; i++)
        {
            total = total + this.coefficients[i] * Math.pow(x,i);
        }
        return total;
    }
    public boolean hasRoot(double y){
        double result = evaluate(y);
        if(result == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
