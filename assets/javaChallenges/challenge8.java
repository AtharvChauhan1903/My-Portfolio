import java.util.Scanner;

class challenge8{
    static void main() {
        Scanner input= new Scanner(System.in) ;
        System.out.print("Enter the first number:");
        int num1= input.nextInt();
        System.out.print("Enter the second number:");
        int num2= input.nextInt();
        System.out.println("Sum is "+(num1+num2));
        System.out.println("Difference is "+(num1-num2));
        System.out.println("Product is "+(num1*num2));
        System.out.println("quotient is "+(num1/num2));
        System.out.println("remainder is "+(num1%num2));
        float num= input.nextFloat();
        float num3= input.nextFloat();
        System.out.println("product is"+(num*num3));
    }
}