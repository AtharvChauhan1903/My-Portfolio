import java.util.Scanner;

public class BitwiseOddEven {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your number");
        int num= input.nextInt();
        if((num&1)==0)
            System.out.println("Even");
        else
            System.out.println("Odd");
    }
}
