import java.util.Scanner;

public class challenge36 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the final range for Fibonacci series:");
        int num= input.nextInt();
        result(num);

    }
    static void result(int num) {
        int[] result = new int[num];

        if (num >= 1)
            result[0] = 0;

        if (num >= 2)
            result[1] = 1;

        for (int i = 2; i < num; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
         for(int j=0;j<result.length;j++)
             System.out.println(result[j]);

    }
}
