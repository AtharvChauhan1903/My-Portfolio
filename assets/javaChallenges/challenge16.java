import java.util.Scanner;

class challenge16 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num=input.nextInt();
        if(num%2==0)
            System.out.println("The number is even");
        if(num%2!=0)
            System.out.println("The number is odd");
       }
}

