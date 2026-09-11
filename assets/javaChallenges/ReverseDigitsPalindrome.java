import java.util.Scanner;

public class challenge35and38 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num1= input.nextInt();
        int reverse=reverse(num1);
        boolean ispalindrome=isPalindrome(num1,reverse);
        System.out.println(reverse);
        System.out.println("IS palindrome: "+ispalindrome);
    }
    static int reverse(int num){
        int rev=0;int rem=0;
        while(num%10>=1){
            rem=num%10;
            rev=(rev*10)+rem;
            num/=10;
        }
        if(num%10<1&&num!=0)
            rev=(rev*10)+num;
        return rev;
    }
    static boolean isPalindrome(int num1,int num2){
        if(num1==num2)
            return true;
        else
            return false;
    }
}
