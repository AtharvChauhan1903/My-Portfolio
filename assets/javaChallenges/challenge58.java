import java.util.Scanner;

public class challenge58 {
    static void main() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the  number");
        int num = input.nextInt();
        System.out.println("Enter the  number");
        int num2 = input.nextInt();
        System.out.println("NOw enter the operation you want to perform\n1)Addition    2)Subtraction\n3)Multiplication    4)Division");
        int answer = input.nextInt();
        switch (answer) {
        case 1-> System.out.println("Result = "+(num+num2));
        case 2-> System.out.println("Result = "+(num-num2));
        case 3-> System.out.println("Result = "+(num*num2));
        case 4-> System.out.println("Result = "+(num/(float)num2));
        default -> System.out.println("Wrong input try again");
        }
    }
}
