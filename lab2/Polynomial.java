package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Polynomial {
    private double[] coefficients;
    private int[] exponents;
    public Polynomial(){
        coefficients = new double[]{};
        exponents = new int[]{};
    }
    public Polynomial(double[] newlist, int[] exponents){
        int count = 0;
        for(int i = 0; i<newlist.length; i++)
        {
            if(newlist[i] != 0)
            {
                count++; 
            }
        }
        double[] latest_list = new double[count];
        int[] latest_exp = new int[count];
        int z = 0;
        for(int j = 0; j<newlist.length; j++)
        {
            if(newlist[j] != 0)
            {
                latest_list[z] = newlist[j];
                latest_exp[z] = exponents[j];
                z++;
            }
        }
        coefficients = latest_list; 
        this.exponents = latest_exp;
    }
    public Polynomial add(Polynomial p){
         
         int numthis = this.coefficients.length;
         int nump = p.coefficients.length;
         double[] temp_coeff = new double[numthis + nump];
         int [] temp_exp = new int[numthis + nump];
         int i = 0;
         int j = 0;
         int k = 0;
         while(i<nump && j<numthis)
         {
            if(this.exponents[j] == p.exponents[i])
            {   double total = this.coefficients[j] + p.coefficients[i];
                if(total != 0)
                {   temp_coeff[k] = total;
                    temp_exp[k] = this.exponents[j];
                    k++;
                }
                i++;
                j++;
            }
            else if(this.exponents[j] < p.exponents[i])
            {
                temp_coeff[k] = this.coefficients[j];
                temp_exp[k] = this.exponents[j];
                j++;
                k++;
            }
            else{
                temp_coeff[k] = p.coefficients[i];
                temp_exp[k] = p.exponents[i];
                i++;
                k++;
            }
         }
         while(j<numthis)
         {
            temp_coeff[k] = this.coefficients[j];
            temp_exp[k] = this.exponents[j];
            j++;
            k++;
         }
         while(i<nump)
         {
            temp_coeff[k+1] = p.coefficients[i];
            temp_exp[k+1] = p.exponents[i];
            i++;
            k++;
         }
         return new Polynomial(temp_coeff, temp_exp);
    }


    public Polynomial multiply(Polynomial p){
        int numthis = this.coefficients.length;
        int nump = p.coefficients.length;
        double[] temp_coeff = new double[numthis * nump];
        int [] temp_exp = new int[numthis * nump];
        int z = 0;
        for(int i = 0; i<numthis; i++)
        {
            for(int j = 0; j<nump; j++)
            {
                temp_coeff[z] = this.coefficients[i] * p.coefficients[j];
                temp_exp[z] = this.exponents[i] + p.exponents[j];
                z++;
            }
        }
        for(int i = 0; i<z; i++){
            if(temp_coeff[i] == 0) continue;
            for(int j = i+1; j<z; j++)
            {
                if(temp_exp[i] == temp_exp[j])
                {
                    temp_coeff[i] = temp_coeff[i] + temp_coeff[j];
                    temp_coeff[j] = 0;
                }
            }
        }
        int count = 0;
        for(int i = 0; i<z; i++)
        {
            if(temp_coeff[i] != 0)
            {
                count ++;
            }
        }
        double[] final_coeff = new double[count];
        int [] final_exp = new int[count];
        int a = 0;
        for(int i = 0; i<z; i++){
            if(temp_coeff[i]!=0)
            {
                final_coeff[a] = temp_coeff[i];
                final_exp[a] = temp_exp[i];
                a++;
            }
        }
        return new Polynomial(final_coeff, final_exp);

    }
    public Polynomial(File file) throws FileNotFoundException
    {
        Scanner input = new Scanner(file);
        String line = input.nextLine();
        input.close();
        line = line.replace("+", "#+");
        line = line.replace("-", "#-");
        String[] newline = line.split("#");
        double[] coefficients = new double[newline.length];
        int[] exponents = new int[newline.length];
        for(int i=0; i<newline.length; i++)
        {
            if(newline[i].contains("x"))
            {
                String[] templine = newline[i].split("x");
                coefficients[i] = Double.parseDouble(templine[0]);
                exponents[i] = Integer.parseInt(templine[1]);
            }
            else{
                coefficients[i] = Double.parseDouble(newline[i]);
                exponents[i] = 0;
            }
        }
    }

    public double evaluate(double x){
        double total = 0;
        for(int i = 0; i<this.coefficients.length; i++)
        {
            total = total + this.coefficients[i] * Math.pow(x,this.exponents[i]);
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

    public void saveToFile(String file_name) throws IOException{
        PrintStream output = new PrintStream(file_name);
        int coef_len = this.coefficients.length;
         for(int i = 0; i<coef_len; i++)
         {  
            if(this.coefficients[i] > 0 && i>0)
            {
                output.print("+" );
            }
            output.print(this.coefficients[i]);
            if(this.exponents[i]!=0)
            {
                output.print("x" + this.exponents[i]);
            }
         }
         output.close();
}
public double[] getCoefficients() {
    return coefficients;
}
public int[] getExponents() {
    return exponents;
}
}
