import java.util.Scanner;

public class challenge2126 {
    static void main() {
        Scanner input =new Scanner(System.in);
        System.out.println("Enter the first number");
        int num1= input.nextInt();
        System.out.println("Enter the second number");
        int num2= input.nextInt();
        System.out.println("And operator in num1 and num2 is "+(num1&num2));
        System.out.println("OR operation between num1 and num2 is "+(num1|num2));
        System.out.println("XOR operation between num1 and num2 is "+(num1^num2));
        System.out.println("XOR operation between num1 and num2 is "+(~num1)+"and "+(~num2));
        System.out.println("Or operation between num1 and num2 is "+(num1>>1)+"and for num2: "+(num2>>1));
        System.out.println("Or operation between num1 and num2 is "+(num1<<1)+"and for num2: "+(num2<<1));
    }
}
