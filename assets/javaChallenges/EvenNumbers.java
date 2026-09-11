import java.util.Scanner;

public class challenge67 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int num= input.nextInt();
        for(int i=0;i<=num;i++){
            if(i%2!=0)
                continue;
            System.out.println(i);
        }
    }
}
