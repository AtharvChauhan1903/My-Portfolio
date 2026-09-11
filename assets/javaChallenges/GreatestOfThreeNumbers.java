import java.util.Scanner;

class challenge17 {
    static void main() {
        Scanner input=new Scanner(System.in);
        int num1,num2,num3;
        System.out.println("Enter the number 1");
        num1= input.nextInt();
        System.out.println("Enter the number 2");
        num2= input.nextInt();
        System.out.println("Enter the number 3");
        num3= input.nextInt();
        if(num1>num2&&num1>num3)
            System.out.println("Number 1 is the greatest");
        if(num2>num1&&num2>num3)
            System.out.println("Number 2 is the greatest");

        if(num3>num1&&num3>num2)
            System.out.println("Number 3 is the greatest");
    }
}
