import java.util.Scanner;

public class arrayutilities {
    static int[] arraycreation(){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number of element for the array");
        int num= input.nextInt();
        int[] arr=new int[num];
        for(int i=0;i<num;i++){
            System.out.println("Enter the "+(i+1)+"th value:");
            arr[i]= input.nextInt();
        }
        return arr;
    }
    static int[][] array2dcreation(){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter the number of rows for the array");
        int num= input.nextInt();
        System.out.println("Enter the number of columns for the array");
        int col= input.nextInt();
        int[][] arr=new int[num][col];
        for(int i=0;i<num;i++){
            for(int j=0;j<col;j++) {
                System.out.println("Enter the value:");
                arr[i][j] = input.nextInt();
            }
        }
        return arr;
    }
}
