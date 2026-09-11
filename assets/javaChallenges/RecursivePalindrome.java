import java.util.Scanner;

public class challenge69 {


    static int countDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return 1 + countDigits(num / 10);
    }


    static int power(int base, int exp) {
        if (exp == 0) {
            return 1;
        }
        return base * power(base, exp - 1);
    }


    static int getArmstrongSum(int num, int totalDigits) {
        if (num == 0) {
            return 0;
        }
        int lastDigit = num % 10;
        return power(lastDigit, totalDigits) + getArmstrongSum(num / 10, totalDigits);
    }


    static void printArmstrongSeries(int current, int limit) {

        if (current > limit) {
            return;
        }


        int digits = (current == 0) ? 1 : countDigits(current);


        if (getArmstrongSum(current, digits) == current) {
            System.out.print(current + " ");
        }


        printArmstrongSeries(current + 1, limit);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the upper limit for the series: ");
        int limit = scanner.nextInt();

        System.out.println("Armstrong numbers up to " + limit + ":");

        printArmstrongSeries(0, limit);


    }
}