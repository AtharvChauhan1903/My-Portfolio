import java.util.Scanner;

public class challenge31 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        int result=sum(num);
        System.out.println(result);
    }
    static int sum(int num){
        int sum=0;
        int i=0;
        while(num/10>1){
            i=num%10;
            sum+=i;
            num=num/10;
        }
        if((num/10)<1)
            sum+=num;
        return sum;
    }
}
