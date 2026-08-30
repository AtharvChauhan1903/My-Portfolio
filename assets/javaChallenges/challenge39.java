import java.util.Scanner;

public class challenge39 {
    static void main() {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number of rows");
        int rows= input.nextInt();
        printIncreasingStar(rows);
        printDecreasingStar(rows);
        printIncreasingRightStar(rows);
    }
    static void printIncreasingStar(int rows){
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=i;j++)
                System.out.print("* ");
            System.out.println();
        }
    }
    static void printDecreasingStar(int rows){
        for(int i=1;i<=rows;i++){
            for(int j=rows;j>=i;j--)
                System.out.print("* ");
            System.out.println();
        }
    }
    static void printIncreasingRightStar(int rows){
        for(int i=1;i<=rows;i++){
            for(int k=rows;k>i;k--)
                System.out.print(" ");
            for(int j=1;j<=i;j++)
                System.out.print("*");
            System.out.println();
        }
    }
    static void printDecreasingRightStar(int rows){
        for(int i=1;i<=rows;i++){
            for(int k=1;k<i;k++)
                System.out.print(" ");
            for(int j=rows;j>=i;j--)
                System.out.print("*");
            System.out.println();
        }
    }
}
