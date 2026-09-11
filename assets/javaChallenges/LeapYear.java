import java.util.Scanner;

public class challenge18 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.print("Enter the year:");
        int year= input.nextInt();
        if(year%4==0)
            System.out.println("This is a leap year");
        else
            System.out.println("This is not a leap year");
    }
}
