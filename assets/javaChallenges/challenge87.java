import java.util.Scanner;

public class challenge87 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the first number");
        int a= input.nextInt();
        System.out.println("Enter the second number");
        int b= input.nextInt();
        double sol=div(a,b);
        System.out.println(sol);

    }
    public static double div(int a,int b){
        double sol=0;
        try{
          sol=a/b;
        } catch (ArithmeticException e) {
            System.out.println("Denominator should not be zero");
            return Double.NaN;
        }
        return sol;
    }
}
