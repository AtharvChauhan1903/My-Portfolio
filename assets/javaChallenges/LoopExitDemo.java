import java.util.Scanner;

public class challenge65 {
    static void main() {
        Scanner input=new Scanner(System.in);
        String exit="Exit";
        String ink=" ";
        do{
            System.out.println("Enter your command");
             ink= input.next();
        }while(!exit.equals(ink));
        System.out.println("The program has been exited");

    }
}
