import java.util.Scanner;

public class challenge20 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter your age");
        int age= input.nextInt();
        if(age<13)
            System.out.println("Hello child");
        else if(age<20)
            System.out.println("Hello Teenager");
        else if(age<60)
            System.out.println("Hello Adult");
        else
            System.out.println("Hello senior citizen");
    }
}
