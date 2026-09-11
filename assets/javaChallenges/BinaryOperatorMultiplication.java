import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Challenge106 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the values of A");
        int a= input.nextInt();
        System.out.println("Enter the value of B");
        int b= input.nextInt();
        BinaryOperator<Integer> mul=(t,c)->t*c;
        int result=mul.apply(a,b);
        System.out.println(result);
    }
}
