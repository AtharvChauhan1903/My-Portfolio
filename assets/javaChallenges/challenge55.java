import java.util.Scanner;

public class challenge55 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        num=(num<0)?(num*(-1)):num;
        System.out.println(num);
    }
}
