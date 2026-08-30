import java.util.Scanner;

public class challenge33 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the first number");
        int num1= input.nextInt();
        System.out.println("Enter the second number");
        int num2= input.nextInt();
        int lcm=lcm(num1,num2);
        System.out.println(lcm);
    }
    static int lcm(int num1,int num2){
        int low,high,lcm=0;
        if(num1<=num2){
            low=num1;
            high=num2;
        }
        else {
            low=num2;
            high=num1;
        }
        for(int i=low;(low%i==0)&(high%i==0)&(i>1);i--){
            lcm=i;
        }
        return lcm;
    }
}
