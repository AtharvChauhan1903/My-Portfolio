import java.util.Scanner;

public class TernaryOddEven {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        String result=(num%2==0)?"Even":"Odd";
        System.out.println(result);
    }
}
