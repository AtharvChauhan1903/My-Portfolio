import java.util.Scanner;

public class challenge59 {
    static void main() {
        Scanner input=new Scanner(System.in);
        String password="5691b100";
        String trial="a";
        int attempt=0;
        do{
            System.out.println("Enter the password Correctly");
            trial= input.next();
            attempt++;
            if(attempt==3&&!password.equals(trial)){
                System.out.println("access Denied get out");
                return;
            }
        }while(!password.equals(trial));
        System.out.println("Good job password cracked");
    }
}
