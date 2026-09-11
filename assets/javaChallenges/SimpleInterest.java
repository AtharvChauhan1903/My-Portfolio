import java.util.Scanner;

 class challenge12 {

        static void main() {
            System.out.println("Enter the parameters for interest and amount:");
            Scanner input=new Scanner(System.in);
            System.out.print("Enter the Principle Amount:");
            float principle=input.nextFloat();
            System.out.print("Enter the Rate of Interest:");
            float rateOfInterest=input.nextFloat();
            System.out.print("Enter the Time(in years):");
            float time=input.nextFloat();
            System.out.println("Simple interest is "+(principle*rateOfInterest*time/100));
            System.out.println("The final amount received is "+((principle*rateOfInterest*time/100)+principle));
        }
    }

