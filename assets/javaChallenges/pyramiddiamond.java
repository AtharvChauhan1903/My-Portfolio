import java.util.Scanner;

public class pyramiddiamond {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number");
        int rows= input.nextInt();
        pyramid(rows);
        pyramiddownward(rows);
        diamond(rows);
    }
    static void pyramid(int rows){
        for(int i=1;i<=rows;i++) {
            for (int k = rows; k > i; k--)
                System.out.print(" ");

            for (int j = 1; j <= (2 * i - 1); j++)
                System.out.print("*");

            System.out.println();
        }
    }
    static void pyramiddownward(int rows){
        for(int i=rows;i>=0;i--) {
            for (int k =rows; k>i; k--)
                System.out.print(" ");

            for (int j = 1; j <= (2 * i - 1); j++)
                System.out.print("*");

            System.out.println();
        }
    }
    static void diamond(int rows){
        pyramid(rows);
        pyramiddownward(rows);
    }
}
