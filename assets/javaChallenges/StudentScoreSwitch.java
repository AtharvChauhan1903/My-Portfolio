import java.util.Scanner;

public class challenge56switch {
    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = input.nextInt();
        int i=0;
        if(num>80)
            i=1;
        if(num>49&&num<80)
            i=2;
        switch(i){
            case 1:
           System.out.println("High");
           break;

            case 2:
           System.out.println("Moderate");
           break;

            default:
           System.out.println("Low");
        }

    }
}
