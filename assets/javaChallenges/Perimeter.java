import java.sql.SQLOutput;
import java.util.Scanner;

class challenge10 {
    static void main() {
        System.out.println("Enter the four sides of rectangle:");
       Scanner input=new Scanner(System.in);
        System.out.print("Enter the side 1:");
        int num1=input.nextInt();
        System.out.print("Enter the side 2:");
        int num2=input.nextInt();
        System.out.print("Enter the side 3:");
        int num3=input.nextInt();
        System.out.print("Enter the side 4:");
        int num4=input.nextInt();
        System.out.println("Perimeter of the rectangle is "+(num1+num2+num3+num4)+"units");
    }
}
