import java.util.Scanner;

public class challenge32 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter number");
        int num1= input.nextInt();
        System.out.println("Enter number");
        int num2= input.nextInt();
        int lcm=lcm(num1,num2);
        System.out.println(lcm);
    }
    static int lcm(int num1,int num2){
        int high=(num1>num2)?num1:num2;
        while (true) {
            if(high%num1==0&&high%num2==0){
                return high;
            }
            high++;
        }
    }
}
