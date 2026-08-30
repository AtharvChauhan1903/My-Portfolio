import java.util.Scanner;

public class challenge30 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        int sol=sol(num);
        System.out.println(sol);

    }
    static int sol(int num){
        int sol=1;
        if(num==1)
            sol=1;
        else {
            for(int i=1;i<=num;i++)
                sol*=i;
        }
        return sol;
    }
}
