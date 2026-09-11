import java.util.Scanner;

public class challenge36other {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number:");
        int num= input.nextInt();
        if (num==1)
            System.out.println("0");
        else if(num==2)
            System.out.println("0,1");
        else if(num>2){
            int a=0;
            int b=1;
            int c=1;
            System.out.print(a+" "+b+" ");


            for(int i=0;i<(num-2);i++){
                c=a+b;
                System.out.print(c+" ");
                a=b;
                b=c;
            }
        }
    }
}
