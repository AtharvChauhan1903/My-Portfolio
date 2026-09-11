import java.util.Scanner;

public class challenge53 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the first number");
        int num= input.nextInt();
        System.out.println("Enter the first number");
        int num2= input.nextInt();
        int result=(num<=num2)?num:num2;
        System.out.println("The minimum of the two given number is "+result);
    }
}
