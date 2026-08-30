import java.util.Scanner;

public class challenge28 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number for the table");
        int num= input.nextInt();
        for(int i=1;i<=10;i++)
        System.out.println(i+" times "+num+" = "+(i*num));
    }
}
