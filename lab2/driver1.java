package lab2;

public class driver1{
    public static void main(String [] args){
        double[] coefficients1 = {1, 3, 2, 4};
        int[] exponents1 = {0, 2, 3, 4};
        Polynomial p1 = new Polynomial(coefficients1, exponents1);

        double[] coefficients2 = {2, 3, 2};
        int[] exponents2 = {2, 3, 4};
        Polynomial p2 = new Polynomial(coefficients2, exponents2);

        System.out.println("Test add....");
        Polynomial result1 = p1.add(p2);

        System.out.println(result1.getCoefficients());
        System.out.println(result1.getExponents());
        double[] r1coef = result1.getCoefficients();

        for(int i = 0; i<r1coef.length; i++)
        {
            System.out.println(r1coef[i] + " ");
        }

        System.out.println("Test multiply");
        Polynomial result2 = p1.multiply(p2);

        System.out.println(result2.getCoefficients());
        System.out.println(result2.getExponents());
        double[] r2coef = result2.getCoefficients();
        for(int i = 0; i<r2coef.length; i++)
        {
            System.out.println(r2coef[i] + " ");
        }
    }
}