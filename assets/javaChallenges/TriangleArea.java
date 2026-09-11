import java.util.Scanner;

 class challenge11 {

        static void main() {
            System.out.println("Enter the height and breadth of the triangle:");
            Scanner input=new Scanner(System.in);
            System.out.print("Enter the height:");
            float num1=input.nextInt();
            System.out.print("Enter the breadth:");
            float num2=input.nextInt();
            float area=(num1*num2/2);
            System.out.println("Area of the rectangle is "+area+" units");
        }
    }

