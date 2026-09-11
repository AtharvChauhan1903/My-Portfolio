import java.util.Scanner;

public class challenge37 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        int original=num;
        if(original==armstrongCalculation(num))
            System.out.println("True");
        else
            System.out.println("False");
    }
    static int numberCount(int num){
        int count =0;
        while(num>0){
            count++;
            num/=10;
        }

        return count;
    }
    static int armstrongCalculation(int num){
        int sum=0;
        int count=numberCount(num);
        while(num>0){
            int rem=num%10;
            int newdigit= (int)Math.pow(rem,count);
            sum+=newdigit;
            num/=10;
        }
        return sum;
    }
}
