import java.util.Scanner;

public class challenge29 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        int sol=solution(num);
        System.out.println(sol);
    }
    static int solution( int num){
        int sol=0;
        if(num==1){
             sol=1;
        return sol;}
        else if(num<=0)
            sol= 0;
        else{
            for(int i=1;i<=num;i++)
                if(i%2!=0)
                    sol+=i;

        }
        return sol;
    }
}
