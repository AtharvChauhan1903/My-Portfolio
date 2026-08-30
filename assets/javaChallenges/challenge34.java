import java.util.Scanner;

public class challenge34 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        boolean isprime=isprime(num);
        System.out.println(isprime);
    }
    static boolean isprime(int num){
        int count=0;
        for(int i=1;i<=num;i++){
            if(num%i==0)
                count++;
        }
        if(count==2)
            return true;
        else
            return false;
    }
}
