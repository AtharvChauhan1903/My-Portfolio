import java.util.Scanner;

public class challenge62 {

    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = input.nextInt();
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if (num % 1 == 0) {
                count++;
            }
        }
        if(count<2){
            System.out.println("The number is neither prime nor composite");
        } else if (count==2) {
            System.out.println("The number is Prime");
        }else
            System.out.println("The number is composite");
        System.out.println("Are you stupid lah your cousin timmy can tell whether the number is prime or not without even telling timmy what number you will tell him");
    }
}

