package Challenge111;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Challenge111 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number whose factorial you want");
        int sok= input.nextInt();
        System.out.println(Factorial(sok));
        int sol=IntStream.rangeClosed(1,sok)
                .reduce(1,(a,b)->a*b);
        System.out.println(sol);
    }
    public static int Factorial(int sok){
        if(sok<=1)
            return 1;
        else
            return sok*Factorial(sok-1);
    }
}
