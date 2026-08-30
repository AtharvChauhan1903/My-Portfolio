import java.util.Scanner;


public class challenge13 {
    static void main() {
        System.out.println("Enter the parameters for interest and amount:");
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the Principle Amount:");
        float P=input.nextFloat();
        System.out.print("Enter the Rate of Interest:");
        float rateOfInterest=input.nextFloat();
        System.out.print("Enter the Time(in years):");
        float time=input.nextFloat();
        double compoundInterest=Math.pow((1+rateOfInterest/100),time)*P;
        System.out.println("Compound Interest is "+compoundInterest);
        System.out.println("Final amount is "+(P+compoundInterest));
    }
}
