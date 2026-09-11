import java.util.Scanner;

class challenge15 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num=input.nextInt();
        if(num<0)
            System.out.println("The number is negative");
        if(num==0)
            System.out.println("The number is 0");
        if(num>0)
            System.out.println("The Number is positive");
    }
}
